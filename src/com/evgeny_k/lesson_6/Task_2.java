package com.evgeny_k.lesson_6;

import java.util.Arrays;

public class Task_2 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};

        System.out.println("Исходный массив: " + Arrays.toString(array));
        System.out.println("Результат поиска: " + find4Or1(array));
    }

    public static boolean find4Or1(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == 4 || ints[i] == 1) {
                return true;
            }
        }
        return false;
    }

}