package ru.job4j.tracker.action;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.StubOutput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.store.MemTracker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("some item");
        tracker.add(item);
        FindByIdAction fbi = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(String.valueOf(0));

        fbi.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is(item.toString() + ln));
        assertThat(tracker.findAll().get(0).getName(), is(item.getName()));
    }

    @Test
    public void executeFail() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("some item");
        tracker.add(item);
        FindByIdAction fbi = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(String.valueOf(1));

        fbi.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Wrong id! Not found" + ln));
    }
}