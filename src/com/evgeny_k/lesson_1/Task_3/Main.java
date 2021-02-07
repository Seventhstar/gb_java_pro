package com.evgeny_k.lesson_1.Task_3;

public class Main {
    public static void main(String[] args) {

        Box<Orange> orangeBox1 = new Box<>("Коробка 1");
        Box<Orange> orangeBox2 = new Box<>( "Коробка 2");

        Box<Apple> appleBox1 = new Box<>("Коробка 3");
        Box<Apple> appleBox2 = new Box<>("Коробка 4");

        for (int i = 0; i < 4; i++) {
            orangeBox1.add(new Orange());
            appleBox1.add(new Apple());
            appleBox2.add(new Apple());
        }

        for (int i = 0; i < 6; i++) {
            orangeBox2.add(new Orange());
        }

        appleBox2.add(new Apple());

        System.out.println(orangeBox1.toString());
        System.out.println(orangeBox2.toString());
        System.out.println(appleBox1.toString());
        System.out.println(appleBox2.toString());

        System.out.println("Пересыпаем из `" + appleBox1.getName() + "` в `" + appleBox2.getName()+"`");
        appleBox1.moveTo(appleBox2);

        // компилятор такое не пропустит - все ок
        // System.out.println("Пересыпаем из коробки `" + orangeBox2.getName() + "` в коробку `" + appleBox1.getName()+"`");
        // orangeBox2.moveTo(appleBox1);

        System.out.println(orangeBox1.toString());
        System.out.println(orangeBox2.toString());
        System.out.println(appleBox1.toString());
        System.out.println(appleBox2.toString());

        System.out.println("Одинаковы ли коробки "+orangeBox2.toString() + " и " + appleBox2.toString() + " ответ: "+ orangeBox2.compare(appleBox2));
        System.out.println("Одинаковы ли коробки "+orangeBox2.toString() + " и " + orangeBox1.toString() + " ответ: "+ orangeBox2.compare(orangeBox1));

    }
}
