import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

@RunWith(SpringRunner.class)
public class CollectorPartitioningTests {

    /*Partitioning is a special kind of grouping,
    in which the resultant map contains at most two different groups
     – one for true and one for false.*/

    List<Developer> developers = Arrays.asList(
            new Developer("John", "Milovicz", 7800.00),
            new Developer("Mark", "Maklovicz", 4000.00),
            new Developer("Kent", "Packolo", 5900.00),
            new Developer("Mark", "Breckin", 5800.00),
            new Developer("Nick", "Carpatio", 9800.00),
            new Developer("Michael", "Lemaro", 3300.00),
            new Developer("Greg", "Mandolo", 6600.00)
    );


    @Test
    public void partitioning() {

        Map<Boolean, List<Developer>> developersByRichPoor = developers.stream()
                .collect(partitioningBy(Developer::isRich));

       out.println("Rich developers" + developersByRichPoor.get(true));
       out.println("Poor developers" + developersByRichPoor.get(false));
    }

    @Test
    public void partitioning_with_grouping() {

        Map<Boolean, Map<Character, List<Developer>>> developersByRichPoor = developers.stream()
                .collect(partitioningBy(Developer::isRich,
                        groupingBy(d -> d.getName().charAt(0) )));

        out.println("Rich developers, grouped by first letter of name" + developersByRichPoor.get(true));
        out.println("Poor developers, grouped by first letter of name" + developersByRichPoor.get(false));
    }

    @Test
    public void is_prime(){

       int candidate = 7;

       boolean isPrime =  IntStream.range(2, candidate)
            .noneMatch(i -> candidate % i == 0);

        out.println("Candidate: " + candidate + " is " + isPrime + " prime");
    }

    @Test
    public void is_prime_using_function(){

        Function<Integer, Boolean> isPrime = candidate -> IntStream.range(2, candidate)
                .boxed()
                .noneMatch(divider -> candidate % divider == 0);

        boolean isPrimeResult = isPrime.apply(21);
        out.println(isPrimeResult);
    }

    @Test
    public void partitioning_numbers_for_primes_and_none_primes() {

        Function<Integer, Boolean> isPrime = candidate -> IntStream.range(2, candidate)
                .boxed()
                .noneMatch(divider -> candidate % divider == 0);

        int lastCandidateNumber = 100;

        Map<Boolean, List<Integer>> primes = IntStream.range(1, lastCandidateNumber)
                .boxed()
                .collect(partitioningBy(candidate -> isPrime.apply(candidate)));


        out.println("Primes : " + primes.get(true));
    }
}