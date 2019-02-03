import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

@RunWith(SpringRunner.class)
public class SequentialWordCounter {

    int[] integers = IntStream.rangeClosed(1, 5000).toArray();
    String sentence = "Very long sentence    and other   words";

    @Test
    public void iterative_word_counter() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        long start = System.nanoTime();

        char[] charArray = sentence.toCharArray();
        int counter = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (i == 0 && !Character.isWhitespace(charArray[i])) {
                counter++;
            } else if (!Character.isWhitespace(charArray[i]) && Character.isWhitespace(charArray[i - 1])) {
                counter++;
            }

        }

        out.println("Word number: " + counter);

        long duration = (System.nanoTime() - start);
        out.println("Duration: " + duration + " ns");
    }

    @Test
    public void stream_word_counter() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        long start = System.nanoTime();

        Stream<Character> sentenceStream = IntStream.range(0, sentence.length()).mapToObj(i -> sentence.charAt(i));

        WordCounter count = sentenceStream.reduce(
                new WordCounter(),
                WordCounter::accumulate,
                WordCounter::combine);

        out.println("Word number: " + count.counter);

        long duration = (System.nanoTime() - start);
        out.println("Duration: " + duration + " ns");
    }

    private class WordCounter {

        private int counter;
        private Character lastCharacter;

        public WordCounter() {
            lastCharacter = null;
            counter = 0;
        }

        public WordCounter(Character lastCharacter, int counter) {
            this.lastCharacter = lastCharacter;
            this.counter = counter;
        }

        WordCounter accumulate(Character character) {

            if (lastCharacter == null && !Character.isWhitespace(character)) {
                counter++;
            } else if (Character.isWhitespace(lastCharacter) && !Character.isWhitespace(character)) {
                counter++;
            }
            lastCharacter = character;
            return this;
        }

        WordCounter combine(WordCounter wordCounter) {

            return new WordCounter(wordCounter.getLastCharacter(), wordCounter.getCounter());
        }

        public Character getLastCharacter() {
            return lastCharacter;
        }

        public int getCounter() {
            return counter;
        }
    }

    @Test
    public void split_iterator_parallel_stream_word_counter() {

        out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        long start = System.nanoTime();

        Stream<Character> sentenceStream = IntStream.range(0, sentence.length()).mapToObj(i -> sentence.charAt(i));

        WordCounter count = sentenceStream.reduce(
                new WordCounter(),
                WordCounter::accumulate,
                WordCounter::combine);

        out.println("Word number: " + count.counter);

        long duration = (System.nanoTime() - start);
        out.println("Duration: " + duration + " ns");
    }
}
