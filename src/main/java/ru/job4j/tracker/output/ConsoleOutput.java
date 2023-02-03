package ru.job4j.tracker.output;

public class ConsoleOutput implements Output {
    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void print(Object object) {

    }


}
