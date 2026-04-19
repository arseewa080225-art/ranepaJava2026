package ru.ranepa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class HrmServiceTest {

    private HrmService hrmService;

    @BeforeEach
    void setUp() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        hrmService = new HrmService(employeeRepository);
    }

    @Test
    void shouldCalculateAverageSalary() {
        hrmService.addEmployee("Ivan", "Developer", 100.0, LocalDate.of(2024, 1, 1));
        hrmService.addEmployee("Petr", "Manager", 200.0, LocalDate.of(2024, 1, 2));
        hrmService.addEmployee("Anna", "QA", 300.0, LocalDate.of(2024, 1, 3));

        double actual = hrmService.calculateAverageSalary();

        assertEquals(200.0, actual);
    }

    @Test
    void shouldFindTopPaidEmployee() {
        hrmService.addEmployee("Ivan", "Developer", 100.0, LocalDate.of(2024, 1, 1));
        hrmService.addEmployee("Anna", "Manager", 500.0, LocalDate.of(2024, 1, 2));

        Employee topEmployee = hrmService.findTopPaidEmployee();

        assertNotNull(topEmployee);
        assertEquals("Anna", topEmployee.getName());
        assertEquals(500.0, topEmployee.getSalary());
    }

    @Test
    void shouldReturnNullWhenNoEmployees() {
        Employee topEmployee = hrmService.findTopPaidEmployee();

        assertNull(topEmployee);
    }
}