import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DefaultMethodTest {

    /*Differences between static and default methods in Java 8

    Default methods can be overriden in implementing class, while static cannot.

    Static method belongs only to Interface class, so you can only invoke static method on Interface class,
    not on class implementing this Interface.*/


    /*So what’s the difference between an abstract class and an interface? They both can contain
    abstract methods and methods with a body.

    First, a class can extend only from one abstract class, but a class can implement multiple
            interfaces.

    Second, an abstract class can enforce a common state through instance variables (fields). An
    interface can’t have instance variables.*/

    interface LegacyInterface {

        void legacyMethod(String input);

        default void defaultMethod(String input){

            System.out.println("LegacyInterface.defaultMethod");
            System.out.println("input = " + input);
        }

        static void staticNewMethod(String input){

            System.out.println("LegacyInterface.staticNewMethod");
            System.out.println("input = [" + input + "]");
        }
    }

    class LegacyInterfaceImpl implements LegacyInterface {

        @Override
        public void legacyMethod(String input) {

            System.out.println("LegacyInterfaceImpl.legacyMethod");
            System.out.println("input = [" + input + "]");
        }
    }

    @Test
    public void default_method_test() {

        LegacyInterface legacyInterface = new LegacyInterfaceImpl();

        legacyInterface.legacyMethod("Legacy method");
        legacyInterface.defaultMethod("Default method");
        LegacyInterface.staticNewMethod("Static method");
    }
}