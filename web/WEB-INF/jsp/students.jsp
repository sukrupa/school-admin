<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<ul>
    <c:forEach items="${students}" var="student">
        <li>${student.name}</li>
    </c:forEach>
</ul>
</body>
</html>
