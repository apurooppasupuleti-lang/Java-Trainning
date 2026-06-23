package org.example;
import dao.TodoDao;
import entity.Todo;
import java.util.Scanner;
public class TodoConsoleController {
    private Scanner scanner;
    private TodoDao todoDao;
    public TodoConsoleController(Scanner scanner, TodoDao todoDao){
        this.scanner=scanner;
        this.todoDao=todoDao;
    }
    public void printWelcomeMessage(){
        System.out.println("Welcome to Todo Dao service..");
    }
    public void showMenu(){
        while(true) {
            System.out.println("1:Add");
            System.out.println("2:Update");
            System.out.println("3:Delete");
            System.out.println("4:List All");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            redirectChoice(choice);
        }
    }
    private void redirectChoice(int choice) {
        switch(choice){
            case 1:
                add();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
            case 4:
                listAll();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    private void listAll() {
        System.out.println(todoDao.findAll());
    }
    private void delete() {
        System.out.println("Enter id of task to be deleted:");
        int id=scanner.nextInt();
        todoDao.deleteById(id);
    }
    private void update() {
        System.out.println("Enter id of task to be updated:");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new content:");
        String content=scanner.nextLine();
        System.out.println("Enter new done status:");
        boolean done=scanner.nextBoolean();
        todoDao.updateById(id,new Todo(content,done));
    }
    private void add() {
        System.out.println("Enter id:");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter content:");
        String content=scanner.nextLine();
        System.out.println("Enter done:");
        boolean done=scanner.nextBoolean();
        todoDao.save(new Todo(id,content,done));
    }
}