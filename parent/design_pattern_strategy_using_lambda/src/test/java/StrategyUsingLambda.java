import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StrategyUsingLambda {

    class Validator {

        ValidatorStrategy validatorStrategy;

        public Validator(ValidatorStrategy validatorStrategy) {
            this.validatorStrategy = validatorStrategy;
        }

        public boolean validate(String input) {

            return validatorStrategy.validate(input);
        }
    }

    interface ValidatorStrategy {

        boolean validate(String input);
    }

    /*By now you should recognize that ValidationStrategy is a functional interface (in addition, it has
    the same function descriptor as Predicate<String>). This means that instead of declaring new
    classes to implement different strategies, you can pass lambda expressions directly, which are
    more concise:

    Now strategy implementation is necessary
    */

    @Test
    public void strategy_usage_without_lambda() {

        System.out.println(new Validator((input) -> input.matches("[a-z]+")).validate("kkkkkk"));
        System.out.println(new Validator((input) -> input.matches("\\d+")).validate("kjdsad8sad"));
    }
}