package Stepic_06_04_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Stepic_06_04_03 {
    public static void main(String[] args) {
        new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8"))).lines()
                .limit(1)
                .map(s -> s.split("[^\\p{IsAlphabetic}0-9]"))
                .flatMap(Arrays::stream)
                .map(s -> s.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(11)
                .forEachOrdered(s -> System.out.println(s.getKey()));
    }
}
