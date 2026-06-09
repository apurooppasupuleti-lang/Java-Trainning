package wrapperclasses;

import java.util.Scanner;

class Manually{
    public static void NameNotFound(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name= sc.next();
    
        try{
            if(!name.equals("Sachin") && !name.equals("Rahul") && !name.equals("Saurav")){
                throw new NameNotFound("Invalid name");
            }
            System.out.println("Welcome " + name);
        } catch (NameNotFound e) {
            System.out.println("You are not allowed!!: " + e.getMessage());
        }
        finally {
            sc.close(); 
          
            finally close print.out {}
         
         
         finally c
          }
        
        }
    }

      