package HW10;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class Homework10 {
    public static void main(String[] args) {
        Homework10 homework10 = new Homework10();

        homework10.readNumbers();

        homework10.createJson();

        homework10.countWords();
    }
    //countWords()
    public void countWords() {
        try {
            Map<String, Long> wordCountMap = Files.lines(Path.of("src/repository/words.txt"))
                    .parallel()
                    .flatMap(line -> Arrays.stream(line.trim().split(" ")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").trim())
                    .filter(word -> word.length() > 0)
                    .map(word -> new AbstractMap.SimpleEntry<>(word, 1))
                    .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));
            System.out.println("\n\n\n");
            wordCountMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Піймали Exception");
            e.printStackTrace();
        }
    }

//createJson()
    public static String nameFirstColum = "";
    public static String nameSecondColum = "";
    public void createJson() {
        try {
            InputStream inputStream = new FileInputStream("src/repository/file_2.txt");
            BufferedReader samBR = new BufferedReader(new InputStreamReader(inputStream));
            String line = samBR.readLine();
            String[] masFirstString = line.split(" ");
            nameFirstColum = masFirstString[0];
            nameSecondColum = masFirstString[1];
            List<User> users = new ArrayList<User>() {
                @Override
                public String toString() {
                    String rez = "[";
                    for(int i=0; i<this.size(); i++) {
                        if (i != this.size()-1) {
                            rez += this.get(i) + ",";
                        } else {
                            rez += this.get(i);
                        }
                    }
                    return (rez + " \n]");
                }
            };
            FileWriter fileWriter = new FileWriter("src/repository/file_2.json");

            line = samBR.readLine();
            while (line != null) {
                String[] masLine = line.split(" ");
                User newUser = new User(masLine[0], Integer.parseInt(masLine[1]));
                users.add(newUser);

                line = samBR.readLine();
            }
            fileWriter.write(users.toString());
            fileWriter.close();
            System.out.println(users);

        } catch (Exception e) {
            System.out.println("Піймали Exception");
            e.printStackTrace();
        }

    }
    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "\n{\n" +
                    "\"" + Homework10.nameFirstColum + "\":" + "\"" + name +  "\""+"," + "\n" +
                    "\"" + Homework10.nameSecondColum + "\":" + age +
                    "\n}";
        }
    }

//readNumbers()
    public void readNumbers() {
        try {
            InputStream inputStream = new FileInputStream("src/repository/file_1.txt");
            BufferedReader samBR = new BufferedReader(new InputStreamReader(inputStream));
            String line = samBR.readLine();

            while (line != null) {

                //Припускаємо, що "валідний" номер телефону – це
                // Рядок в одному з двох форматів: (xxx) xxx-xxxx або xxx-xxx-xxxx
                // (х означає цифру).

                Pattern pattern_1 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
                Matcher matcher_1 = pattern_1.matcher(line);
                Pattern pattern_2 = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");
                Matcher matcher_2 = pattern_2.matcher(line);

                while (matcher_1.find() || matcher_2.find()){
                    System.out.println(line);
                }
                line = samBR.readLine();
            }
        } catch (Exception e) {
            System.out.println("Піймали Exception: ");
            e.printStackTrace();
        }
    }
}

