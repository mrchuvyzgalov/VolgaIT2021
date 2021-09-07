package ru.mrchuvyzgalov;

import java.io.*;
import java.util.*;

public class Main {
    public static void main( String[] args ) throws IOException {
        System.out.println("Волга-IT XXI" + System.lineSeparator());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь до файла: ");
        String path = scanner.nextLine();

        try {
            Statistic statistic = new Statistic(HtmlParser.parseHtmlFile(path));

            System.out.println(System.lineSeparator() + "Статистика:");
            System.out.println("Всего " + statistic.getCountOfWords() + " различных слов" + System.lineSeparator());

            for (var entity : statistic) {
                System.out.println(entity.getKey() + " - " + entity.getValue());
            }

            System.out.println(System.lineSeparator() + "Для сохранения статистики в базу данных MySQL введите необходимые данные");

            System.out.print("URL: ");
            String URL = scanner.nextLine();

            System.out.print("Username: ");
            String USERNAME = scanner.nextLine();

            System.out.print("Password: ");
            String PASSWORD = scanner.nextLine();

            StatisticDB statisticDB = new StatisticDB(URL, USERNAME, PASSWORD);
            statisticDB.saveStatistic(statistic);

            System.out.println(System.lineSeparator() + "Статистика была сохраненена в базу данных");
        } catch (Exception e) {
            ErrorLogger.logError(e);
        }
    }
}
