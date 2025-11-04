
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${carrera != null}">
        <title>Editar Carrera</title>
    </c:if>
    <c:if test="${carrera == null}">
        <title>Nueva Carrera</title>
    </c:if>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="container">
        <c:choose>
            <c:when test="${carrera != null}">
                <h2>Editar Carrera</h2>
                <form action="${pageContext.request.contextPath}/carreras?action=actualizar" method="post">
                    <input type="hidden" name="idcarrera" value="<c:out value='${carrera.idcarrera}' />" />
            </c:when>
            <c:otherwise>
                <h2>Nueva Carrera</h2>
                <form action="${pageContext.request.contextPath}/carreras?action=insertar" method="post">
            </c:otherwise>
        </c:choose>

            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<c:out value='${carrera.nombre}' />" required />
            </div>

            <div>
                <label for="idfacultad">Facultad:</label>
                <c:choose>
                    <c:when test="${not empty carrera.idcarrera}">
                        <p><b><c:out value="${carrera.facultad.nombre}"/></b></p>
                        <input type="hidden" name="idfacultad" value="${carrera.facultad.idfacultad}" />
                    </c:when>
                    <c:otherwise>
                        <select id="idfacultad" name="idfacultad" required>
                            <c:forEach var="facultad" items="${listaFacultades}">
                                <option value="${facultad.idfacultad}">
                                    <c:out value="${facultad.nombre}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>

            <input type="submit" value="Guardar" class="btn btn-save"/>
        </form>

        <a href="${pageContext.request.contextPath}/carreras" class="back-link">Volver al Listado</a>
    </div>

</body>
</html>
