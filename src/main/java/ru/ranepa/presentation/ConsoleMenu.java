package ru.ranepa.presentation;

import ru.ranepa.model.Employee;
import ru.ranepa.service.HrmService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final HrmService hrmService;
    private final Scanner scanner;

    public ConsoleMenu(HrmService hrmService) {
        this.hrmService = hrmService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Выберите пункт меню: ");

            switch (choice) {
                case 1:
                    showAllEmployees();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    findEmployeeById();
                    break;
                case 5:
                    showStatistics();
                    break;
                case 6:
                    System.out.println("Выход из программы...");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный пункт меню. Попробуйте снова.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== HRM System Menu ===");
        System.out.println("1. Показать всех сотрудников");
        System.out.println("2. Добавить сотрудника");
        System.out.println("3. Удалить сотрудника по ID");
        System.out.println("4. Найти сотрудника по ID");
        System.out.println("5. Показать статистику");
        System.out.println("6. Выход");
    }

    private void showAllEmployees() {
        List<Employee> employees = hrmService.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("Список сотрудников пуст.");
            return;
        }

        System.out.println("\nСписок сотрудников:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void addEmployee() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите должность: ");
        String position = scanner.nextLine();

        double salary = readDouble("Введите зарплату: ");

        LocalDate hireDate = readDate("Введите дату приема на работу (например, 2026-04-10): ");

        Employee employee = hrmService.addEmployee(name, position, salary, hireDate);
        System.out.println("Сотрудник успешно добавлен: " + employee);
    }

    private void deleteEmployee() {
        Long id = readLong("Введите ID сотрудника для удаления: ");
        boolean deleted = hrmService.deleteEmployee(id);

        if (deleted) {
            System.out.println("Сотрудник удален.");
        } else {
            System.out.println("Сотрудник с таким ID не найден.");
        }
    }

    private void findEmployeeById() {
        Long id = readLong("Введите ID сотрудника: ");
        Employee employee = hrmService.getEmployeeById(id);

        if (employee != null) {
            System.out.println("Найден сотрудник:");
            System.out.println(employee);
        } else {
            System.out.println("Сотрудник с таким ID не найден.");
        }
    }

    private void showStatistics() {
        double averageSalary = hrmService.calculateAverageSalary();
        Employee topPaidEmployee = hrmService.findTopPaidEmployee();

        System.out.println("Средняя зарплата: " + averageSalary);

        if (topPaidEmployee != null) {
            System.out.println("Самый высокооплачиваемый сотрудник:");
            System.out.println(topPaidEmployee);
        } else {
            System.out.println("Сотрудников пока нет.");
        }
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести целое число.");
            }
        }
    }

    private Long readLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число формата Long.");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число, например 1500.0");
            }
        }
    }

    private LocalDate readDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: дата должна быть в формате ГГГГ-ММ-ДД, например 2026-04-10");
            }
        }
    }
}
