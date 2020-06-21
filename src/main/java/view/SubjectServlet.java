package view;

import boundary.SubjectService;
import entity.Subject;
import entity.SQLInjectionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@WebServlet(urlPatterns = "/subjects")
public class SubjectServlet extends HttpServlet {

    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        super.init();
        subjectService = new SubjectService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = subjectService.subjects();
        if (subjects == null) {
            subjects = emptyList();
        }
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("subjects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("subject_add") != null){
            String name = req.getParameter("name");
            try {
                subjectService.addSubject(new Subject(null, name));
            }
            catch (SQLInjectionException e){
                System.out.println(e.getMessage());
            }
        }
        else if (req.getParameter("subject_delete") != null)
        {
            Integer id = Integer.valueOf(req.getParameter("subject_id"));
            subjectService.deleteSubject(id);
        }
        List<Subject> subjects = subjectService.subjects();
        if (subjects == null) {
            subjects = emptyList();
        }
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("subjects.jsp").forward(req, resp);
    }

}
