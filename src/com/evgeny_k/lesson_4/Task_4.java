package com.evgeny_k.lesson_4;

public class Task_4 {
    private final Object monitor = new Object();
    private volatile int counter = 0;

    public static void main(String[] args) {
        Task_4 task_4 = new Task_4();

        Thread t1 = new Thread(() -> task_4.printChar('A', 0));
        Thread t2 = new Thread(() -> task_4.printChar('B', 1));
        Thread t3 = new Thread(() -> task_4.printChar('C', 2));
        t1.start();
        t2.start();
        t3.start();
    }

    public void printChar(char printChar, int index) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while ((counter % 3) != index) {
                        monitor.wait();
                    }
                    System.out.print(printChar);
                    counter++;
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
