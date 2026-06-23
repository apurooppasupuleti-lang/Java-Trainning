package org.example;
import dao.TodoDao;
import dao.TodoDaoImplCollections;
import main.TodoConsoleController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Scanner;
@Configuration
public class SpringConfiguration {
    @Bean
    public TodoDao todoDao(){
        return new TodoDaoImplCollections();
    }
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
    @Bean
    public TodoConsoleController consoleController(Scanner scanner,TodoDao todoDao){
        return new TodoConsoleController(scanner,todoDao);
    }
}