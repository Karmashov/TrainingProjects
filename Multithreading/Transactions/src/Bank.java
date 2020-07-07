import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount)
    {
        if (accounts.get(fromAccountNum).isBlocked() || accounts.get(toAccountNum).isBlocked())
        {
            System.out.println("Вы пытаетесь совершить операцию с заблокированным аккаунтом!");
            return;
        }
        if (amount > accounts.get(fromAccountNum).getMoney())
        {
            System.out.println("Не достаточно средств!");
            return;
        }
        accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
        accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
        if (amount > 50000)
        {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount))
                {
                    System.out.println("Аккаунты заблокированы!");
                    accounts.get(fromAccountNum).setBlocked(true);
                    accounts.get(toAccountNum).setBlocked(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }
}
