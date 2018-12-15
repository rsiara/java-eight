package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
public class Developer implements Comparable {

    private String name;
    private String surname;
    private double salary;

    public Developer(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(this.salary, (((Developer) o).salary));
    }

    public static List<Developer> filterDevelopers(List<Developer> inventory, Predicate<Developer> p) {

        List<Developer> result = new ArrayList<>();

        for (Developer developer : inventory) {
            if (p.test(developer)) {
                result.add(developer);
            }
        }
        return result;
    }

    public static Developer calculateSalary(Developer developer, Function<Developer, Double> calculateSalaryFunction) {

        developer.setSalary(calculateSalaryFunction.apply(developer));
        return developer;
    }

    public static Integer roundUpSalary(Developer developer, DoubleToIntFunction roundUpFunction) {

        return roundUpFunction.applyAsInt(developer.getSalary());
    }

    public static void showDevelopers(List<Developer> developers, Consumer<Developer> printFunction) {

        developers.forEach(printFunction);
    }

    public boolean isRich() {
        return getSalary() > 5000;
    }
}
