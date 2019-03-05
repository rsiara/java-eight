import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;

@RunWith(SpringRunner.class)
public class TestingLambdasTest {

    /*Sometime you may have access to a lambda via a field so you can reuse it, and you’d really like to
    test the logic encapsulated in that lambda. What can you do? You could test the lambda just like
    when calling methods. For example, let’s say you add a static field compareByXAndThenY in the
    Point class that gives you access to a Comparator object that’s generated from method
    references:*/

    static class Point {

        public final static Comparator<Point> pointComparator =
                Comparator.comparing(Point::getX).thenComparing(Point::getX);

        final int x;
        final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }

        public Point moveRightBy(int x){
            return new Point(this.x + x, this.y);
        }
    }

    @Test
    public void test_lambda() {

        Point p1 = new Point(10, 5);
        Point p2 = new Point(5, 5);

        int result = Point.pointComparator.compare(p1, p2);

        Assert.assertEquals(1, result);
    }
}