import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class BifunctionApplicationTests {

/*    Interface BiFunction<T,U,R>

    Type Parameters:
    T - the type of the first argument to the function
    U - the type of the second argument to the function
    R - the type of the result of the function*/

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
    public void bifunction() {

        BiFunction<Developer, String, Developer> createDevFromDev = (d, s) -> {
            return new Developer(s, d.getSurname(), d.getSalary());
        };

        out.println(createDevFromDev.apply(developers.get(0), "Pablo"));
    }
}
