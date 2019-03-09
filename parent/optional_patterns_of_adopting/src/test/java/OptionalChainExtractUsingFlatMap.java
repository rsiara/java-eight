import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class OptionalChainExtractUsingFlatMap {

    class Person {

        private Optional<Car> car;

        public Person(Car car) {
            this.car = Optional.ofNullable(car);
        }

        public Optional<Car> getCar() {
            return car;
        }
    }

    class Car {

        private Optional<Insurance> insurance ;

        public Car(Insurance insurance) {
            this.insurance = Optional.ofNullable(insurance);
        }

        public Optional<Insurance> getInsurance() {
            return insurance;
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
    public void optional_extract_chain_using_flat_map() {

        Person person = new Person(new Car(new Insurance("Insurance name")));
        Optional<Person> optionalPerson = Optional.ofNullable(person);

        String personCarInsuranceName = optionalPerson
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        out.println(personCarInsuranceName);
    }

    @Test
    public void optional_extract_chain_using_flat_map_when_objects_are_null() {

        Optional<Person> optionalPerson = Optional.ofNullable(null);

        String personCarInsuranceName = optionalPerson
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        out.println(personCarInsuranceName);
    }
}