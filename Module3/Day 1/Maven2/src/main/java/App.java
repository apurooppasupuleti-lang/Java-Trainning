package org.example;
import config.SpringConfiguration;
import dao.TodoDao;
import entity.Todo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class App {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfiguration.class);
        TodoConsoleController todoConsoleController=context.getBean(TodoConsoleController.class);
        todoConsoleController.printWelcomeMessage();
        todoConsoleController.showMenu();
    }
}