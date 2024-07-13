import java.util.*;

public class Book {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Set<String>> Book = new HashMap<>();

        System.out.println(
                "Для добавления данных введите имя и номер телефона через пробел\nДля просмотра данных введите 2\nДля удаления данных введите 3\nДля выхода введите 4");

        while (true) {
            String input = scan.nextLine();
            if (input.equals("4")) {
                break;
            } else if (input.equals("2")) {
                for (Map.Entry<String, Set<String>> entry : Book.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else if (input.equals("3")) {
                System.out.println("Введите имя, данные которого нужно удалить:");
                String NameDelete = scan.nextLine();
                Book.remove(NameDelete);
                System.out.println("Данные удалены.");
            } else {
                String[] DataBook = input.split(" ");
                if (DataBook.length != 2) {
                    System.out.println("Данные введены некорректно. \nПовторите ввод через пробел.");
                    continue;
                }

                String Name = DataBook[0];
                String Phone = DataBook[1];

                Book.computeIfAbsent(Name, k -> new HashSet<>()).add(Phone);

            }
        }
        Map<String, Set<String>> consolPhoneBook = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : Book.entrySet()) {
            String Name = entry.getKey();
            Set<String> phones = entry.getValue();
            consolPhoneBook.computeIfAbsent(Name, k -> new HashSet<>()).addAll(phones);
        }

        List<Map.Entry<String, Set<String>>> Sorting = new ArrayList<>(consolPhoneBook.entrySet());
        Sorting.sort((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()));

        for (Map.Entry<String, Set<String>> entry : Sorting) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        scan.close();
    }
}
