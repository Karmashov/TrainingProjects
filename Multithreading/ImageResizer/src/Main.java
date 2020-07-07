import java.io.File;

public class Main
{
    private static int newWidth = 300;

    public static void main(String[] args)
    {
        Runtime runtime = Runtime.getRuntime();
        int logicCores = runtime.availableProcessors();

        String srcFolder = "D:\\2\\";
        String dstFolder = "D:\\1\\";

        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();

        long start = System.currentTimeMillis();

        int filesParts = files.length / logicCores;
        int residue = 0;

        if (files.length % logicCores == 0)
        {
            for (int i = 0; i < logicCores; i++)
            {
                File[] files1 = new File[filesParts];
                System.arraycopy(files, i * filesParts, files1, 0, filesParts);
                ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
                new Thread(resizer).start();
            }
        }
        else
        {
            for (int i = 0; i < logicCores - 1; i++)
            {
                File[] files1 = new File[filesParts];
                System.arraycopy(files, i * filesParts, files1, 0, filesParts);
                ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
                new Thread(resizer).start();
            }
            residue = files.length % logicCores;
            File[] files1 = new File[residue];
            System.arraycopy(files, logicCores * filesParts, files1, 0, residue);
            ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
            new Thread(resizer).start();
        }

    }
}
