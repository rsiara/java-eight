import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ObserverUsingLambda {

    interface Observer {

        void notify(String tweet);
    }

    interface Subject {

        void registerObserver(Observer observer);

        void notifyObservers(String tweet);
    }

    class PressAgency implements Subject {

        List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer observer) {

            observers.add(observer);
        }

        @Override
        public void notifyObservers(String tweet) {

            for (Observer observer : observers) {
                observer.notify(tweet);
            }
        }
    }

    /*Should you use lambda expressions all the time? The answer is no! In the example we described,
    lambda expressions work great because the behavior to execute is simple, so they’re helpful to
    remove boilerplate code. But the observers may be more complex: they could have state, define
    several methods, and the like. In those situations, you should stick with classes.*/

    @Test
    public void observer_usage_without_lambda() {

        PressAgency papAgency = new PressAgency();

        papAgency.registerObserver((tweet) -> System.out.println("Guardian retweet: " + tweet));
        papAgency.registerObserver((tweet) -> System.out.println("NY Times retweet: " + tweet));

        papAgency.notifyObservers("Salary rice for developers");
    }
}