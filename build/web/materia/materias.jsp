
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Materias</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="container">
        <h2>Listado de Materias</h2>

        <a href="${pageContext.request.contextPath}/materias?action=nuevo" class="btn btn-add">Agregar Nueva Materia</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th class="actions">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="materia" items="${listaMaterias}">
                    <tr>
                        <td><c:out value="${materia.idmateria}" /></td>
                        <td><c:out value="${materia.nombre}" /></td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/materias?action=editar&id=${materia.idmateria}" class="btn btn-edit">Editar</a>
                            <a href="${pageContext.request.contextPath}/materias?action=eliminar&id=${materia.idmateria}" class="btn btn-delete" onclick="return confirm('¿Estás seguro de que deseas eliminar esta materia?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/" class="back-link">Volver al Inicio</a>
    </div>

</body>
</html>
