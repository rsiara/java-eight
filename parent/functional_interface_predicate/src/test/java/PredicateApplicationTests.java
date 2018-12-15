import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredicateApplicationTests {

    /*
    Interface Predicate<T>

    Type Parameters:
    T - the type of the input to the predicate

    Represents a predicate (boolean-valued function) of one argument.
    */

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
    public void filter_developers() {

        List<Developer> richDevelopers
                = Developer.filterDevelopers(developers, developer -> developer.getSalary() > 5000);

        richDevelopers.forEach(out::println);

        out.println("--------------------------------------------------------------------");

        richDevelopers
                = Developer.filterDevelopers(developers, Developer::isRich);

        richDevelopers.forEach(out::println);

        out.println("--------------------------------------------------------------------");

        List<Developer> developersWithSurnameStartWithM
                = Developer.filterDevelopers(developers, (Developer d) -> d.getSurname().startsWith("M"));

        developersWithSurnameStartWithM.forEach(out::println);
    }
}
