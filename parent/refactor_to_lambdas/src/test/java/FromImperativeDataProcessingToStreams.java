import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class FromImperativeDataProcessingToStreams {

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
    public void imperative_data_processing_to_parallel_stream_data_processing() {

        //Before refactor
        List<String> richDeveloperNames = new ArrayList<>();
        for (Developer dish : developers) {
            if (dish.getSalary() > 3000) {
                richDeveloperNames.add(dish.getName());
            }
        }

        System.out.println(richDeveloperNames);

        //After refactor

        richDeveloperNames = developers.parallelStream()
                .filter(d -> d.getSalary() > 3000) // pass only developers with salary above 3000
                .map(Developer::getName)
                .collect(Collectors.toList());

        System.out.println(richDeveloperNames);
    }
}
