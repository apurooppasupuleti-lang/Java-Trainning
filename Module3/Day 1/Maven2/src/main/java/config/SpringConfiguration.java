package config;

import dao.TodoDao;
import dao.TodoDaoImplCollections;
import org.example.TodoConsoleController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public TodoDao todoDao() {
        return new TodoDaoImplCollections();

    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);

    }

    @Bean
    public TodoConsoleController consoleController(Scanner scanner, TodoDao todoDao) {
        return new TodoConsoleController(Scanner, todoDao);
    }

}