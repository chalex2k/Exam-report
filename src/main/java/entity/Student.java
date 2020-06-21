package entity;

public class Student {

    private Integer id;

    private String name;

    private String surname;

    private Integer year;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getId() {
        return id;
    }

    public Student(Integer id, String name, String surname, Integer year) throws SQLInjectionException {
        this.id = id;
        this.year = year;
        if (isOnlyLetters(name) && isOnlyLetters(surname) ) {// protect against sql-injections
            this.name = name;
            this.surname = surname;
        }
        else
            throw new SQLInjectionException(String.format("string \"%s\" or \"%s\" is potential danger", name, surname));
    }

    private static boolean isOnlyLetters(String str) {
        // Only english letters without spaces
        return str.matches("[A-Za-z]+");
    }
}
