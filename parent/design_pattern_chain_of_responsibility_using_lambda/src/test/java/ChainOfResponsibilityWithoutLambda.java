import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@RunWith(SpringRunner.class)
public class ChainOfResponsibilityWithoutLambda {

    /*The chain of responsibility pattern is a common solution to create a chain of processing objects
            (such as a chain of operations). One processing object may do some work and pass the result to
    another object, which then also does some work and passes it on to yet another processing
    object, and so on.
            Generally, this pattern is implemented by defining an abstract class representing a processing
            object that defines a field to keep track of a successor. Once it has finished its work, the
    processing object hands over its work to its successor. In code it looks like this:*/

    @Test
    public void chain_of_responsibility_usage_without_lambda() {

        UnaryOperator<String> registerOrder = (order) -> "Order number: " + order + " has been registered";
        UnaryOperator<String> addPromoCoupon = (order) -> order + " with promo coupon";

        Function<String, String> orderProcessingChain =
                registerOrder.andThen(addPromoCoupon);

        System.out.println(orderProcessingChain.apply("1224"));
    }
}