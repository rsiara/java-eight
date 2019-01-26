import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class ForkJoinTest {

    int[] integers = IntStream.rangeClosed(1, 5000).toArray();

    @Test
    public void fork_join_task_sum() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

        ForkJoinSumCulculator forkJoinSumCulculator =
                new ForkJoinSumCulculator(integers, 0, integers.length);

        long start = System.nanoTime();

        long sum = new ForkJoinPool().invoke(forkJoinSumCulculator);

        long duration =  (System.nanoTime() - start);
        out.println("Duration: " + duration + " ns");
        out.println("Sum: " + sum);
    }

    private class ForkJoinSumCulculator extends RecursiveTask<Long>{

        private static final int THRESHOLD = 1000;

        private int[] numbers;
        private int start;
        private int end;

        public ForkJoinSumCulculator(int[] numbers, int start, int end) {

            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;

            if((end - start) <= THRESHOLD){
                return computeSequentially();
            }

            ForkJoinSumCulculator leftTask =
                    new ForkJoinSumCulculator(numbers, start, start + length / 2);
            leftTask.fork();

            ForkJoinSumCulculator rightTask =
                    new ForkJoinSumCulculator(numbers, start + length / 2, end);
            long rightTaskResult = rightTask.compute();

            long leftTaskResult = leftTask.join();

            return leftTaskResult + rightTaskResult;
        }

        private Long computeSequentially() {

            long sum = 0;
            for(int i = start; i < end; i++){
                sum += numbers[i];
            }
            return sum;
        }
    }
}
