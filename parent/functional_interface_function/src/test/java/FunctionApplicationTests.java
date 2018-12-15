import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionApplicationTests {

    List<Developer> developers = Arrays.asList(
            new Developer("John", "Milovicz", 7800.00),
            new Developer("Mark", "Maklovicz", 4000.00),
            new Developer("Kent", "Packolo", 5900.00),
            new Developer("Mark", "Breckin", 5800.00),
            new Developer("Nick", "Carpatio", 9800.00),
            new Developer("Michael", "Lemaro", 3300.00),
            new Developer("Greg", "Mandolo", 6600.00)
    );

    /*    Interface Function<T,R>

        Type Parameters:
        T - the type of the input to the function
        R - the type of the result of the function*/
    @Test
    public void calculate_result() {

        Function<Developer, Double> grossSalaryCalculate = developer -> developer.getSalary() * 1.23;

        Double grossSalary = grossSalaryCalculate.apply(developers.get(0));
        out.println(grossSalary);
    }

    @Test
    public void calculate_result_example_two() {

        Developer developerWithCalculatedSalary =
                Developer.calculateSalary(developers.get(0), developer -> developer.getSalary() * 1.23);

        out.println(developerWithCalculatedSalary);
    }

    @Test
    public void function_default_and_then_method() {

        Function<Developer, Double> grossSalaryCalculate = developer -> developer.getSalary() * 1.23;
        Function<Double, Double> divideSalaryByTwo = grossSalary -> grossSalary / 2;

        Double grossSalaryDevidedByTwo = grossSalaryCalculate.andThen(divideSalaryByTwo).apply(developers.get(0));
        out.println(grossSalaryDevidedByTwo);
    }

/*    Interface DoubleFunction<R>

    Type Parameters:
    R - the type of the result of the function*/

    @Test
    public void double_function() {

        DoubleFunction<String> transformToString = d -> d + " is now string";

        out.println(transformToString.apply(0.24));
    }

/*    public interface DoubleToIntFunction

      Represents a function that accepts a double-valued argument and produces an int-valued result.
      This is the double-to-int primitive specialization for Function.*/

    @Test
    public void double_to_int_function() {

        DoubleToIntFunction roundUpFunction = d -> {
            return (int) d / 1;
        };

        out.println(roundUpFunction.applyAsInt(2.23));

        out.println(Developer.roundUpSalary(developers.get(0), roundUpFunction));
    }


  /*  Interface BiFunction<T,U,R>

    Type Parameters:
    T - the type of the first argument to the function
    U - the type of the second argument to the function
    R - the type of the result of the function*

    Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.
    */

    @Test
    public void binary_function() {

        BiFunction<Integer, Integer, Integer> sumNumberFunction = (x, y) -> {
            return x + y;
        };

        out.println(sumNumberFunction.apply(23, 13));
    }
/*
    Interface ToIntBiFunction<T,U>

    Type Parameters:
    T - the type of the first argument to the function
    U - the type of the second argument to the function
    */

    @Test
    public void to_int_bi_function() {

        ToIntBiFunction<Double, Double> sumNumberAndRoundUp = (x, y) -> {
            return x.intValue() + y.intValue();
        };

        out.println(sumNumberAndRoundUp.applyAsInt(23.76, 13.89));
    }
}
