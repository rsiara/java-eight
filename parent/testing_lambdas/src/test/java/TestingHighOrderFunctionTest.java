import org.junit.Test;
import org.mockito.internal.util.collections.ListUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestingHighOrderFunctionTest {

    /*Methods that take a function as argument or return another function (so-called higher-order
            functions, explained more in chapter 14) are a little harder to deal with. One thing you can do if
    a method takes a lambda as argument is test its behavior with different lambdas. For example,
    you can test the filter method created in chapter 2 with different predicates:*/

    @Test
    public void testing_high_order_function() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = ListUtil.filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = ListUtil.filter(numbers, i -> i < 3);

        assertEquals(Arrays.asList(2, 4), even);
        assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }
}
