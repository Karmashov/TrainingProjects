package generator;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class Generator implements Runnable {
    private long threadID;
    private long start;
    private int regionStartPosition;
    private int regionEndPosition;

    public Generator(long threadID, long start, int regionStartPosition, int regionEndPosition) {
        this.threadID = threadID;
        this.start = start;
        this.regionStartPosition = regionStartPosition;
        this.regionEndPosition = regionEndPosition;
    }

    @Override
    public void run() {
        StringBuilder path = new StringBuilder();
        path.append("res/numbers").append(threadID).append(".txt");
        try (FileChannel writer = FileChannel.open(Paths.get(path.toString()), WRITE, CREATE)){
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for(int regionCode = regionStartPosition; regionCode <= regionEndPosition; regionCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(regionCode, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
                writer.write(ByteBuffer.wrap(builder.toString().getBytes()));
            }

            writer.force(true);
            System.out.println("Parallel process finished: " + threadID);
            System.out.println((System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static synchronized String padNumber(int number, int numberLength) {
        String numberStr = String.valueOf(number);
        int padSize = numberLength - numberStr.length();
        for(int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
