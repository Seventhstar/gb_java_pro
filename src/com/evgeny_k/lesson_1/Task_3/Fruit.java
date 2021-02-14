package com.evgeny_k.lesson_1.Task_3;

abstract class Fruit {
    private float weight;
    private String nameForWeight;

    Fruit(String name, float weight) {
        this.weight = weight;
        this.nameForWeight = name;
    }

    public float getWeight() {
        return weight;
    }

    public String getNameForWeight() {
        return nameForWeight;
    }
}
