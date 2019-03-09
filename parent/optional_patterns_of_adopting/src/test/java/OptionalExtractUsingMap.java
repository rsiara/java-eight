import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintStream;
import java.util.Optional;

import static java.lang.System.in;
import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class OptionalExtractUsingMap {

    class Person {

        private Optional<Car> car = null;

        public Optional<Car> getCar() {
            return car;
        }
    }

    class Car {

        private Insurance insurance ;

        public Optional<Insurance> getInsurance() {
            return Optional.ofNullable(insurance);
        }

        public void setInsurance(Insurance insurance) {
            this.insurance = insurance;
        }
    }

    class Motorbike {

        private Optional<Insurance> insurance ;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }

        public void setInsurance(Insurance insurance) {
            this.insurance = Optional.ofNullable(insurance);
        }
    }


    class Insurance {

        public Insurance(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }

    @Test
    public void extract_value_from_optional_using_map_if_value_exist() {

        Insurance insurance = new Insurance("Insurance for everything");
        Car car = new Car();
        car.setInsurance(insurance);

        String insuranceName = car.getInsurance().map(Insurance::getName).get();
        out.println(insuranceName);


        car.setInsurance(null);
        insuranceName = car.getInsurance().map(Insurance::getName).get();

        out.println(insuranceName);
    }

    @Test
    public void extract_value_from_optional_using_map_if_value_is_null() {

        Car car = new Car();
        car.setInsurance(null);

        Optional<String> insuranceName = car.getInsurance().map(Insurance::getName);

        out.println();
        insuranceName.ifPresent((value) -> out.println(value));
    }

    @Test
    public void extract_value_from_optional_using_map_if_value_is_null_and_field_is_optional() {

        Motorbike motorbike = new Motorbike();
        motorbike.setInsurance(null);

        Optional<String> insuranceName = motorbike.getInsurance().map(Insurance::getName);

        insuranceName.ifPresent((value) -> out.println(value));
    }
}