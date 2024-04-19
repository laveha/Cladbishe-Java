package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.AbstractMap;

public class Main {

    private static Map<Integer, String> graves;
    private static int totalGraves;
    private static final Map<String, Integer> inventory;
    private static final Map<String, Integer> prices;

    private List<String> employees;
    private List<Map.Entry<String, Integer>> ritualServices;

    static {
        graves = new HashMap<>();
        inventory = new HashMap<>();
        prices = new HashMap<>();

        inventory.put("Лопата", 10);
        inventory.put("Пила", 15);
        inventory.put("Грабли", 5);

        prices.put("Обычное место в земле", 500);
        prices.put("Маузолей", 1000);
    }

    public Main() {
        employees = new ArrayList<>();
        ritualServices = new ArrayList<>();

        initializeEmployees();
        initializeRitualServices();

        totalGraves = 300;
    }

    // Статические функции
    public static void addGrave(int graveNumber, String occupant) {
        graves.put(graveNumber, occupant);
        totalGraves++;
    }

    public static int getInventoryCount(String item) {
        return inventory.getOrDefault(item, 0);
    }

    public static int getPrice(String placeType) {
        return prices.getOrDefault(placeType, 0);
    }

    public void displayInventory() {
        System.out.println("Инвентарь:");
        for (Map.Entry<String, Integer> item : inventory.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue());
        }
    }

    private void initializeEmployees() {
        employees.add("Даня Филимонов");
        employees.add("Игорь Ковыков");
    }

    private void initializeRitualServices() {
        ritualServices = new ArrayList<>();

        Map.Entry<String, Integer> service1 = new AbstractMap.SimpleEntry<>("Кремация", 2500);
        Map.Entry<String, Integer> service2 = new AbstractMap.SimpleEntry<>("Надгробные памятники", 2000);
        Map.Entry<String, Integer> service3 = new AbstractMap.SimpleEntry<>("Оформление места захоронения", 5000);
        ritualServices.add(service1);
        ritualServices.add(service2);
        ritualServices.add(service3);
    }

    public static void addGraves(int count) {
        totalGraves += count;
    }

    public static void main(String[] args) {
        Main graveyard = new Main();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Изначальное меню:");
            System.out.println("1. Просмотр количества мест на кладбище");
            System.out.println("2. Просмотр списка сотрудников");
            System.out.println("3. Редактирование списка сотрудников");
            System.out.println("4. Просмотр списка ритуальных услуг");
            System.out.println("5. Редактирование списка ритуальных услуг");
            System.out.println("6. Цены мест");
            System.out.println("7. Просмотр инвентаря");
            System.out.println("8. Добавление мест на кладбище");
            System.out.println("9. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Общее количество мест на кладбище: " + totalGraves);
                    break;
                case 2:
                    System.out.println("Список сотрудников:");
                    for (String employee : graveyard.employees) {
                        System.out.println(employee);
                    }
                    break;
                case 3:
                    System.out.println("Редактирование списка сотрудников:");
                    System.out.println("1. Добавить сотрудника");
                    System.out.println("2. Удалить сотрудника");
                    int editChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (editChoice) {
                        case 1:
                            System.out.print("Введите имя нового сотрудника: ");
                            String newEmployee = scanner.nextLine();
                            graveyard.employees.add(newEmployee);
                            System.out.println("Сотрудник успешно добавлен.");
                            break;
                        case 2:
                            System.out.print("Введите имя сотрудника для удаления: ");
                            String removeEmployee = scanner.nextLine();
                            graveyard.employees.remove(removeEmployee);
                            System.out.println("Сотрудник успешно удален.");
                            break;
                        default:
                            System.out.println("Неверный выбор.");
                    }
                    break;
                case 4:
                    System.out.println("Список ритуальных услуг:");
                    for (Map.Entry<String, Integer> service : graveyard.ritualServices) {
                        System.out.println(service.getKey() + " - " + service.getValue());
                    }
                    break;
                case 5:
                    System.out.println("Редактирование списка ритуальных услуг:");
                    System.out.println("1. Добавить ритуальную услугу");
                    System.out.println("2. Удалить ритуальную услугу");
                    int editServiceChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (editServiceChoice) {
                        case 1:
                            System.out.print("Введите название новой ритуальной услуги: ");
                            String newServiceName = scanner.nextLine();
                            System.out.print("Введите стоимость новой ритуальной услуги: ");
                            int newServicePrice = scanner.nextInt();
                            graveyard.ritualServices.add(new AbstractMap.SimpleEntry<>(newServiceName, newServicePrice));
                            System.out.println("Ритуальная услуга успешно добавлена.");
                            break;
                        case 2:
                            System.out.print("Введите название ритуальной услуги для удаления: ");
                            String removeServiceName = scanner.nextLine();
                            for (Map.Entry<String, Integer> service : graveyard.ritualServices) {
                                if (service.getKey().equals(removeServiceName)) {
                                    graveyard.ritualServices.remove(service);
                                    System.out.println("Ритуальная услуга успешно удалена.");
                                    break;
                                }
                            }
                            break;
                        default:
                            System.out.println("Неверный выбор.");
                    }
                    break;
                case 6:
                    System.out.println("Цены:");
                    for (Map.Entry<String, Integer> price : prices.entrySet()) {
                        System.out.println(price.getKey() + " - " + price.getValue());
                    }
                    break;
                case 7:
                    graveyard.displayInventory();
                    break;
                case 8:
                    System.out.print("Введите количество добавляемых мест на кладбище: ");
                    int newGraves = scanner.nextInt();
                    addGraves(newGraves);
                    System.out.println("Количество мест на кладбище успешно обновлено.");
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }
}