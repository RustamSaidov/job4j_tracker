package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;

import java.util.List;

public class DeleteAllAction implements UserAction {

    private final Output out;

    public DeleteAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Delete All items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        int l = tracker.findAll().size();
        for(int i=0; i< l; i++){
            tracker.delete(i);
        }
        return true;
    }
}
