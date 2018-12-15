import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTests {

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
    public void consumer() {

        Consumer<Developer> printDeveloperBanner = developer -> {

            out.println(" X X X X X X X X X X X X");
            out.println(developer.getName());
            out.println(developer.getSurname());
            out.println(developer.getSalary());
            out.println(" X X X X X X X X X X X X");
        };

        printDeveloperBanner.accept(developers.get(0));
    }

    @Test
    public void pass_consumer_as_parameter() {

        Consumer<Developer> printDeveloperFunction = developer -> {

            out.println("-----------------------");
            out.println(developer.getName());
            out.println(developer.getSurname());
            out.println(developer.getSalary());
            out.println("-----------------------");
        };

        Developer.showDevelopers(developers, printDeveloperFunction);
    }


    @Test
    public void pass_consumer_as_lambda_parameter() {

        Developer.showDevelopers(developers, developer -> out.println(developer.getName()));
    }

}
