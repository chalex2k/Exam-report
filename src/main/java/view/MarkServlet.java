package view;

import boundary.MarkService;
import boundary.StudentService;
import boundary.SubjectService;
import entity.Mark;
import entity.SQLInjectionException;
import entity.Student;
import entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@WebServlet(urlPatterns = "/marks")
public class MarkServlet extends HttpServlet {

    private MarkService markService;
    private StudentService studentService;
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        super.init();
        markService = new MarkService();
        studentService = new StudentService();
        subjectService = new SubjectService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Mark> marks = markService.marks();
        if (marks == null) {
            marks = emptyList();
        }
        List<Student> students = studentService.students();
        if (students == null) {
            students = emptyList();
        }
        List<Subject> subjects = subjectService.subjects();
        if (subjects == null) {
            subjects = emptyList();
        }
        req.setAttribute("marks", marks);
        req.setAttribute("students", students);
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("marks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mark_update") != null){
            Integer id = Integer.valueOf(req.getParameter("mark_id"));
            Integer value = Integer.valueOf(req.getParameter("mark_value"));
            try {
                Student student = getStudentFromRequest(req);
                Subject subject = getSubjectFromRequest(req);
                markService.updateMark(new Mark(id, value, student, subject));
            } catch (SQLInjectionException e) {
                System.out.println(e.getMessage());
            }
        }
        else if (req.getParameter("mark_delete") != null)
        {
            Integer id = Integer.valueOf(req.getParameter("mark_id"));
            markService.deleteMark(id);
        }
        else if (req.getParameter("mark_add") != null){
            Integer value =  Integer.valueOf(req.getParameter("mark_value"));
            try {
                Student student = getStudentFromRequest(req);
                Subject subject = getSubjectFromRequest(req);
                markService.addMark(new Mark(null, value, student, subject));
            } catch (SQLInjectionException e) {
                System.out.println(e.getMessage());
            }
        }
        List<Mark> marks = markService.marks();
        if (marks == null) {
            marks = emptyList();
        }
        List<Student> students = studentService.students();
        if (students == null) {
            students = emptyList();
        }
        List<Subject> subjects = subjectService.subjects();
        if (subjects == null) {
            subjects = emptyList();
        }
        req.setAttribute("marks", marks);
        req.setAttribute("students", students);
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("marks.jsp").forward(req, resp);

    }

    private Student getStudentFromRequest(HttpServletRequest req) throws SQLInjectionException {
        Integer stud_id = Integer.valueOf(req.getParameter("student_id"));
        String stud_name = req.getParameter("student_name");
        String stud_surname = req.getParameter("student_surname");
        Integer stud_year = Integer.valueOf(req.getParameter("student_year"));
        return new Student(stud_id, stud_name, stud_surname, stud_year);
    }

    private Subject getSubjectFromRequest(HttpServletRequest req) throws SQLInjectionException {
        Integer subj_id = Integer.valueOf(req.getParameter("subject_id"));
        String subj_name = req.getParameter("subject_name");
        return new Subject(subj_id, subj_name);
    }

}
