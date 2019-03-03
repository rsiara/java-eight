import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ObserverWithoutLambda {

    interface Observer {

        void notify(String tweet);
    }

    class Guardian implements Observer{

        @Override
        public void notify(String tweet) {
            System.out.println("Guardian retweet: " + tweet);
        }
    }

    class NYTimes implements Observer {

        @Override
        public void notify(String tweet) {
            System.out.println("NY Times retweet: " + tweet);
        }
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

            for(Observer observer : observers){
                observer.notify(tweet);
            }
        }
    }

    @Test
    public void observer_usage_without_lambda() {

        PressAgency papAgency = new PressAgency();

        papAgency.registerObserver(new Guardian());
        papAgency.registerObserver(new NYTimes());

        papAgency.notifyObservers("Salary rice for developers");
    }
}