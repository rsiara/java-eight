import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
public class FactoryWithoutLambda {

    /*The factory design pattern lets you create objects without exposing the instantiation logic to the
    client. For example, let’s say you’re working for a bank and they need a way of creating different
    financial products: loans, bonds, stocks, and so on.

    Typically you’d create a Factory class with a method that’s responsible for the creation of
    different objects, as shown here:*/


    /*Supplier<T> : This is a functional interface and can therefore
     be used as the assignment target for a lambda expression or method reference.*/

    static class CollectionFactory {

        final static Map<String, Supplier<List>> map = new HashMap<>();
        static {
            map.put("ArrayList", ArrayList::new);
            map.put("Vector", Vector::new);
            map.put("LinkedList", LinkedList::new);
        }

        static List createList(String name) {

            Supplier<List> p = map.get(name);
            if(p != null) return p.get();
            throw new IllegalArgumentException("No such product " + name);
        }
    }

    @Test
    public void factory_usage_without_lambda() {

        List<String> list = FactoryUsingLambda.CollectionFactory.createList("ArrayList");
        list.add("Element one");
        list.add("Element two");
        list.add("Element three");

        System.out.println(list);
        list = FactoryUsingLambda.CollectionFactory.createList("Vector");
        list = FactoryUsingLambda.CollectionFactory.createList("LinkedList");
    }
}