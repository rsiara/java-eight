import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DefaultMethodRules {

    /* 1: Classes always win. A method declaration in the class or a superclass takes priority over any
    default method declaration.*/

    /* 2: Otherwise, sub-interfaces win: the method with the same signature in the most specific
    default-providing interface is selected. (If B extends A, B is more specific than A).*/

    /* 3: Finally, if the choice is still ambiguous, the class inheriting from multiple interfaces has to
            explicitly select which default method implementation to use by overriding it and calling the
    desired method explicitly.*/

    interface Greetable {

        default void helloWorld() {

            System.out.println("Greetable.helloWorld");
            System.out.println("Hello world");
        }
    }

    class SomeImplWithGreetings {

        public void helloWorld() {

            System.out.println("SomeImplWithGreetings.helloWorld");
            System.out.println("Hello world");
        }
    }

    class Client extends SomeImplWithGreetings implements Greetable {

    }

    @Test
    public void rules() {

        new Client().helloWorld();
    }
}