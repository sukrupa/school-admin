<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TWU :: Trainers</title></head>
<body>
<b>Here are the trainers:</b>
<ul>
    <c:forEach items="${trainers}" var="trainer">
        <li>${trainer}</li>
    </c:forEach>
</ul>
</body>
</html>
