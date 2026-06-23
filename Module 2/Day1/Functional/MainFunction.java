package functional;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MainFunction {
    public static void main(String[] args) {

        Function<String, Integer> d = (String s) -> s.length();
        System.out.println("Length = " + d.apply("Hello"));

        BiFunction<Integer, Integer, Integer> mf = (t, u) -> t + u;
        System.out.println("Sum = " + mf.apply(1, 2));

        Predicate<Integer> predicate = (i) -> {
            if (i % 2 == 0)
                return true;
            return false;
        };
        System.out.println("Is Even = " + predicate.test(10));

        UnaryOperator<String> unaryOperator = (s) -> s.toUpperCase();
        System.out.println("Upper Case = " +
                unaryOperator.apply("hello"));

        BinaryOperator<Integer> binaryOperator =
                (a, b) -> a * b;
        System.out.println("Product = " +
                binaryOperator.apply(10, 20));

        Consumer<String> consumer =
                (name) -> System.out.println("Hi " + name);
        consumer.accept("Apuroop");

        Supplier<String> supplier =
                () -> "Java Functional Interfaces";
        System.out.println("Supplier Value = " +
                supplier.get());
    }
}