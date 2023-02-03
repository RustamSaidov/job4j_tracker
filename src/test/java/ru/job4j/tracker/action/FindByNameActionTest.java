package ru.job4j.tracker.action;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.StubOutput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.store.MemTracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {

    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item1 = new Item("item");
        Item item2 = new Item("item");
        Item item3 = new Item("something");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        FindByNameAction fbn = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(String.valueOf("item"));

        fbn.execute(input, tracker);

        String ln = System.lineSeparator();
        List<Item> resultItems = new ArrayList<>();
        resultItems.add(item1);
        resultItems.add(item2);
        assertThat(out.toString(), is(item1.toString() + ln + item2.toString() + ln));
        assertThat(tracker.findByName("item"), is(resultItems));
    }
}