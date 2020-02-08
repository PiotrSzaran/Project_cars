package pl.szaran.service;

import pl.szaran.exceptions.MyException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDataService {

    private static Scanner scanner = new Scanner(System.in);

    public static SortType getSortType() {
        SortType[] sortTypes = SortType.values();
        AtomicInteger counter = new AtomicInteger(1);

        Arrays.stream(sortTypes).forEach(sortType -> System.out.println(counter.getAndAdd(1) + ". " + sortType));
        System.out.println("Wybierz numer kryterium sortowaina:");
        String text = scanner.nextLine();

        if (!text.matches("[1-" + sortTypes.length + "]")) {
            throw new MyException("Sort types option value is not correct");
        }
        System.out.println("Wybrano: " + sortTypes[Integer.parseInt(text) - 1]);

        return sortTypes[Integer.parseInt(text) - 1];
    }

    public static boolean getSortOrder() {
        System.out.println("1. ROSNĄCO\n2. MALEJĄCO\nWybierz porządek sortowania:");
        String text = scanner.nextLine();

        if (!text.matches("[1-2]")) {
            throw new MyException("Boolean value is not correct");
        }
        return text.matches("1");
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
}
