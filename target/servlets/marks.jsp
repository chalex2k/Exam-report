<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <h3>Marks</h3>
        <%@ page isELIgnored="false"%>
        <table border="2">
            <tr> <td> </td>
                <c:forEach items="${subjects}" var="subject">
                <td><c:out value="${subject.getName()}"> </c:out></td>
                </c:forEach>
            </tr>

            <c:forEach items="${students}" var="student">
            <tr>
                <td><c:out value="${student.getSurname()}"> </c:out></td>
                <c:forEach items="${subjects}" var="subject">
                <td>
                    <% boolean flg = false; %>
                    <c:forEach items="${marks}" var="mark">
                        <c:if test="${mark.getStudent().getId() == student.getId() && mark.getSubject().getId() == subject.getId()}">
                        <form method="post">
                            <input type="text" name="mark_value" value="${mark.getValue()}">
                            <input type="hidden" name="mark_id" value="${mark.getId()}" >
                            <input type="hidden" name="student_id" value="${mark.getStudent().getId()}" >
                            <input type="hidden" name="student_name" value="${mark.getStudent().getName()}" >
                            <input type="hidden" name="student_surname" value="${mark.getStudent().getSurname()}" >
                            <input type="hidden" name="student_year" value="${mark.getStudent().getYear()}" >
                            <input type="hidden" name="subject_id" value="${mark.getSubject().getId()}" >
                            <input type="hidden" name="subject_name" value="${mark.getSubject().getName()}" >
                            <input type="submit" name="mark_update" value="update">
                            <input type="submit" name="mark_delete" value="delete">
                        </form>
                        <% flg = true;%>
                        </c:if>
                        </c:forEach>
                        <% if (flg == false){ %>
                            <form method="post">
                        <input type="text" name="mark_value" value="">
                        <input type="hidden" name="student_id" value="${student.id}" >
                        <input type="hidden" name="student_name" value="${student.getName()}" >
                        <input type="hidden" name="student_surname" value="${student.getSurname()}" >
                        <input type="hidden" name="student_year" value="${student.year}" >
                        <input type="hidden" name="subject_id" value="${subject.getId()}" >
                        <input type="hidden" name="subject_name" value="${subject.getName()}" >
                        <input type="submit"   name="mark_add" value="add">
                            </form>
                        <% }%>
                </td>
                    </c:forEach>
            </tr>
            </c:forEach>
        </table>
    <br>
        <a href="subjects">subjects</a>
        <a href="students">students</a>
        <a href="marks">marks</a>
    </body>
</html>
