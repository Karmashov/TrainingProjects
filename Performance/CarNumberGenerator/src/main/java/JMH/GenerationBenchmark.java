package JMH;

import generator.BasicGenerator;
import generator.Generator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms1G", "-Xmx1G"})
public class GenerationBenchmark {
    private Generator newGenerator;
    private BasicGenerator basicGenerator;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(GenerationBenchmark.class.getSimpleName())
                .forks(2)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        int threadID = 0;
        long time = System.currentTimeMillis();
        int regionStartPosition = 1;
        int regionEndPosition = 2;

        newGenerator = new Generator(threadID, time, regionStartPosition, regionEndPosition);
        basicGenerator = new BasicGenerator(threadID, time, regionStartPosition, regionEndPosition);
    }

    @Benchmark
    public void newGeneratorBenchmark(Blackhole bh) {
        newGenerator.run();
        bh.consume(0);
    }

    @Benchmark
    public void basicGeneratorBenchmark(Blackhole bh) {
        basicGenerator.run();
        bh.consume(0);
    }
}
