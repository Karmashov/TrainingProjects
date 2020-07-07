import java.util.HashMap;
import java.util.Random;

public class Generator implements Runnable
{
    private Bank bank;
    private Object[] values;
    private HashMap<String, Account> accounts = new HashMap<>();
    private int count;

    public Generator(Bank bank, int count) {
        this.bank = bank;
        this.count = count;
    }

    public void generateAccounts()
    {
        for (int i = 0; i < count; i++)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            accounts.put(sb.toString(), new Account((long) (300000 * Math.random()), sb.toString()));
            bank.setAccounts(accounts);
        }
        values = accounts.keySet().toArray();
    }

    @Override
    public void run()
    {
        Random random = new Random();
        for (int i = 0; i < 50; i++)
        {
            Account toAcc = bank.getAccounts().get(values[random.nextInt(values.length)].toString());
            Account fromAcc = bank.getAccounts().get(values[random.nextInt(values.length)].toString());
            long amount = (long) (70000 * Math.random());
            printInfo(fromAcc, toAcc);
            System.out.println("Amount: " + amount);
            bank.transfer(fromAcc.getAccNumber(), toAcc.getAccNumber(), amount);
            printInfo(fromAcc, toAcc);
            System.out.println("===================");
        }
    }

    private void printInfo(Account fromAcc, Account toAcc)
    {
        System.out.println("From " + fromAcc.getAccNumber() + ", balance: " + fromAcc.getMoney() + ", blocked: " + fromAcc.isBlocked());
        System.out.println("To " + toAcc.getAccNumber() + ", balance: " + toAcc.getMoney() + ", blocked: " + fromAcc.isBlocked());
    }
}
