import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class FromLambdaToMethodReference {

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
    public void lambdaAsMethodReference() {

        /*Before refactor*/
        System.out.println("Before refactor");
        Map<String, List<Developer>> developersBySalary =
                developers.stream()
                        .collect(
                                Collectors.groupingBy(developer -> {
                                    if (developer.getSalary() > 6000) return "RICH";
                                    else if (developer.getSalary() < 6000 && developer.getSalary() > 3000)
                                        return "NORMAL";
                                    else return "POOR";
                                })
                        );

        developersBySalary.forEach((k, v) -> System.out.println("k = " + k + " v =" + v));

        /*After refactor lambda to method reference*/
        System.out.println("After refactor");
        Map<String, List<Developer>> developersBySalaryTwo =
                developers.stream()
                        .collect(Collectors.groupingBy(Developer::groupBySalary));

        developersBySalaryTwo.forEach((k, v) -> System.out.println("k = " + k + " v =" + v));

        /*After refactor lambda to static method reference*/
        System.out.println("After refactor - static method reference invocation");
        Map<String, List<Developer>> developersBySalaryThree =
                developers.stream()
                        .collect(Collectors.groupingBy(Developer::groupDeveloperBySalary));

        developersBySalaryThree.forEach((k, v) -> System.out.println("k = " + k + " v =" + v));
    }
}
