import collector.CustomPrimeCollector;
import collector.CustomToListCollector;
import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class CollectorInterfaceTest {

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

    List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);

    @Test
    public void custom_collector_interface_to_list_implementation() {

      String collectionAsStting =  developers.stream()
               .collect(new CustomToListCollector<>());

      out.println(collectionAsStting);
    }

    @Test
    public void custom_collector_interface_prime_number_implementation() {

        List<Integer> listOfPrimes =  developers.stream()
                .mapToInt(developer ->(int)developer.getSalary())
                .boxed()
                .collect(new CustomPrimeCollector());

        out.println(listOfPrimes);
    }

    @Test
    public void custom_collector_interface_prime_number_on_pure_int_stream_implementation() {

        List<Integer> listOfPrimes =  integers.stream()
                .collect(new CustomPrimeCollector());

        out.println(listOfPrimes);
    }
}
