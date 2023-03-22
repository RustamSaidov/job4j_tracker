package ru.job4j.tracker;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrackerHbmTest {

    @AfterEach
    public void clearItems() {
        try (var tracker = new HbmTracker()) {
            var items = tracker.findAll();
            for (var item : items) {
                tracker.delete(item.getId());
            }
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenAddNewItemThenReplaceItemThenGetUpdatedItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item newItem = new Item("test2");
            tracker.replace(item.getId(), newItem);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(newItem.getName()));
        }
    }

    @Test
    public void whenAddNewItemThenDeleteItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            System.out.println("ITEM: " + item);
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            System.out.println("RESULT: " + result);
            assertThat(result.getName(), is(item.getName()));
            boolean delFlag = tracker.delete(result.getId());
            System.out.println("DELFLAG: " + delFlag);
            Item resultAfterDeleting = tracker.findById(item.getId());
            assertNull(resultAfterDeleting);
        }
    }

    @Test
    public void whenAddSeveralNewItemsThenFindAll() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            Item item2 = new Item("test2");
            List<Item> list = new ArrayList<>();
            item1 = tracker.add(item1);
            item2 = tracker.add(item2);
            list.add(item1);
            list.add(item2);
            List<Item> resultList = tracker.findAll();
            assertEquals(resultList, list);
        }
    }

    @Test
    public void whenAddSeveralNewItemsThenFindByName() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            Item item2 = new Item("test2");
            List<Item> list = new ArrayList<>();
            item1 = tracker.add(item1);
            list.add(item1);
            tracker.add(item2);
            List<Item> resultList = tracker.findByName(item1.getName());
            assertEquals(resultList, list);
        }
    }
}