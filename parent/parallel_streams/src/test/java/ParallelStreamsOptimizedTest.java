import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class ParallelStreamsOptimizedTest {

    List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);

    /*
    So how can you leverage your multicore processors and use the stream to perform a parallel sum
    in an effective way? We discussed a method called LongStream.rangeClosed in chapter 5. This
    method has two benefits compared to iterate:

    - LongStream.rangeClosed works on primitive long numbers directly so there’s no boxing and
    unboxing overhead.
    - LongStream.rangeClosed produces ranges of numbers, which can be easily split into independent
    chunks. For example, the range 1–20 can be split into 1–5, 6–10, 11–15, and 16–20.
    */

    @Test
    public void stream_to_paralleling_simple_test() {

       Long sum = LongStream.rangeClosed(1, 10000)
                .reduce(0L, (a, b) -> a + b);

        out.println(sum);
    }

    @Test
    public void iterative_sum() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        long start = System.nanoTime();

        long n = 10000;
        long sum = 0;
        for (long i = 1L; i <= n; i++) {
            sum += i;
        }

        long duration =  (System.nanoTime() - start);

        out.println(duration);
        out.println(sum);
    }

    @Test
    public void parallel_stream_sum_test() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        long start = System.nanoTime();

        Long sum = LongStream.rangeClosed(1, 10000)
                .reduce(0L, Long::sum);

        long duration =  (System.nanoTime() - start);

        out.println(duration);
        out.println(sum);
    }

    @Test
    public void stream_sum_test() { //faster than parallel

        long start = System.nanoTime();

        Long sum = LongStream.rangeClosed(1, 10000)
                .parallel()
                .reduce(0L, Long::sum);

        long duration =  (System.nanoTime() - start);

        out.println(duration);
        out.println(sum);
    }
}
