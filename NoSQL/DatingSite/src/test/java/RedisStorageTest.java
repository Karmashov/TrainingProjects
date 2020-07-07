import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.RedisStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisStorageTest
{
    private RedisStorage testStorage;

    @BeforeEach
    public void setUp() {
        testStorage = new RedisStorage();
        testStorage.init();
    }

    @Test
    public void testAddAndGet() {
        testStorage.addUsers(37);

        int retrieved = testStorage.getUser();
        assertEquals(37, retrieved);
    }
}
