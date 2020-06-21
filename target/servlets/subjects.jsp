<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
        <h3>Subjects</h3>
        <%@ page isELIgnored="false"%>
        Total <c:out value="${subjects.size()}"> </c:out> subjects. <br> <br>
        <c:forEach items="${subjects}" var="subject">
            <form method="post">
                <c:out value="${subject.getName()}"> </c:out>
                <input type="hidden" name="subject_id" value="${subject.getId()}" >
                <input type="submit" name="subject_delete" value="delete">
            </form>
        </c:forEach>
        <form method="post">
            name: <input type="text" name="name" value="">
            <input type="submit" name="subject_add" value="add">
        </form>
        <br>
        <a href="subjects">subjects</a>
        <a href="students">students</a>
        <a href="marks">marks</a>
    </body>
</html>
