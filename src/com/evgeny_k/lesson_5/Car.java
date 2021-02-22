package com.evgeny_k.lesson_5;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private final Race race;
    private final int speed;
    private final String name;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        CARS_COUNT++;
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + CARS_COUNT;

    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));

            // уменьшаем счетчик "защелки" готовности
            MainClass.startLine.countDown();
            System.out.println(this.name + " готов");

            // ждем общего старта
            MainClass.roadStage.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // проходим все этапы
        for (Stage stage: race.getStages()){
            stage.go(this);
        }
    }
}