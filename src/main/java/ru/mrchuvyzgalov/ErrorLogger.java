package ru.mrchuvyzgalov;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ErrorLogger {
    private final static String logFile = "errors.log";

    public static void logError(Exception exception) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(logFile), true))) {
            Date date = new Date();

            bw.write("Время: " + date +  ". Информация об ошибке: " + exception.getMessage());
            bw.newLine();

            System.out.println("Ошибка записана в log-файл");
        } catch (IOException e) {
            System.out.println("Ошибка записи ошибки в файл. Информация об ошибке: " + e.getMessage());
        }
    }
}
