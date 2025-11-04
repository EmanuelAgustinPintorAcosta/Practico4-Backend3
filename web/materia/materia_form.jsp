
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${materia != null}">
        <title>Editar Materia</title>
    </c:if>
    <c:if test="${materia == null}">
        <title>Nueva Materia</title>
    </c:if>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="container">
        <c:choose>
            <c:when test="${materia != null}">
                <h2>Editar Materia</h2>
                <form action="${pageContext.request.contextPath}/materias?action=actualizar" method="post">
                    <input type="hidden" name="idmateria" value="<c:out value='${materia.idmateria}' />" />
            </c:when>
            <c:otherwise>
                <h2>Nueva Materia</h2>
                <form action="${pageContext.request.contextPath}/materias?action=insertar" method="post">
            </c:otherwise>
        </c:choose>

            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<c:out value='${materia.nombre}' />" required />
            </div>

            <input type="submit" value="Guardar" class="btn btn-save"/>
        </form>

        <a href="${pageContext.request.contextPath}/materias" class="back-link">Volver al Listado</a>
    </div>

</body>
</html>
