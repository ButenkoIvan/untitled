package HW11;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] test3 = {"1, 2, 0", "4, 5"};
        List<String> input = List.of("Maks", "Anna", "Taras", "Petro", "Valera", "Mark", "Olga", "Zina");

        List<Integer> test4 = List.of(6, 7, 3, 4, 9, 0);
        List<Integer> test5 = List.of(6, 8, 2, 9);

        List<String> test1 = List.of("Maks", "Anna", "Taras", "Petro", "Valera", "Mark", "Olga", "Zina");
        List<String> test2 = List.of("11", "22", "33", "44", "55");

        Homework11 homeWork11 = new Homework11();
        System.out.println(homeWork11.getOddNames(input));

        System.out.println(homeWork11.getUpperSortedReverseNames(input));

        System.out.println(homeWork11.getParseIntStr(test3));

        System.out.println(homeWork11.task4(2L, 25214903917L, 11L, 2 ^ 48L).limit(10).collect(Collectors.toList()));

        System.out.println(Homework11.zip(test4.stream(), test5.stream()).collect(Collectors.toList()));
        System.out.println(Homework11.zip(test1.stream(), test2.stream()).collect(Collectors.toList()));
    }
}
