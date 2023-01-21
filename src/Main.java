import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> shoppingList = new ArrayList<>();

        while (true) {
            System.out.println("Выберите операцию:\n1. Добавить товар\n" +
                            "2. Показать список покупок\n3. Удалить товар\n4. Выход");

            String operationNumber = scanner.nextLine();

            switch (operationNumber) {
                case "1" -> {
                    System.out.println("Какую покупку хотите добавить?");
                    String purchase = scanner.nextLine();
                    shoppingList.add(purchase);
                    System.out.println("Итого в списке покупок: " + shoppingList.size());
                }
                case "2" -> {
                    System.out.println("Список покупок:");
                    System.out.println(shoppingListToString(shoppingList));
                }
                case "3" -> {
                    if (shoppingList.isEmpty()) {
                        System.out.println(shoppingListToString(shoppingList));
                        break;
                    }

                    System.out.println("Список покупок:");
                    System.out.println(shoppingListToString(shoppingList));
                    System.out.println("Какую хотите удалить? Введите номер или название");

                    String itemToDelete = scanner.nextLine();

                    try {
                        int itemAsNumber = Integer.parseUnsignedInt(itemToDelete);

                        if (itemAsNumber <= shoppingList.size()) {
                            String deletedItem = shoppingList.get(itemAsNumber -1);
                            shoppingList.remove(itemAsNumber - 1);
                            System.out.println("Покупка \"" + deletedItem  +"\" удалена, список покупок:");
                            System.out.println(shoppingListToString(shoppingList));
                        } else {
                            System.out.println("Such item absents");
                        }
                    } catch (NumberFormatException e) {
                        boolean wasDeleted = false;
                        for (Iterator<String> iterator = shoppingList.iterator(); iterator.hasNext();) {
                            String nextItem = iterator.next();
                            if (itemToDelete.trim().equalsIgnoreCase(nextItem)) {
                                iterator.remove();
                                System.out.println("Покупка \"" + nextItem  +"\" удалена, список покупок:");
                                System.out.println(shoppingListToString(shoppingList));
                                wasDeleted = true;
                                break;
                            }
                        }
                        if (!wasDeleted) {System.out.println("Such item absents");}
                    }
                }
                case "4" -> {
                    System.out.println("Bye bye");
                    return;
                }
                default -> System.out.println("Incorrect input");
            }
        }
    }

    private static String shoppingListToString(List<String> list) {
        if (list.isEmpty()) {
            return "Your shopping list is empty";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append(".\n");
        }
        return sb.toString();
    }
}