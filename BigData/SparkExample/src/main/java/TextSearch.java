import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.regex.Pattern;

public class TextSearch {
    private static final Pattern SPACE = Pattern.compile("\\s+");

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: JavaWordCount <file>");
            System.exit(1);
        }

        SparkSession spark = SparkSession
                .builder()
                .config("spark.master", "local")
                .appName("JavaWordCount")
                .getOrCreate();

        spark.read().textFile(args[0]).javaRDD()
                .flatMap(s -> Arrays.asList(SPACE.split(s)).iterator())
                .mapToPair(s -> new Tuple2<>(s.replaceAll("[^а-яА-Я -]", ""), 1))
                .reduceByKey((i1, i2) -> i1 + i2).saveAsTextFile(args[1]);

        spark.stop();
    }
}
