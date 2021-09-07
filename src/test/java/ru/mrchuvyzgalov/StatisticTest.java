package ru.mrchuvyzgalov;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StatisticTest {

    @Test
    public void testEmptyListSize() throws Exception {
        int result = new Statistic(new ArrayList<>()).getCountOfWords();

        assertEquals(0, result);
    }

    @Test
    public void testDifferentWordsListSize() throws Exception {
        int result = new Statistic(List.of("B", "A", "C")).getCountOfWords();

        assertEquals(3, result);
    }

    @Test
    public void testDifferentWordsList() throws Exception {
        List<Map.Entry<String, Integer>> result = new Statistic(List.of("B", "A", "C")).getData();

        List<Map.Entry<String, Integer>> expected = new ArrayList<>();
        expected.add(new AbstractMap.SimpleEntry<>("A", 1));
        expected.add(new AbstractMap.SimpleEntry<>("B", 1));
        expected.add(new AbstractMap.SimpleEntry<>("C", 1));

        assertEquals(expected, result);
    }

    @Test
    public void testEqualsWordsListSize() throws Exception {
        int result = new Statistic(List.of("A", "A", "A")).getCountOfWords();

        assertEquals(1, result);
    }

    @Test
    public void testEqualsWordsList() throws Exception {
        List<Map.Entry<String, Integer>> result = new Statistic(List.of("A", "A", "A")).getData();

        List<Map.Entry<String, Integer>> expected = new ArrayList<>();
        expected.add(new AbstractMap.SimpleEntry<>("A", 3));

        assertEquals(expected, result);
    }

    @Test
    public void testTypicalWordsListSize() throws Exception {
        int result = new Statistic(List.of("B", "A", "B", "C", "A")).getCountOfWords();

        assertEquals(3, result);
    }

    @Test
    public void testTypicalWordsList() throws Exception {
        List<Map.Entry<String, Integer>> result = new Statistic(List.of("B", "A", "B", "C", "A")).getData();

        List<Map.Entry<String, Integer>> expected = new ArrayList<>();
        expected.add(new AbstractMap.SimpleEntry<>("A", 2));
        expected.add(new AbstractMap.SimpleEntry<>("B", 2));
        expected.add(new AbstractMap.SimpleEntry<>("C", 1));

        assertEquals(expected, result);
    }
}
