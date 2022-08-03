package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

import java.util.Random;
import java.util.UUID;

public class Create1MlnAction implements UserAction {

    private final Output out;

    public Create1MlnAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Create 1 million of new random Items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        for (int i = 0; i < 1000000; i++) {
            String name = UUID.randomUUID().toString();
            Item item = new Item(name);
            tracker.add(item);
        }
        out.println("Items successfully added!");
        return true;
    }
}
