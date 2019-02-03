import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class HelperStaticMethod {

    List<Developer> developers = Arrays.asList(
            new Developer("John", "Milovicz", 7800.00),
            new Developer("Mark", "Maklovicz", 4000.00),
            new Developer("Kent", "Packolo", 5900.00),
            new Developer("Mark", "Breckin", 5800.00),
            new Developer("Nick", "Carpatio", 9800.00),
            new Developer("Michael", "Lemaro", 3300.00),
            new Developer("Greg", "Mandolo", 6600.00),
            new Developer("Andrew", "Caravano", 1500.0)
    );


    @Test
    public void comparator_static_helper_methods() {

        System.out.println("Comparator ");

        System.out.println("developers = " + developers);
        developers.sort(Comparator.comparing(Developer::getSalary));
        System.out.println("developers = " + developers);


        Developer richestDeveloper = developers.stream().collect(Collectors.maxBy(Comparator.comparing(Developer::getSalary))).get();
        System.out.println("richestDeveloper = " + richestDeveloper);
    }


    @Test
    public void predefined_reduce_methods() {

        /*Before refactor*/
        System.out.println("Before refactor");
        double sumOfDevelopersSalary =
                developers.stream().mapToDouble(Developer::getSalary).reduce(0, (d1, d2) -> d1 + d2);

        System.out.println("sumOfDevelopersSalary = " + sumOfDevelopersSalary);

        /*After refactor*/
        System.out.println("After refactor");
        sumOfDevelopersSalary = 0;
        sumOfDevelopersSalary = developers.stream().collect(Collectors.summingDouble(Developer::getSalary));

        System.out.println("sumOfDevelopersSalary = " + sumOfDevelopersSalary);

    }
}
