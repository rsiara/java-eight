import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class CollectorApplicationTests {

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
    public void grouping() {

        Map<String, List<Developer>> developerByName = developers.stream()
                .collect(Collectors.groupingBy(Developer::getName));

        out.println(developerByName);
    }

    @Test
    public void grouping_by_custom_classification_function() {

        Map<String, List<Developer>> developerBySalary = developers.stream()
                .collect(Collectors.groupingBy(d -> {
                    return d.getSalary() > 6000 ? "RICH" : "POOR";
                }));

        out.println(developerBySalary);
    }

    @Test
    public void grouping_multilevel() {

        Map<String, Map<String, List<Developer>>> developerBySalaryAndName = developers.stream()
                .collect(Collectors.groupingBy(d -> d.getSalary() > 6000 ? "RICH" : "POOR",
                        Collectors.groupingBy(Developer::getName)));

        out.println(Arrays.toString(developerBySalaryAndName.entrySet().toArray()));
    }

    @Test
    public void grouping_subgroups_with_counting() {

        Map<String, Long> richDevelopersCounting = developers.stream()
                .collect(Collectors.groupingBy(d -> d.getSalary() > 6000 ? "RICH" : "POOR",
                        Collectors.counting()));

        out.println(richDevelopersCounting);
    }


    @Test
    public void grouping_subgroups_with_max_by() {

        Map<String, Optional<Developer>> richestDeveloperInHisGroup = developers.stream()
                .collect(Collectors.groupingBy(d -> d.getSalary() > 6000 ? "RICH" : "POOR",
                        Collectors.maxBy(Comparator.comparingDouble(Developer::getSalary))));

        out.println(richestDeveloperInHisGroup);
    }

    @Test
    public void collectingAndThen_adapting_collector_result_to_diffrent_type() {

        Map<String, Developer> richestDeveloperInHisGroup = developers.stream()
                .collect(Collectors.groupingBy(d -> d.getSalary() > 6000 ? "RICH" : "POOR",
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Developer::getSalary)), Optional::get)
                        )
                );

        out.println(richestDeveloperInHisGroup);
    }

    @Test
    public void grouping_and_summing() {

        Map<String, Double> summaryOfSalaryInEachGroup = developers.stream()
                .collect(Collectors.groupingBy(d -> d.getSalary() > 6000 ? "RICH" : "POOR",
                        Collectors.summingDouble(Developer::getSalary))
                );

        out.println(summaryOfSalaryInEachGroup);
    }

    @Test
    public void grouping_conjuction_with_mapping() {

        Map<String, List<String>> groupByDevNameWithSalaryLevelType = developers.stream()
                .collect(Collectors.groupingBy(Developer::getName,
                        Collectors.mapping(d -> {
                            if (d.getSalary() > 5000) {
                                return "RICH";
                            } else {
                                return "POOR";
                            }
                        }, Collectors.toList())
                ));

        out.println(groupByDevNameWithSalaryLevelType);
    }

    @Test
    public void grouping_conjuction_with_mapping_and_usung_toCollection() {

        Map<String, Set<String>> groupByDevNameWithSalaryLevelType = developers.stream()
                .collect(Collectors.groupingBy(Developer::getName,
                        Collectors.mapping(d -> {
                            if (d.getSalary() > 5000) {
                                return "RICH";
                            } else {
                                return "POOR";
                            }
                        }, Collectors.toCollection(HashSet::new))
                ));

        out.println(groupByDevNameWithSalaryLevelType);
    }
}