package control;

import entity.Mark;
import entity.Student;
import entity.Subject;

import java.util.ArrayList;

public class MarkManager extends EntityManager<Mark>{

    private static MarkManager instance;

    public static MarkManager getInstance() {
        if (instance == null) {
            instance = new MarkManager();
        }
        return instance;
    }

    public MarkManager() {
        super();
        tableName = "mark";
        attribute_names = new ArrayList<>();
        attribute_names.add("value");
        attribute_names.add("student_id");
        attribute_names.add("subject_id");
    }

    private Mapper<Mark> markMapper = resultSet -> {
        // subject
        Integer sub_id = resultSet.getInt("subject_id");
        String sub_name = resultSet.getString("subject_name");
        Subject sbj = new Subject(sub_id, sub_name);
        // student
        Integer stud_id = resultSet.getInt("student_id");
        String stud_name = resultSet.getString("student_name");
        String stud_surname = resultSet.getString("student_surname");
        Integer stud_year = resultSet.getInt("student_year");
        Student stud = new Student(stud_id, stud_name, stud_surname, stud_year);
        //mark
        Integer mark_id = resultSet.getInt("mark_id");
        Integer mark_value = resultSet.getInt("mark_value");
        Mark mark = new Mark(mark_id, mark_value, stud, sbj);
        return mark;
    };

    @Override
    protected Mapper<Mark> convertFrom() {
        return markMapper;
    }

    @Override
    protected String getValuesForInsert(Mark entity) {
        return String.format("%d, %d, %d", entity.getValue(), entity.getStudent().getId(), entity.getSubject().getId());
    }

    @Override
    protected String getValuesForUpdate(Mark entity){
        return String.format("value = %d, student_id = %d, subject_id = %d",
                entity.getValue(), entity.getStudent().getId(), entity.getSubject().getId());
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT mark.id as mark_id, mark.value as mark_value, " +
                "student.id as student_id, student.name as student_name, student.surname as student_surname, student.year as student_year," +
                "subject.id as subject_id, subject.name as subject_name" +
                " FROM mark JOIN student ON mark.student_id = student.id JOIN subject ON mark.subject_id = subject.id ";
    }
}
