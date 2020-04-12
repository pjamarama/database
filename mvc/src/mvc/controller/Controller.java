/**
 *  Model layer is responsible for students list
 */

package mvc.controller;

import mvc.model.Student;
import mvc.model.StudentDao;
import mvc.view.MenuView;
import mvc.view.MessageView;
import mvc.view.StudentsView;

import java.util.List;

public class Controller {
    private StudentDao studentDAO;

    public Controller(StudentDao studentDAO) {
        this.studentDAO = studentDAO;
    }

    public View showMenu() {
        return new MenuView();
    }

    public View showStudents() {
        List<Student> studentList = studentDAO.findAll();
        return new StudentsView(studentList);
    }

    // TODO: 27.03.2020 Place message to file 'message.properties' 
    public View showInputNameMessage() {
        return new MessageView("Input student's name: ");
    }

    public View showInputDepartmentMessage() {
        return new MessageView("Input student's department: ");
    }

    public View addStudent(String name, String department) {
        studentDAO.create(name, department);
        return new MessageView("Student successfully added");
    }
}
