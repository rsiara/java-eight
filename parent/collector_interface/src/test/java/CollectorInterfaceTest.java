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


    @Test
    public void custom_collector_interface_implementation() {

      String collectionAsStting =  developers.stream()
               .collect(new CustomToListCollector<>());

      out.println(collectionAsStting);
    }
}
