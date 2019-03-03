import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TemplateWithoutLambda {

    /*The template method design pattern is a common solution when you need to represent the
    outline of an algorithm and have the additional flexibility to change certain parts of it. Okay, it
    sounds a bit abstract. In other words, the template method pattern is useful when you find
    yourself in a situation such as “I’d love to use this algorithm but I need to change a few lines so it
    does what I want.”*/

    abstract class OnlineBanking {

        public void processCustomer(int id) {

            String customer = "customer_" + id;
            makeCustomerHappy(customer);
        }

        protected abstract void makeCustomerHappy(String customer);
    }


    class OnlineBankingInAsia extends OnlineBanking {

        @Override
        protected void makeCustomerHappy(String customer) {

            System.out.println("Customer: " + customer + " get fresh rice");
        }
    }

    class OnlineBankingInEurope extends OnlineBanking {

        @Override
        protected void makeCustomerHappy(String customer) {

            System.out.println("Customer: " + customer + " get coffe");
        }
    }

    @Test
    public void template_method_usage_without_lambda() {

        new OnlineBankingInAsia().processCustomer(12);
        new OnlineBankingInEurope().processCustomer(12);
    }
}