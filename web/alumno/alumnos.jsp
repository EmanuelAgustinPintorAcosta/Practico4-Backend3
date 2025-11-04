
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Alumnos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="container">
        <h2>Listado de Alumnos</h2>

        <a href="${pageContext.request.contextPath}/alumnos?action=nuevo" class="btn btn-add">Agregar Nuevo Alumno</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Registro</th>
                    <th>Carrera</th>
                    <th>Facultad</th>
                    <th class="actions">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${listaAlumnos}">
                    <tr>
                        <td><c:out value="${alumno.idalumno}" /></td>
                        <td><c:out value="${alumno.nombre}" /></td>
                        <td><c:out value="${alumno.registro}" /></td>
                        <td><c:out value="${alumno.carrera.nombre}" /></td>
                        <td><c:out value="${alumno.carrera.facultad.nombre}" /></td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/alumnos?action=editar&id=${alumno.idalumno}" class="btn btn-edit">Editar</a>
                            <a href="${pageContext.request.contextPath}/alumnos?action=eliminar&id=${alumno.idalumno}" class="btn btn-delete" onclick="return confirm('¿Estás seguro de que deseas eliminar este alumno?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/" class="back-link">Volver al Inicio</a>
    </div>

</body>
</html>
