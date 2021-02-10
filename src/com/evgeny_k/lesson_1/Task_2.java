package com.evgeny_k.lesson_1;

import java.util.Arrays;
import java.util.List;

public class Task_2 {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        System.out.println(asList(array));
    }

    private static <T> List<T> asList(T[] array) {
        return Arrays.asList(array);
    }
}
