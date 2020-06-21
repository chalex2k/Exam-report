package view;

import boundary.StudentService;
import entity.SQLInjectionException;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@WebServlet(urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.students();
        if (students == null) {
            students = emptyList();
        }
        req.setAttribute("students", students);
        req.getRequestDispatcher("students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("student_update") != null){
            Integer id = Integer.valueOf(req.getParameter("student_id"));
            String name = req.getParameter("student_name");
            String surname = req.getParameter("student_surname");
            Integer year = Integer.valueOf(req.getParameter("student_year"));
            try {
                studentService.updateStudent(new Student(id, name, surname, year));
            }
            catch (SQLInjectionException e){
                System.out.println(e.getMessage());
            }
        }
        else if (req.getParameter("student_delete") != null)
        {
            Integer id = Integer.valueOf(req.getParameter("student_id"));
            studentService.deleteStudent(id);
        }
        else if (req.getParameter("student_add") != null){
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            Integer year = Integer.valueOf(req.getParameter("year"));
            try {
                studentService.addStudent(new Student(null, name, surname, year));
            }
            catch (SQLInjectionException e){
                System.out.println(e.getMessage());
            }
        }
        List<Student> students = studentService.students();
        if (students == null) {
            students = emptyList();
        }
        req.setAttribute("students", students);
        req.getRequestDispatcher("students.jsp").forward(req, resp);
    }
}
