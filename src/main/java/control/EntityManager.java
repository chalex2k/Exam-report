package control;

import entity.SQLInjectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityManager<R> {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DATABASE_USER = "university";
    private static final String DATABASE_PASS = "university";

    protected String tableName;
    List<String> attribute_names;
    protected abstract Mapper<R> convertFrom();

    public EntityManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<R> list() {
        return executeSelectQuery(getSelectQuery());
    }

    public R getById(Integer id) {
        String query = getSelectQuery();
        List<R> items = executeSelectQuery(query.substring(0, query.length() - 1) +
                getCondition(id));
        if (items != null && !items.isEmpty()) {
            return items.get(0);
        }
        return null;
    }

    public void insert(R newEntity) {
        executeUpdateQuery(getInsertQuery(newEntity));
    }
    public void update(Integer id, R changedEntity){
        executeUpdateQuery(getUpdateQuery(id, changedEntity));
    }
    public void delete(Integer id){
        executeUpdateQuery(getDeleteQuery(id));
    }

    private List<R> executeSelectQuery(String query) {
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            List<R> items = new ArrayList<>();
            while (resultSet.next()) {
                R item = convertFrom().map(resultSet);
                if (item != null)
                    items.add(item);
            }
            return items;
        } catch (SQLException | SQLInjectionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void executeUpdateQuery(String query)
    {
        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected String getInsertQuery(R entity){
        String head = String.join(", ", attribute_names);
        return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, head, getValuesForInsert(entity));
    }

    protected String getUpdateQuery(Integer id, R entity){
        return String.format("UPDATE %s SET %s WHERE id = %d", tableName, getValuesForUpdate(entity), id);
    }

    protected String getSelectQuery() {
        return String.format("SELECT * FROM %s;", tableName);
    }

    protected String getCondition(Integer id){
        return String.format(" WHERE id = %d;", id);
    }

    protected String getDeleteQuery(Integer id){
        return String.format("DELETE FROM %s WHERE id = %d", tableName, id);
    }

    protected abstract String getValuesForInsert(R entity);
    protected abstract String getValuesForUpdate(R entity);
}
