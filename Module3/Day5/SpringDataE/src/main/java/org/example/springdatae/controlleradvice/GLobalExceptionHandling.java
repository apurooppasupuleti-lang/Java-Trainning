package org.example.springdatae.controlleradvice;

import org.example.springdatae.exception.EmployeeNotFound;
import org.example.springdatae.exception.ProjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GLobalExceptionHandling {

    @ExceptionHandler(ProjectNotFound.class)
    public ResponseEntity<String> projectHandler(ProjectNotFound e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<String> employeeHandler(EmployeeNotFound e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
