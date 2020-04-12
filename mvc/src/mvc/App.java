package mvc;

import mvc.controller.Controller;
import mvc.model.StudentInMemoryDaoImpl;

import java.util.Scanner;

public class App {

    //    Change to StudentPostgresDaoImpl when connecting PostgreSQL
    public static void main(String[] args) {
        Controller controller = new Controller(new StudentInMemoryDaoImpl());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            controller.showMenu().view();
            String command = scanner.nextLine();
            switch (command) {
                case "list":
                    controller.showStudents().view();
                    break;
                case "add":
                    controller.showInputNameMessage().view();
                    String name = scanner.nextLine();
                    controller.showInputDepartmentMessage().view();
                    String department = scanner.nextLine();
                    controller.addStudent(name, department).view();
                    break;
                case "quit":
                    return;
            }
        }
    }
}
