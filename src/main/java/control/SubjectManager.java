package control;

import entity.Subject;

import java.util.ArrayList;

public class SubjectManager extends EntityManager<Subject> {

    private static SubjectManager instance;

    public static SubjectManager getInstance() {
        if (instance == null) {
            instance = new SubjectManager();
        }
        return instance;
    }

    public SubjectManager() {
        super();
        tableName = "subject";
        attribute_names = new ArrayList<>();
        attribute_names.add("name");
    }

    private Mapper<Subject> subjectMapper = resultSet -> {
        Integer s_id = resultSet.getInt("subject_id");
        String s_name = resultSet.getString("subject_name");
        return new Subject(s_id, s_name);
    };

    @Override
    protected Mapper<Subject> convertFrom() {
        return subjectMapper;
    }

    @Override
    protected String getValuesForInsert(Subject entity) {
        return String.format("'%s'", entity.getName());
    }

    @Override
    protected String getValuesForUpdate(Subject entity){
        return String.format("'%s'", entity.getName());
    }

    @Override
    protected String getSelectQuery() {
        return String.format("SELECT id as subject_id, name as subject_name " +
                "FROM %s;", this.tableName);
    }
}
