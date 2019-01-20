package collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;


public class CustomPrimeCollector implements Collector<Integer, List<Integer>, List<Integer>> {

    @Override
    public Supplier<List<Integer>> supplier() {

        return () -> new ArrayList<>();
    }


    /*BiConsumer -> Represents an operation that accepts two input arguments and returns no result.
      This is the two-arity specialization of Consumer.
      Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects. */

    /*The accumulator() method returns a function that is used for adding a new element to an existing accumulator */
    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {

        return (list, element) -> {
            Function<Integer, Boolean> isPrime = candidate -> IntStream.range(2, candidate)
                    .boxed()
                    .noneMatch(divider -> candidate % divider == 0);

            if (isPrime.apply(element)) {
                list.add(element);
            }
        };
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {

        return (lastAccumulator, newAccumulator) -> {
            lastAccumulator.addAll(newAccumulator);
            return lastAccumulator;
        };
    }

    @Override
    public Function<List<Integer>, List<Integer>> finisher() {

        return (list) -> list;
    }

    public Set<Characteristics> characteristics() {
        return Collections
                .unmodifiableSet(EnumSet.of(CONCURRENT));
    }
}
