import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class SpliteratorUseTest {

    String sentence = "Very long sentence    and other   words";

    private class CharacterCounter {

        private int counter;

        public CharacterCounter(int counter) {
            this.counter = counter;
        }

        CharacterCounter accumulate(Character character) {

            if (!Character.isWhitespace(character)) {
                counter++;
            }
            return this;
        }

        CharacterCounter combine(CharacterCounter characterCounter) {

            return new CharacterCounter(characterCounter.counter);
        }
    }


    class CharacterIterator implements Spliterator<Character> {

        private static final int TRESHOLD = 5;
        private String sentence;
        private int currentChar;

        public CharacterIterator(String sentence) {

            this.sentence = sentence;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) { // consumer performs action on given argument

            action.accept(sentence.charAt(currentChar++)); // consume current character
            return currentChar < sentence.length(); // Return true if there are further characters to be consumed
        }

        @Override
        public Spliterator<Character> trySplit() {

            int currentSize = sentence.length() - currentChar;
            if (currentSize < TRESHOLD) { // return null to signal if "object" is small enough to proceed sequentially
                return null;
            }

            for (int splitPositionCandidate = currentSize / 2 + currentChar;
                 splitPositionCandidate < currentSize;
                 splitPositionCandidate++) {

                if (Character.isWhitespace(sentence.charAt(splitPositionCandidate))) {

                    Spliterator<Character> wordCounterSpilterator
                            = new CharacterIterator(sentence.substring(currentChar, splitPositionCandidate));

                    currentChar = splitPositionCandidate;
                    return wordCounterSpilterator;
                }
            }

            return null;
        }

        @Override
        public long estimateSize() {
            return sentence.length() - currentChar;
        }


        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }


    @Test
    public void spliterator_use_as_parallel_iterator() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        long start = System.nanoTime();

        Spliterator<Character> wordCounterSpliterator = new CharacterIterator(sentence);
        Stream<Character> sentenceStream = StreamSupport.stream(wordCounterSpliterator, true);

        CharacterCounter count = sentenceStream.reduce(
                new CharacterCounter(0),
                CharacterCounter::accumulate,
                CharacterCounter::combine);

        out.println("Word number: " + count.counter);

        long duration = (System.nanoTime() - start);
        out.println("Duration: " + duration + " ns");
    }
}
