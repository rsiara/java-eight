import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class ParallelStreamsBenchmarkTest {

    List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);

    /*
        This is quite disappointing: the parallel version of the summing method is much slower than the
        sequential one. How can you explain this unexpected result? There are actually two issues mixed
        together:
        - iterate generates boxed objects, which have to be unboxed to numbers before they can be added.
        - iterate is difficult to divide into independent chunks to execute in parallel.
    */

    @Test
    public void stream_to_paralleling_simple_test() {

       Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(3)
                .reduce(0L, (a, b) -> a + b);

        out.println(sum);
    }

    @Test
    public void iterative_sum() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        long start = System.nanoTime();

        long n = 5000;
        long sum = 0;
        for (long i = 1L; i <= n; i++) {
            sum += i;
        }

        long duration =  (System.nanoTime() - start);

        out.println(duration + " ns");
        out.println(sum);
    }

    @Test
    public void parallel_stream_sum_test() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        long start = System.nanoTime();

        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(10000)
                .parallel()
                .reduce(0L, (a, b) -> a + b);

        long duration =  (System.nanoTime() - start);

        out.println(duration);
        out.println(sum);
    }

    @Test
    public void stream_sum_test() { //faster than parallel

        long start = System.nanoTime();

        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(10000)
                .reduce(0L, (a, b) -> a + b);

        long duration =  (System.nanoTime() - start);

        out.println(duration);
        out.println(sum);
    }
}
