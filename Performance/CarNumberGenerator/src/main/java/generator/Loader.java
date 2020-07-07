package generator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int logicCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(logicCores);

        int regionCount = 50;

        int regionParts = (int) Math.ceil((double) regionCount / logicCores);

        for (int i = 0; i < logicCores; i++) {
            int regionStartPosition = i * regionParts + 1;
            int regionEndPosition = (i + 1) * regionParts;
            if (regionEndPosition > regionCount) {
                regionEndPosition = regionCount;
            }
            executor.execute(new Generator(i, startTime, regionStartPosition, regionEndPosition));
        }
        executor.shutdown();
    }
}