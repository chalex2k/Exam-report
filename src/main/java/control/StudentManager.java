package control;

import entity.Student;

import java.util.ArrayList;

public class StudentManager extends EntityManager<Student>{

    private static StudentManager instance;

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public StudentManager() {
        super();
        tableName = "student";
        attribute_names = new ArrayList<>();
        attribute_names.add("name");
        attribute_names.add("surname");
        attribute_names.add("year");
    }

    private Mapper<Student> studentMapper = resultSet -> {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Integer year = resultSet.getInt("year");
        Student student = new Student(id, name, surname, year);
        return student;
    };

    @Override
    protected Mapper<Student> convertFrom() {
        return studentMapper;
    }

    @Override
    protected String getValuesForInsert(Student entity) {
        return String.format("'%s', '%s', %d", entity.getName(), entity.getSurname(), entity.getYear());
    }

    @Override
    protected String getValuesForUpdate(Student entity){
        return String.format("name = '%s', surname = '%s', year = %d", entity.getName(), entity.getSurname(), entity.getYear());
    }
}
