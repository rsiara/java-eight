import model.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
public class ConditionalDeferredExecution {

    Logger logger = Logger.getLogger(this.getClass().getName());

    // Warunkowa odroczona realizacja

    List<Developer> developers = Arrays.asList(
            new Developer("John", "Milovicz", 7800.00),
            new Developer("Mark", "Maklovicz", 4000.00),
            new Developer("Kent", "Packolo", 5900.00),
            new Developer("Mark", "Breckin", 5800.00),
            new Developer("Nick", "Carpatio", 9800.00),
            new Developer("Michael", "Lemaro", 3300.00),
            new Developer("Greg", "Mandolo", 6600.00),
            new Developer("Andrew", "Caravano", 1500.0)
    );


    @Test
    public void conditional_deferred_execution() {

        /* The state of the logger (what level it supports) is exposed in the client code through the method
        isLoggable. */

        /* Why should you have to query the state of the logger object every time before you can log a message?
        It just clutters your code. */

        //Before refactor
        if (logger.isLoggable(Level.FINER)){
            logger.finer("Problem: " + generateDiagnostic());
        }


        // A better alternative

        /* Unfortunately, there’s still an issue with this code. The logging
        message is always evaluated, even if the logger isn’t enabled for the message level passed as
        argument. */
        logger.log(Level.FINER, "Problem: " + generateDiagnostic());


        //After refactor

        logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
        /*The log method will internally execute the lambda passed as argument only if the logger is of the
        right level. The internal implementation of the log method is along the lines of this:*/
    }

    public void log(Level level, Supplier<String> msgSupplier){

        if (logger.isLoggable(Level.FINER)){
            logger.finer("Problem: " + msgSupplier.get());
        }
    }

    private String generateDiagnostic(){
        return "GENERATED DIAGNOSTIC DATA";
    }
}
