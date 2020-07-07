package generator;

import java.io.FileOutputStream;

public class BasicGenerator implements Runnable {
    private long threadID;
    private long start;
    private int regionStartPosition;
    private int regionEndPosition;

    public BasicGenerator(long threadID, long start, int regionStartPosition, int regionEndPosition) {
        this.threadID = threadID;
        this.start = start;
        this.regionStartPosition = regionStartPosition;
        this.regionEndPosition = regionEndPosition;
    }

    @Override
    public void run() {
        try (FileOutputStream writer = new FileOutputStream("res/numbers.txt");){
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for(int regionCode = regionStartPosition; regionCode <= regionEndPosition; regionCode++) {
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                String carNumber = firstLetter + padNumber(number, 3) +
                                        secondLetter + thirdLetter + padNumber(regionCode, 2);
                                writer.write(carNumber.getBytes());
                                writer.write('\n');

                            }
                        }
                    }
                }
            }
            writer.flush();
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
