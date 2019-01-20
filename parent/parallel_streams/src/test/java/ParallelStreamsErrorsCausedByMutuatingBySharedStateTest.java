import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class ParallelStreamsErrorsCausedByMutuatingBySharedStateTest {

    List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);

    class Accumulator {
        public long total = 0;
        public void add(long value) { total += value; }
    }

    @Test
    public void stream_to_paralleling_simple_test() {

       Long sum = LongStream.rangeClosed(1, 10000)
                .reduce(0L, (a, b) -> a + b);

        out.println(sum);
    }

    @Test
    public void sequential_stream() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        long start = System.nanoTime();

        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, 10000).forEach(accumulator::add);

        long duration =  (System.nanoTime() - start);

        out.println(duration + " ns");
        out.println("Good result: " + accumulator.total);
    }

    @Test
    public void parallel_with_mutating_shared_accumulator_stream() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        long start = System.nanoTime();

        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, 10000).parallel().forEach(accumulator::add);

        long duration =  (System.nanoTime() - start);

        out.println(duration + " ns");
        out.println("Bad result: " + accumulator.total);
    }


}
