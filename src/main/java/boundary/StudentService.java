package boundary;

import entity.Student;
import control.StudentManager;

import java.util.List;

public class StudentService {

    public List<Student> students() {
        return StudentManager.getInstance().list();
    }

    public Student getById(Integer id) {
        return StudentManager.getInstance().getById(id);
    }

    public void addStudent(Student u){
        StudentManager.getInstance().insert(u);
    }

    public void deleteStudent(Integer id){
        StudentManager.getInstance().delete(id);
    }

    public void updateStudent(Student u){
        StudentManager.getInstance().update(u.getId(), u);
    }
}