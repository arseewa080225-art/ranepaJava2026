package ru.ranepa.repository;
import ru.ranepa.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository {
    private final Map<Long, Employee> employeeStorage = new HashMap<>();
    private Long currentId = 1L;

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(currentId++);
        }
        employeeStorage.put(employee.getId(), employee);
        return employee;
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employeeStorage.values());
    }

    public Employee findById(Long id) {
        return employeeStorage.get(id);
    }

    public boolean delete(Long id) {
        return employeeStorage.remove(id) != null;
    }
}