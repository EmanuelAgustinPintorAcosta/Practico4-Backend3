<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Docentes que dictan más de dos materias</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>Docentes que dictan más de dos materias</h2>

    <c:choose>
        <c:when test="${not empty docentesMasDeDosMaterias}">
            <table>
                <thead>
                    <tr>
                        <th>Nombre del Docente</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="docenteNombre" items="${docentesMasDeDosMaterias}">
                        <tr>
                            <td><c:out value="${docenteNombre}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No se encontraron docentes que dicten más de dos materias.</p>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/" class="back-link">Volver al Menú Principal</a>
</div>

</body>
</html>
