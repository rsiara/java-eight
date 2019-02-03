import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AnonymousToLambda {

    @Test
    public void simpleRunnableExample() {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("Run run run");
            }
        };

        Runnable runnableAsLambda = () -> System.out.println("Run run lambda");
    }


//Second, anonymous classes are allowed to
//shadow variables from the enclosing class. Lambda expressions can’t (they’ll cause a compile
//error), as shown in the following code
// Int test below which I just wrote problem doesn't appear

    private int a = 10;
    @Test
    public void simpleRunnableExampleWithShadowingVariableProblem() {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                int a = 20;
                System.out.println("a = " + a);
            }
        };

        Runnable runnableAsLambda = () -> {
            int a = 30;
            System.out.println("a = " + a);
        };

        new Thread(runnable).start();
        new Thread(runnableAsLambda).start();
    }

    interface Task{
        void execute();
    }

    public static void doSomething(Task task){
        task.execute();
    }

    public static void doSomething(Runnable runnable){
        runnable.run();
    }

    @Test
    public void ambiguousMethodCall() {

        //below not compile
//        doSomething(() -> System.out.println("Do something"));

        doSomething((Task)() -> System.out.println("Do something Task"));
        doSomething((Runnable) () -> System.out.println("Do something Runnable"));
    }
}
