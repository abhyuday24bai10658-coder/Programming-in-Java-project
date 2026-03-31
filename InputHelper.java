package utils;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputHelper {

    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Try again.");
            }
        }
    }

    public static String readString(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static LocalDateTime readDateTime(Scanner sc, String message) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            System.out.print(message + " (yyyy-MM-dd HH:mm): ");
            try {
                return LocalDateTime.parse(sc.nextLine(), fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time! Try again.");
            }
        }
    }
}
