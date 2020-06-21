package entity;

public class Mark {
    private Integer id;
    private Integer value;
    private Student student;
    private Subject subject;

    public Mark(Integer id, Integer value, Student student, Subject subject) {
        this.id = id;
        this.value = value;
        this.student = student;
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }
}
