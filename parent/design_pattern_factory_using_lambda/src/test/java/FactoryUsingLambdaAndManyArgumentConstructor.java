import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class FactoryUsingLambdaAndManyArgumentConstructor {

    /*The factory design pattern lets you create objects without exposing the instantiation logic to the
    client. For example, let’s say you’re working for a bank and they need a way of creating different
    financial products: loans, bonds, stocks, and so on.

    Typically you’d create a Factory class with a method that’s responsible for the creation of
    different objects, as shown here:*/

    interface TriArgFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    static class Product {

        private String name;
        private String manufacturer;
        private String color;

        public Product(String name, String manufacturer, String color) {
            this.name = name;
            this.manufacturer = manufacturer;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", manufacturer='" + manufacturer + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    static class ProductFactory {

        final static Map<String, Product> map = new HashMap<>();

        static TriArgFunction<String, String, String, Product> productConstruct =
                (name, manufacturer, color) -> new Product(name, manufacturer, color);

        static {
            map.put("Mercedes", productConstruct.apply("Klasa A", "Mercedes", "red"));
            map.put("Opel", productConstruct.apply("Astra", "Opel", "black"));
            map.put("Citroen", productConstruct.apply("C4", "Citroen", "white"));
        }

        static Product create(String name) {

            Product product = map.get(name);
            if (product != null) return product;
            throw new IllegalArgumentException("No such product " + name);
        }
    }

    @Test
    public void factory_usage_using_lambda() {

        System.out.println(ProductFactory.create("Mercedes"));
        System.out.println(ProductFactory.create("Opel"));
        System.out.println(ProductFactory.create("Citroen"));
    }
}