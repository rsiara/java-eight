import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class BiconsumerApplicationTests {

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
    public void biconsumer() {

        BiConsumer<Developer, String> printDeveloperNameWithSuffixFunction = (d, s) -> {
            out.println(s + " " + d.getName());
        };


        printDeveloperNameWithSuffixFunction.accept(developers.get(0), "Super");
    }

}
