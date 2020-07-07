import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable
{
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start)
    {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run()
    {
        System.out.println("Parallel process started");
        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                image = preResize(image);

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                );

                double widthStep = (double) image.getWidth() / (double) newWidth;
                double heightStep = (double) image.getHeight() / (double) newHeight;

                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB((int) Math.ceil(x * widthStep), (int) Math.ceil(y * heightStep));
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    private BufferedImage preResize(BufferedImage image)
    {
        int tmpWidth = newWidth * 2;
        int tmpHeight = (int) Math.round(
                image.getHeight() / (image.getWidth() / (double) tmpWidth)
        );
        BufferedImage tmpImage = new BufferedImage(
                tmpWidth, tmpHeight, BufferedImage.TYPE_INT_RGB
        );

        double widthStep = (double) image.getWidth() / (double) tmpWidth;
        double heightStep = (double) image.getHeight() / (double) tmpHeight;

        for (int x = 0; x < tmpWidth; x++)
        {
            for (int y = 0; y < tmpHeight; y++) {
                int rgb = image.getRGB((int) Math.ceil(x * widthStep), (int) Math.ceil(y * heightStep));
                tmpImage.setRGB(x, y, rgb);
            }
        }
        return tmpImage;
    }
}
