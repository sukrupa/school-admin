<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Religion</th>
        <th>Caste</th>
        <th>Sub Caste</th>
        <th>Area</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.name}</td>
            <td>${student.religion}</td>
            <td>${student.caste}</td>
            <td>${student.subCaste}</td>
            <td>${student.area}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--<ul>--%>
<%--<c:forEach items="${students}" var="student">--%>
<%--<li>Name: ${student.name} - Religion: ${student.religion} - Caste: ${student.caste} - Sub Caste: ${student.subCaste} - Area: ${student.area}</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
</body>
</html>
