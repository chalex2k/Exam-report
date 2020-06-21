package boundary;

import control.SubjectManager;
import entity.Subject;

import java.util.List;

public class SubjectService {
    public List<Subject> subjects() {
        return SubjectManager.getInstance().list();
    }

    public Subject getById(Integer id) {
        return SubjectManager.getInstance().getById(id);
    }

    public void addSubject(Subject u){
        SubjectManager.getInstance().insert(u);
    }

    public void deleteSubject(Integer id){
        SubjectManager.getInstance().delete(id);
    }
}
