package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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

    public boolean isRich() {
        return getSalary() > 5000;
    }
}
