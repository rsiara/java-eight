import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@RunWith(SpringRunner.class)
public class FactoryUsingLambdass {

    /*The factory design pattern lets you create objects without exposing the instantiation logic to the
    client. For example, let’s say you’re working for a bank and they need a way of creating different
    financial products: loans, bonds, stocks, and so on.

    Typically you’d create a Factory class with a method that’s responsible for the creation of
    different objects, as shown here:*/

    static class CollectionFactory {
        static List createList(String name) {
            switch (name) {
                case "ArrayList":
                    return new ArrayList<>();
                case "Vector":
                    return new Vector();
                case "LinkedList":
                    return new LinkedList();
                default:
                    throw new RuntimeException("No such collection " + name);
            }
        }
    }

    @Test
    public void factory_usage_using_lambda() {

        List<String> list = CollectionFactory.createList("ArrayList");
        list = CollectionFactory.createList("Vector");
        list = CollectionFactory.createList("LinkedList");
    }
}