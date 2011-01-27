<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<table>
    <tr style="text-align:left;">
        <th>Name</th>
        <th>Religion</th>
        <th>Caste</th>
        <th>Sub Caste</th>
        <th>Area</th>
    </tr>
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

</body>
</html>
