package boundary;

import control.MarkManager;
import control.StudentManager;
import entity.Mark;
import entity.Student;

import java.util.List;

public class MarkService {
    public List<Mark> marks() {
        return MarkManager.getInstance().list();
    }

    public Mark getById(Integer id) {
        return MarkManager.getInstance().getById(id);
    }

    public void addMark(Mark u){
        MarkManager.getInstance().insert(u);
    }

    public void deleteMark(Integer id){
        MarkManager.getInstance().delete(id);
    }

    public void updateMark(Mark u){
        MarkManager.getInstance().update(u.getId(), u);
    }
}
