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
                    "2. Показать список покупок\n3. Удалить товар\n" +
                    "4. Поиск\n5. Выход");

            String operationNumber = scanner.nextLine();

            switch (operationNumber) {
                case "1" -> {
                    System.out.println("Какую покупку хотите добавить?");
                    String purchase = scanner.nextLine();
                    if (addProduct(purchase, shoppingList)) {
                        System.out.println("Итого в списке покупок: " + shoppingList.size());
                    }
                }
                case "2" -> {
                    System.out.println("Список покупок:");
                    System.out.println(shoppingListToString(shoppingList));
                }
                case "3" -> {
                    if (shoppingList.isEmpty()) {
                        System.out.println("shoppingList is empty");
                        break;
                    }
                    System.out.print("Список покупок:\n"+ shoppingListToString(shoppingList));
                    System.out.println("Какую хотите удалить? Введите номер или название");
                    String itemToDelete = scanner.nextLine();

                    try {
                        int itemNumber = Integer.parseUnsignedInt(itemToDelete);
                        if (deleteProduct(itemNumber - 1, shoppingList)) {
                            System.out.println("Покупка №" + itemNumber + " удалена, список покупок:");
                        } else {
                            System.out.println("нет такого товара");
                        }
                    } catch (NumberFormatException e) {

                        if (deleteProduct(itemToDelete, shoppingList)) {
                            System.out.println("Покупка \"" + itemToDelete + "\" удалена, список покупок:");
                        } else {
                            System.out.println("нет такого товара");
                        }
                    }
                }
                case "4" -> {
                    System.out.println("Введите текст для поиска:");
                    String query = scanner.nextLine();
                    List<String> foundItems = searchProduct(query, shoppingList);
                    System.out.println("Найдено:\n" + shoppingListToString(foundItems));
                }
                case "5" -> {
                    System.out.println("Bye bye");
                    return;
                }
                default -> System.out.println("Incorrect input");
            }
        }
    }
    public static boolean deleteProduct(String item, List<String> shoppingList) {
        for (Iterator<String> iterator = shoppingList.iterator(); iterator.hasNext(); ) {
            if (item.trim().equalsIgnoreCase(iterator.next())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(int index, List<String> shoppingList) {
        if (0 <= index && index < shoppingList.size()) {
            shoppingList.remove(index);
            return true;
        }
        return false;
    }

    private static boolean addProduct(String product, List<String> shoppingList) {
        return shoppingList.add(product.trim());
    }

    private static List<String> searchProduct(String query, List<String> shoppingList) {
        List<String> foundProducts = new ArrayList<>();

        if (shoppingList.isEmpty()) {
            return foundProducts;
        } else {
            String queryLower = query.toLowerCase();
            for (String item : shoppingList) {
                String itemLower = item.toLowerCase();
                if (itemLower.contains(queryLower)) {
                    foundProducts.add(item);
                }
            }
        }
        return foundProducts;
    }

    private static String shoppingListToString(List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append(".\n");
        }
        return sb.toString();
    }
}