package ru.ranepa;

import ru.ranepa.presentation.ConsoleMenu;
import ru.ranepa.repository.EmployeeRepository;
import ru.ranepa.service.HrmService;

public class HrmApplication {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        HrmService hrmService = new HrmService(employeeRepository);
        ConsoleMenu consoleMenu = new ConsoleMenu(hrmService);

        consoleMenu.start();
    }
}
