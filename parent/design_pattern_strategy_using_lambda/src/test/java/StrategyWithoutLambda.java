import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StrategyWithoutLambda {

    class Validator{

        ValidatorStrategy validatorStrategy;

        public Validator(ValidatorStrategy validatorStrategy){
            this.validatorStrategy = validatorStrategy;
        }

        public boolean validate(String input){

            return validatorStrategy.validate(input);
        }
    }

    interface ValidatorStrategy{

        boolean validate(String input);
    }

    class IsNumeric implements ValidatorStrategy{

        @Override
        public boolean validate(String input) {
            return input.matches("\\d+");
        }
    }

    class IsLowerCase implements ValidatorStrategy{


        @Override
        public boolean validate(String input) {
            return input.matches("[a-z]+");
        }
    }

    @Test
    public void strategy_usage_without_lambda() {


        System.out.println(new Validator(new IsLowerCase()).validate("kkkkkk"));
        System.out.println(new Validator(new IsNumeric()).validate("kjdsad8sad"));
    }

}