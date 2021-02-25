package com.evgeny_k.lesson_6;

import java.util.Arrays;

public class Task_1 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};

        System.out.println("Исходный массив: " + Arrays.toString(array));
        System.out.println("После обработки: " + Arrays.toString(arrayAfterFour(array)));
    }

    public static int[] arrayAfterFour(int[] ints) {
        for (int i = ints.length - 1; i >= 0; i--) {
            if (ints[i] == 4) {
                int newLength = ints.length - i - 1;
                int[] out = new int[newLength];
                System.arraycopy(ints, (i + 1), out, 0, newLength);
                return out;
            }
        }
        throw new RuntimeException("В массиве нет ни одной 4-ки");
    }

}