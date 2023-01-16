package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

import java.util.UUID;

public class Create100KAction implements UserAction {

    private final Output out;

    public Create100KAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Create 100 000 of new random Items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        for (int i = 0; i < 100000; i++) {
            String name = UUID.randomUUID().toString();
            Item item = new Item(name);
            tracker.add(item);
        }
        out.println("Items successfully added!");
        return true;
    }
}
