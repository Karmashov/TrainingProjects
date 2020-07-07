public class Main
{
    public static void main(String[] args)
    {
        Bank bank = new Bank();

        int count = 100;
        Generator generator = new Generator(bank, count);
        generator.generateAccounts();

        for (int i = 0; i < 8; i++)
        {
            Thread thread = new Thread(generator);
            thread.run();
        }
    }
}
