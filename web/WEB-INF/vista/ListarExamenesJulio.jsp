<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Exámenes de Julio</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>Mesas de Examen - Turno Julio 2025</h2>

    <c:choose>
        <c:when test="${not empty examenesJulio}">
            <table>
                <thead>
                    <tr>
                        <th>Nombre del Alumno</th>
                        <th>Nota Obtenida</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="examen" items="${examenesJulio}">
                        <tr>
                            <td><c:out value="${examen[0]}"/></td>
                            <td><c:out value="${examen[1]}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No se encontraron mesas de examen para el turno de Julio 2025.</p>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/" class="back-link">Volver al Menú Principal</a>
</div>

</body>
</html>
