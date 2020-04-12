package mvc.view;

import mvc.controller.View;
import mvc.model.Student;

import java.util.List;

public class StudentsView implements View {
    private List<Student> studentList;

    public StudentsView(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public void view() {
        for (Student student : studentList) {
            System.out.printf("%s\t%s\t%d\n", student.getName(), student.getDepartment(), student.getYear());
        }
    }
}
