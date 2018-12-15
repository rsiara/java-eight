import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.BinaryOperator;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class BinaryOperatorApplicationTests {

/*
    Interface BinaryOperator<T>

    Type Parameters:
    T - the type of the operands and result of the operator

    All Superinterfaces:
    BiFunction<T,T,T>
*/

    @Test
    public void binary_operator() {

        BinaryOperator<Integer> multiplyFunction = (a, b) -> a * b;

        out.println(multiplyFunction.apply(3, 2));
    }

    @Test
    public void binary_operator_created_by_method_reference() {

        BinaryOperator<Integer> multiplyFunction = BinaryOperatorApplicationTests::multiply;

        out.println(multiplyFunction.apply(3, 2));
    }

    public static Integer multiply(Integer a, Integer b) {

        return a * b;
    }

}
