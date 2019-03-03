import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ChainOfResponsibilityUsingLambda {

    /*The chain of responsibility pattern is a common solution to create a chain of processing objects
            (such as a chain of operations). One processing object may do some work and pass the result to
    another object, which then also does some work and passes it on to yet another processing
    object, and so on.
            Generally, this pattern is implemented by defining an abstract class representing a processing
            object that defines a field to keep track of a successor. Once it has finished its work, the
    processing object hands over its work to its successor. In code it looks like this:*/

    abstract class ProcessingOrder<T> {

        protected ProcessingOrder<T> successor;

        public void setSuccessor(ProcessingOrder<T> successor) {

            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        abstract protected T handleWork(T input);
    }

    class RegisterOrder extends ProcessingOrder<String> {

        @Override
        protected String handleWork(String order) {

            return "Order number: " + order + " has been registered";
        }
    }

    class AddPromoCoupon extends ProcessingOrder<String> {

        @Override
        protected String handleWork(String order) {

            return order + " with promo coupon";
        }
    }

    @Test
    public void chain_of_responsibility_usage_without_lambda() {

        ProcessingOrder<String> registerOrder = new RegisterOrder();
        ProcessingOrder<String> addPromoCoupon = new AddPromoCoupon();

        registerOrder.setSuccessor(addPromoCoupon);

        System.out.println(registerOrder.handle("1224"));
    }
}