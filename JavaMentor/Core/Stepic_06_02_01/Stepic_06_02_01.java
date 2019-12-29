package Stepic_06_02_01;

import java.util.HashSet;
import java.util.Set;

public class Stepic_06_02_01 {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        Set<Integer> set2 = new HashSet<>();
        set2.add(0);
        set2.add(1);
        set2.add(2);
        Set<Integer> set3 = symmetricDifference2(set1, set2);
        for (Integer i: set3) {
            System.out.println(i);
        }
    }


    public static <T> Set<T> symmetricDifferenceOld(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> union = new HashSet<>();
        Set<T> intersection = new HashSet<>();
        union.addAll(set1);
        union.addAll(set2);
        for (T value : set1) {
            if (set2.contains(value)) {
                intersection.add(value);
            }
        }
        union.removeAll(intersection);

        return union;
    }


    public static <T> Set<T> symmetricDifference1(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> s1 = new HashSet<>();
        Set<T> s2 = new HashSet<>();
        s1.addAll(set1);
        s2.addAll(set2);
        s1.removeAll(set2);
        s2.removeAll(set1);
        s1.addAll(s2);
        return s1;
    }



    public static <T> Set<T> symmetricDifference2(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> union = new HashSet<>();
        Set<T> intersection = new HashSet<>();
        union.addAll(set1);
        union.addAll(set2);
        intersection.addAll(set1);
        intersection.retainAll(set2);
        union.removeAll(intersection);
        return union;
    }
}


