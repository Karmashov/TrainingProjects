public class Main
{
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String path = "hdfs://ba1c75061fbc:8020";
    private static String filePath = "/test/file.txt";

    public static void main(String[] args) throws Exception
    {
        //Initialize + connection
        FileAccess fileAccess = new FileAccess(path);

        //Create + list
        fileAccess.create(path + "/test/test1");
        fileAccess.create(path + "/test/test1/file1.txt");
        fileAccess.create(path + "/test/test2/file2.txt");
        fileAccess.create(path + "/test/test3");
        System.out.println(fileAccess.list(path + "/test"));

        //Append + read
        for(int i = 0; i < 1_000; i++) {
            fileAccess.append(path + "/test1.txt", getRandomWord() + " ");
        }
        System.out.println(fileAccess.read(path + "/test1.txt"));

        //isDirectory + delete
        System.out.println(fileAccess.isDirectory(path + "/test"));
        fileAccess.delete(path + "/test/test3");
    }

    private static String getRandomWord()
    {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for(int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
