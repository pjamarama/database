package mvc.model;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    void create(String name, String department);
}
