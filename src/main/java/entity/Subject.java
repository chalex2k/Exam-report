package entity;

public class Subject {

    private Integer id;

    public Integer getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public Subject(Integer id, String name) throws SQLInjectionException {
        this.id = id;
        if (isOnlyLetters(name)) // protect against sql-injections
            this.name = name;
        else
            throw new SQLInjectionException(String.format("string \"%s\" is potential danger", name));
    }

    public String toString(){
        return String.format("Subject %s", name);
    }

    private static boolean isOnlyLetters(String str) {
        // Only english letters without spaces
        return str.matches("[A-Za-z]+");
    }
}
