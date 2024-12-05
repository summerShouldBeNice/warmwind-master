package top.warmwind.master.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author warmwind
 * @since 2024-11-29 上午9:23
 */
public class TestMain {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Apple {
        private int weight;
        private String color;
    }

    public List<Apple> initApples() {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(10, "red"));
        apples.add(new Apple(12, "red"));
        apples.add(new Apple(15, "green"));
        apples.add(new Apple(20, "green"));
        return apples;
    }

    public List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @FunctionalInterface
    public interface AcceptApple<T> {
        String accept(T t);
    }


    public void printApples(List<Apple> apples, AcceptApple<Apple> acceptApple) {
        for (Apple apple : apples) {
            System.out.println(acceptApple.accept(apple));
        }
    }


    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        List<Apple> apples = testMain.initApples();
        List<Apple> filterApples = testMain.filterApples(apples, apple -> apple.getWeight() > 10);
        testMain.printApples(filterApples, apple -> apple.getColor());
    }
}
