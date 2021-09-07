package ru.mrchuvyzgalov;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Statistic implements Iterable<Map.Entry<String, Integer>> {
    private List<Map.Entry<String, Integer>> data;

    public Statistic(@NotNull List<String> words) {
        Map<String, Integer> mapWordCount = new HashMap<>();

        words
                .stream()
                .map(word -> word.toUpperCase(Locale.ROOT))
                .forEach(word -> {
                    if (mapWordCount.containsKey(word)) {
                        mapWordCount.put(word, mapWordCount.get(word) + 1);
                    }
                    else {
                        mapWordCount.put(word, 1);
                    }
                });

        data = mapWordCount.entrySet().stream().sorted((entry1, entry2) -> {
            Integer res = Integer.compare(entry2.getValue(), entry1.getValue());

            if (res == 0) {
                return entry1.getKey().compareTo(entry2.getKey());
            } else {
                return res;
            }
        }).collect(Collectors.toList());
    }

    public List<Map.Entry<String, Integer>> getData() {
        return data;
    }

    public int getCountOfWords() {
        return data.size();
    }

    @Override
    public Iterator<Map.Entry<String, Integer>> iterator() {
        return new StatisticIterator();
    }

    public class StatisticIterator implements Iterator<Map.Entry<String, Integer>> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < data.size();
        }

        @Override
        public Map.Entry<String, Integer> next() {
            if (hasNext()) {
                return data.get(position++);
            }
            else {
                return null;
            }
        }
    }
}
