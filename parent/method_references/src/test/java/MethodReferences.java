import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class MethodReferences {

    class Insurance {

        public Insurance(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }

    /*As mentioned earlier, you can get hold of an empty optional object using the static factory
    method Optional.empty:*/

    @Test
    public void optional_empty() {

        Optional<Insurance> insurance = Optional.empty();

        insurance.ifPresent((value) -> out.println(value));
    }

    /*If car were null, a NullPointerException would be immediately thrown (rather than getting a
            latent error once you try to access properties of the car).*/

    @Test
    public void optional_of() {

        Optional<Insurance> insurance = Optional.of(null);
        out.println("after creating optional");

        insurance.ifPresent((value) -> out.println(value.getName()));
    }

    @Test
    public void optional_of_nullable() {

        Optional<Insurance> insurance = Optional.ofNullable(null);
        out.println("after creating optional");

        insurance.ifPresent((value) -> out.println(value.getName()));
    }
}