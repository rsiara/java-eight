import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.function.Supplier;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierApplicationTests {

/*    Interface Supplier<T>

    Type Parameters:
    T - the type of results supplied by this supplier*/

    public static Date getSystemDate() {
        return new Date();
    }

    @Test
    public void supplier_example() {

        Supplier<Developer> developerGeneratorFunction = () -> new Developer("Cyber", "Dev", 10000);

        for (int i = 0; i < 10; i++) {

            out.println(developerGeneratorFunction.get());
        }
    }

    @Test
    public void supplier_as_method_reference_example() {

        Supplier<Date> dateSupplier = SupplierApplicationTests::getSystemDate;

        for (int i = 0; i < 10; i++) {

            out.println(dateSupplier.get());
        }
    }

    @Test
    public void supplier_with_state_example() {

        Supplier<Developer> developerGeneratorFunction = new Supplier<Developer>() {

            private int developerCount = 0;

            @Override
            public Developer get() {
                return new Developer("Cyber - " + ++developerCount, "Dev", 10000);

            }
        };

        for (int i = 0; i < 10; i++) {

            out.println(developerGeneratorFunction.get());
        }
    }

}
