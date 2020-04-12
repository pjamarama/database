package mvc.model;

import java.util.List;
import java.util.ArrayList;

public class StudentInMemoryDaoImpl implements StudentDao {

    //    Temporary workaround
    private List<Student> students = new ArrayList<Student>();



    {
        students.add(new Student("Mickey", "IT"));
        students.add(new Student("Rachel", "IT"));
    }

    // TODO: 27.03.2020  Method should get Student list from data base
    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public void create(String name, String department) {
        students.add(new Student(name, department));
    }
}
