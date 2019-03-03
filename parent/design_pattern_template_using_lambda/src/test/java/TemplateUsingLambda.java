import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Consumer;

@RunWith(SpringRunner.class)
public class TemplateUsingLambda {

    /*The template method design pattern is a common solution when you need to represent the
    outline of an algorithm and have the additional flexibility to change certain parts of it. Okay, it
    sounds a bit abstract. In other words, the template method pattern is useful when you find
    yourself in a situation such as “I’d love to use this algorithm but I need to change a few lines so it
    does what I want.”*/

    class OnlineBanking {

        public void processCustomer(int id, Consumer<String> makeCustomerHappy) {

            String customer = "customer_" + id;
            makeCustomerHappy.accept(customer);
        }
    }

    @Test
    public void template_method_usage_without_lambda() {

        new OnlineBanking().processCustomer(12, (customer) -> System.out.println("Customer: " + customer + " get coffe"));
        new OnlineBanking().processCustomer(12, (customer) -> System.out.println("Customer: " + customer + " get fresh rice"));
    }
}