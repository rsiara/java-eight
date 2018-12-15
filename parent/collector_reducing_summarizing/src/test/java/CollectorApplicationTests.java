import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class CollectorApplicationTests {

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
    public void collector_counting() {

        Long count = developers.stream().collect(Collectors.counting());

        out.println("Collector count: " + count);

        count = developers.stream().count();

        out.println("Stream count: " + count);
    }

    @Test
    public void collector_max_and_min() {

        Comparator<Developer> developerComparator
                = (Developer d1, Developer d2) -> ((int) d1.getSalary()) - ((int) (d2.getSalary()));

        developers.stream().collect(Collectors.maxBy(developerComparator)).ifPresent(d -> out.println("Max salary developer" + d));

        developers.stream().collect(Collectors.minBy(developerComparator)).ifPresent(d -> out.println("Min salary developer" + d));
    }

    @Test
    public void collector_summarizing() {

        Double sumOfDeveloperSalary = developers.stream().collect(Collectors.summingDouble(Developer::getSalary));

        out.println(sumOfDeveloperSalary);

        DoubleSummaryStatistics sumSummaryOfDeveloperSalary = developers.stream().collect(Collectors.summarizingDouble(Developer::getSalary));

        out.println(sumSummaryOfDeveloperSalary);
    }

    @Test
    public void collector_string_joining() {

        String stringOfDeveloperNames = developers.stream()
                .map(d -> d.getName() + " " + d.getSurname())
                .collect(Collectors.joining(","));

        out.println(stringOfDeveloperNames);
    }

    @Test
    public void collector_reduce_function() {

        Double sumOfDeveloperSalary = developers.stream()
                .collect(Collectors.reducing(
                        0.00,
                        Developer::getSalary,
                        (Double a, Double b) -> {
                            out.println("a: " + a + " b: " + b);
                            return a + b;
                        }
                        )
                );

        out.println(sumOfDeveloperSalary);
    }

/*    The reducing operation takes three parameters:

    identity: Like the Stream.reduce operation, the identity element is both the initial value of the reduction and the default result if there are no elements in the stream. In this example, the identity element is 0; this is the initial value of the sum of ages and the default value if no members exist.

    mapper: The reducing operation applies this mapper function to all stream elements. In this example, the mapper retrieves the age of each member.

    operation: The operation function is used to reduce the mapped values. In this example, the operation function adds Integer values.*/

    @Test
    public void collector_reduce_function_second() {

        Developer superDeveloper = developers.stream()
                .collect(Collectors.reducing(
                        new Developer("empty", "empty", 0.00),
                        (Developer a, Developer b) -> {
                            a.setSalary(a.getSalary() + b.getSalary());
                            return a;
                        }
                        )
                );

        out.println(superDeveloper);
    }

    @Test
    public void collector_reduce_function_highest_value() {

        Optional<Developer> superDeveloper = developers.stream()
                .collect(Collectors.reducing(
                        (Developer d1, Developer d2) -> d1.getSalary() > d2.getSalary() ? d1 : d2
                ));

        out.println(superDeveloper);
    }

    @Test
    public void collector_reduce_function_aggregate_function() {

        Double sumOfDeveloperSalary = developers.stream()
                .collect(Collectors.reducing(0.00, Developer::getSalary, Double::sum));

        out.println(sumOfDeveloperSalary);
    }
}
