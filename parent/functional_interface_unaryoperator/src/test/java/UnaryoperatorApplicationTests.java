import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class UnaryoperatorApplicationTests {

/*    Interface UnaryOperator<T>

    Type Parameters:
    T - the type of the operand and result of the operator

    All Superinterfaces:
    Function<T,T>

    Represents an operation on a single operand that produces a result of the same type as its operand. This is a specialization of Function for the case where the operand and result are of the same type.
    */

    @Test
    public void unary_operator() {

        UnaryOperator<Integer> squareFunction = i -> i * i;

        out.println(squareFunction.apply(3));
    }

    @Test
    public void unary_operator_created_by_method_reference() {

        UnaryOperator<Integer> squareFunction = UnaryoperatorApplicationTests::squareFunctionMethod;

        out.println(squareFunction.apply(3));
    }


    @Test
    public void int_unary_operator() {

        IntUnaryOperator squareFunction = i -> i * i;

        out.println(squareFunction.applyAsInt(3));
    }

    public static Integer squareFunctionMethod(Integer number) {

        return number * number;
    }
}
