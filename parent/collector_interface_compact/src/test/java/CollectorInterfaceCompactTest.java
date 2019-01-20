import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class CollectorInterfaceCompactTest {

    List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);

    @Test
    public void custom_collector_by_implementing_in_place_three_core_methods() {

        List<Integer> listOfPrimes =  integers.stream()
                .collect(
                        () -> new ArrayList<>(), /*supplier*/
                        (List<Integer> accumulator, Integer candidate) -> {

                            if(IntStream.range(2, candidate) /*accumulator*/
                                    .boxed()
                                    .noneMatch(divider -> candidate % divider == 0)){
                                accumulator.add(candidate);
                            }
                        } ,
                        (List<Integer> lastAccumulator, List<Integer> newAccumulator) -> { /*combiner*/
                            lastAccumulator.addAll(newAccumulator);
                        }
                        );

        out.println(listOfPrimes);
    }
}
