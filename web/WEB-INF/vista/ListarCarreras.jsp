<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Carreras por Facultad</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <c:if test="${not empty facultad}">
        <h2>Carreras de la Facultad: <c:out value="${facultad.nombre}"/></h2>

        <c:choose>
            <c:when test="${not empty carreras}">
                <table>
                    <thead>
                        <tr>
                            <th>ID Carrera</th>
                            <th>Nombre de la Carrera</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="carrera" items="${carreras}">
                            <tr>
                                <td><c:out value="${carrera.idcarrera}"/></td>
                                <td><c:out value="${carrera.nombre}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Esta facultad no tiene carreras registradas.</p>
            </c:otherwise>
        </c:choose>

    </c:if>

    <a href="${pageContext.request.contextPath}/" class="back-link">Volver al Menú Principal</a>
</div>

</body>
</html>
