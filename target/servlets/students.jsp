<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <h3>Students</h3>
        <%@ page isELIgnored="false"%>
        Total <c:out value="${students.size()}"> </c:out> students. <br> <br>
        Name     |      Surname    |       Year
        <c:forEach items="${students}" var="student">
            <form method="post">
                <input type="text" name="student_name" value="${student.name}">
                <input type="text" name="student_surname" value="${student.surname}">
                <input type="text" name="student_year" value="${student.year}">
                <input type="hidden" name="student_id" value="${student.id}" >
                <input type="submit" name="student_update" value="update">
                <input type="submit" name="student_delete" value="delete">
            </form>
        </c:forEach>
        <form method="post">
            name: <input type="text" name="name" value=""><br>
            surname: <input type="text" name="surname" value=""><br>
            year:  <input type="text" name="year" value=""><br>
            <input type="submit"   name="student_add" value="add">
        </form>

        <br>
        <a href="subjects">subjects</a>
        <a href="students">students</a>
        <a href="marks">marks</a>
    </body>
</html>
