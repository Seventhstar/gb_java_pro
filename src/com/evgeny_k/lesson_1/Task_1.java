package com.evgeny_k.lesson_1;

import java.util.Arrays;

public class Task_1 {
    public static void main(String[] args) {
        String[] strArray = {"12312", "4h23t423", "fhwefhkwe", "weh", "fejjh"};
        System.out.println("До замены: " + Arrays.toString(strArray));
        replaceItemsInArray(strArray, 0, 2);
        System.out.println("После замены: " + Arrays.toString(strArray));
    }

    public static <T> void replaceItemsInArray(T[] array, int firstIndex, int secondIndex) {
        if (firstIndex >= array.length || secondIndex >= array.length) {
            System.out.println("Индесы за пределами массива, не могу поменять местами");
            return;
        }
        T tempItem = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tempItem;
    }
}
