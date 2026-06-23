package org.example.springdatae.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springdatae.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private List<String> projectNames;

    public static EmployeeResponseDTO fromEntity(Employee employee) {
        List<String> projectNames = (employee.getProjects() != null)
                ? employee.getProjects()
                .stream()
                .map(p -> p.getName())
                .collect(Collectors.toList())
                : List.of();

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                projectNames
        );
    }
}