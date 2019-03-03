import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
public class ExecuteAround {


    /*In chapter 3 we discussed another pattern that you can adopt: execute around. If you find
    yourself surrounding different code with the same preparation and cleanup phases, you can
    often pull that code into a lambda. The benefit is that you reuse the logic dealing with the
    preparation and cleanup phases, thus reducing code duplication.*/

    Logger logger = Logger.getLogger(this.getClass().getName());
    // Warunkowa odroczona realizacja

    @Test
    public void execute_around() throws IOException {

        String oneLine = processFile((BufferedReader bf) -> bf.readLine());
        System.out.println(oneLine);
        String twoLines = processFile((BufferedReader bf) -> bf.readLine() + bf.readLine());
        System.out.println(twoLines);
    }

    public String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/data.txt"))
        ) {
            return bufferedReaderProcessor.process(bufferedReader);
        }
    }

    interface BufferedReaderProcessor {

        String process(BufferedReader bufferedReader) throws IOException;
    }

    /* In example below, whithout execution method but with only functional interface exist problem with checked
     * exception */
    @Test
    public void execute_around_using_already_existing_java_functional_interface() throws IOException {

        String oneLine = processFileUsingAlreadyExistingFunctionalInterface((BufferedReader bf) -> {
            try {
                return bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
        System.out.println(oneLine);

        String twoLines = processFileUsingAlreadyExistingFunctionalInterface((BufferedReader bf) -> {
            try {
                return bf.readLine() + bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
        System.out.println(twoLines);
    }

    public String processFileUsingAlreadyExistingFunctionalInterface(Function<BufferedReader, String> readFunction) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/data.txt"))
        ) {
            return readFunction.apply(bufferedReader);
        }
    }
}
