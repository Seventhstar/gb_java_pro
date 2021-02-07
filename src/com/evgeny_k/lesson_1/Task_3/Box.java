package com.evgeny_k.lesson_1.Task_3;

import java.util.ArrayList;
import java.util.List;

class Box<T extends Fruit> {
    private List<T> fruitsList;
    private String name;

    public Box(String name) {
        fruitsList = new ArrayList<>();
        this.name = name;
    }

    public List<T> getFruitsList() {
        return fruitsList;
    }

    void add(T obj) {
        fruitsList.add(obj);
    }

    void moveTo(Box<T> box) {
        box.getFruitsList().addAll(fruitsList);
        fruitsList.clear();
    }

    @Override
    public String toString() {
        if (fruitsList.isEmpty())
            return "Коробка `" + name + "` пуста";
        return "`" + name + "` - " + getWeight() + "кг. " + fruitsList.get(0).getNameForWeight() + " (" + fruitsList.size() + " шт.)";
    }

    float getWeight() {
        if (fruitsList.isEmpty()) {
            return 0;
        } else {
            return fruitsList.size() * fruitsList.get(0).getWeight();
        }
    }

    boolean compare(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }


    public String getName() {
        return name;
    }
}
