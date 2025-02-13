package crudGerenciamentoDeRecursos.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crudGerenciamentoDeRecursos.crud.model.Employee;
import crudGerenciamentoDeRecursos.crud.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, Employee employee) {
        Employee existingEmployee = findById(id); // Verifica se existe antes de atualizar

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setCompany(employee.getCompany());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setCpf(employee.getCpf());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setDescription(employee.getDescription());

        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void delete(Long id) {
        Employee existingEmployee = findById(id); // Verifica se existe antes de deletar
        employeeRepository.delete(existingEmployee);
    }
}
