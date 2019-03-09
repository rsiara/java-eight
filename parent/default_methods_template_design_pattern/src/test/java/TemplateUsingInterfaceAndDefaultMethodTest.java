import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TemplateUsingInterfaceAndDefaultMethodTest {

    /*The template method design pattern is a common solution when you need to represent the
    outline of an algorithm and have the additional flexibility to change certain parts of it. Okay, it
    sounds a bit abstract. In other words, the template method pattern is useful when you find
    yourself in a situation such as “I’d love to use this algorithm but I need to change a few lines so it
    does what I want.”*/

    interface StringTransformer {

        default String processAlgorithm(String processableString) {

            processableString = toUpperCace(processableString);
            processableString = removeWhitespace(processableString);
            processableString = removeChars(processableString);

            return processableString;
        }

        default String toUpperCace(String processableString) {

            return processableString.toUpperCase();
        }

        default String removeWhitespace(String processableString) {

            return processableString.replaceAll("\\s+", "");
        }

        String removeChars(String processableString);
    }


    class StringTransformerCharacterRemover implements StringTransformer {

        @Override
        public String removeChars(String processableString) {

            return processableString.replaceAll("[a-zA-Z]", "");
        }
    }

    class StringTransformerDigitRemover implements StringTransformer {

        @Override
        public String removeChars(String processableString) {

            return processableString.replaceAll("\\d+", "");
        }
    }

    @Test
    public void template_method_usage_without_lambda() {

        String stringToProcess = "12380adhjjkzxhc123098sad98312";

        StringTransformer stringTransformer = new StringTransformerCharacterRemover();

        System.out.println("stringToProcess = " + stringTransformer.processAlgorithm(stringToProcess));

        stringToProcess = "12380adhjjkzxhc123098sad98312";

        stringTransformer = new StringTransformerDigitRemover();

        System.out.println("stringToProcess = " + stringTransformer.processAlgorithm(stringToProcess));
    }
}