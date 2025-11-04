<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Alumnos sin Exámenes este Año</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>Alumnos sin Exámenes en el Año en Curso (2025)</h2>

    <c:choose>
        <c:when test="${not empty alumnosSinExamenes}">
            <table>
                <thead>
                    <tr>
                        <th>ID Alumno</th>
                        <th>Nombre del Alumno</th>
                        <th>Registro</th>
                        <th>Carrera</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="alumno" items="${alumnosSinExamenes}">
                        <tr>
                            <td><c:out value="${alumno.idalumno}"/></td>
                            <td><c:out value="${alumno.nombre}"/></td>
                            <td><c:out value="${alumno.registro}"/></td>
                            <td><c:out value="${alumno.carrera.nombre}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No se encontraron alumnos sin exámenes en el año en curso (2025).</p>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/" class="back-link">Volver al Menú Principal</a>
</div>

</body>
</html>
