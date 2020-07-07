package redis;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RedisStorage implements AutoCloseable
{
    private RedissonClient redisson;

    private RKeys rKeys;

    private RScoredSortedSet<String> users;

    private final static String key = "DATING_SITE";

    public void listKeys()
    {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys)
        {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    public void init()
    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try
        {
            redisson = Redisson.create(config);
        }
        catch (RedisConnectionException Exc)
        {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(key);
        rKeys.delete(key);
    }

    public void shutdown()
    {
        redisson.shutdown();
    }

    public void addUsers(int userId)
    {
        users.add(getTime(), String.valueOf(userId));
    }

    private double getTime() {
        return new Date().getTime();
    }

    public void moveUser(int userId)
    {
        users.remove(String.valueOf(userId));
        users.add(getTime(), String.valueOf(userId));
    }

    public int getUser()
    {
        return Integer.parseInt(users.first());
    }

    public void close() throws Exception
    {
        redisson.shutdown();
    }
}
