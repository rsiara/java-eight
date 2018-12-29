package collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;

public class CustomToListCollector<T, R> implements Collector<T, List<T>, R> {


    /* The supplier() method returns a Supplier instance that generates an empty accumulator instance */
    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<>();
    }

    /*BiConsumer -> Represents an operation that accepts two input arguments and returns no result.
      This is the two-arity specialization of Consumer.
      Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects. */

    /*The accumulator() method returns a function that is used for adding a new element to an existing accumulator */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, element) ->  list.add(element);
    }

    /* BinaryOperator - Represents an operation upon two operands of the same type, producing a result of the same type as the operands */
    /* The combiner() method returns a function that is used for merging two accumulators together: */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (lastAccumulator, newAccumulator) -> {
            lastAccumulator.addAll(newAccumulator);
            return lastAccumulator;
        };
    }

    /*
    Represents a function that accepts one argument and produces a result.
    */

    /*
    The finisher() method returns a function that is used for converting an accumulator to final result type
    */
    @Override
    public Function<List<T>, R> finisher() {
        return (list) -> (R) list.toString();
    }

    /*
    The characteristics() method is used to provide Stream with some additional information that will be used for internal optimizations.
     In this case, we do not pay attention to the elements order in a Set so that we will use Characteristics.UNORDERED.
     To obtain more information regarding this subject, check Characteristics‘ JavaDoc.
    */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(CONCURRENT));
    }
}