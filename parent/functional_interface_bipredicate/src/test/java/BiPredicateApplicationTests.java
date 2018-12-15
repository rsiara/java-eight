import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class BiPredicateApplicationTests {

/*    Interface BiPredicate<T,U>

    Type Parameters:
    T - the type of the first argument to the predicate
    U - the type of the second argument the predicate*/

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
    public void bipredicate() {

        BiPredicate<Developer, Integer> filterDeveloperBySalaryFunction = (d, s) -> d.getSalary() >= s;

        out.println(filterDeveloperBySalaryFunction.test(developers.get(0), 7000));
    }
}
