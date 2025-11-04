
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${alumno != null}">
        <title>Editar Alumno</title>
    </c:if>
    <c:if test="${alumno == null}">
        <title>Nuevo Alumno</title>
    </c:if>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="container">
        <c:choose>
            <c:when test="${alumno != null}">
                <h2>Editar Alumno</h2>
                <form action="${pageContext.request.contextPath}/alumnos?action=actualizar" method="post">
                    <input type="hidden" name="idalumno" value="<c:out value='${alumno.idalumno}' />" />
            </c:when>
            <c:otherwise>
                <h2>Nuevo Alumno</h2>
                <form action="${pageContext.request.contextPath}/alumnos?action=insertar" method="post">
            </c:otherwise>
        </c:choose>

            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<c:out value='${alumno.nombre}' />" required />
            </div>

            <div>
                <label for="registro">Registro:</label>
                <input type="number" id="registro" name="registro" value="<c:out value='${alumno.registro}' />" required />
            </div>

            <div>
                <label for="idcarrera">Carrera:</label>
                <select id="idcarrera" name="idcarrera" required>
                    <c:forEach var="carrera" items="${listaCarreras}">
                        <option value="${carrera.idcarrera}"
                            <c:if test="${carrera.idcarrera == alumno.carrera.idcarrera}">
                                selected
                            </c:if>
                        >
                            <c:out value="${carrera.nombre}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <input type="submit" value="Guardar" class="btn btn-save"/>
        </form>

        <a href="${pageContext.request.contextPath}/alumnos" class="back-link">Volver al Listado</a>
    </div>

</body>
</html>
