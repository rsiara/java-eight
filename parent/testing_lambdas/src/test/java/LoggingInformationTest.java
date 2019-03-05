import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class LoggingInformationTest {

    /*Let’s say you’re trying to debug a pipeline of operations in a stream. What can you do? You could
    use forEach to print or log the result of a stream as follows:*/

    /*Unfortunately, once you call forEach, the whole stream is consumed. What would be really
    useful is to understand what each operation (map, filter, limit) produces in the pipeline of a
    stream.*/

    @Test
    public void for_each() {

        List<Integer> numbers = Arrays.asList(1, 3, 5, 6, 7, 9, 11, 44, 56, 89);
        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(out::println);
    }

    @Test
    public void peek() {

        List<Integer> numbers = Arrays.asList(1, 3, 5, 6, 7, 9, 11, 44, 56, 89);
        numbers.stream()
                .peek(x -> out.println("before map: " + x))
                .map(x -> x + 17)
                .peek(x -> out.println("before filter: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> out.println("before limit: " + x))
                .limit(3)
                .peek(x -> out.println("before collect to list: " + x))
                .collect(Collectors.toList());
    }
}
