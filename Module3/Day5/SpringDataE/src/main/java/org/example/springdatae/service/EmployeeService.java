package org.example.springdatae.service;

import org.example.springdatae.dto.EmployeeRequestDto;
import org.example.springdatae.dto.EmployeeResponseDTO;
import org.example.springdatae.model.Employee;
import org.example.springdatae.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDto requestDTO) {
        Employee employee = new Employee();
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail()); // saved to DB
        Employee saved = employeeRepository.save(employee);
        return EmployeeResponseDTO.fromEntity(saved);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
        return EmployeeResponseDTO.fromEntity(employee);
    }

    // UPDATE — accepts RequestDTO, returns ResponseDTO (email hidden)
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDto requestDTO) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
        existing.setName(requestDTO.getName());
        existing.setEmail(requestDTO.getEmail());
        return EmployeeResponseDTO.fromEntity(employeeRepository.save(existing));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}