import redis.RedisStorage;

import java.util.Random;

public class DatingSite
{
    private static final int users = 20;

    public static void main(String[] args)
    {
        try (RedisStorage redis = new RedisStorage())
        {
            redis.init();
            addUsers(redis);
            for (; ; )
            {
                boolean paidServiceCheck = false;
                for (int i = 1; i <= users; i++)
                {
                    double rand = Math.random();

                    if (rand >= 0.7 && !paidServiceCheck)
                    {
                        paidServiceCheck = true;
                        int userId = new Random().nextInt(users);
                        if (userId == 0)
                        {
                            userId = new Random().nextInt(users);
                        }
                        System.out.println("> Пользователь " + userId + " оплатил платную услугу");
                        printUser(userId);
                        redis.moveUser(userId);
                    }

                    printUser(redis.getUser());
                    redis.moveUser(redis.getUser());
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if (i % 10 == 0)
                    {
                        paidServiceCheck = false;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void printUser(int userId)
    {
        System.out.println("- На главной странице показываем пользователя " + userId);
    }

    private static void addUsers(RedisStorage redis)
    {
        for (int i = 1; i <= users; i++)
        redis.addUsers(i);
    }
}
