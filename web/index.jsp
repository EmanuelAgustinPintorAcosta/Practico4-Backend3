
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sistema de Gestión de Alumnos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h2>Menú Principal</h2>
        <nav>
            <ul id="main-menu">
                <li><a href="materias" class="btn btn-edit">Gestionar Materias</a></li>
                <li><a href="carreras" class="btn btn-edit">Gestionar Carreras</a></li>
                <li><a href="alumnos" class="btn btn-edit">Gestionar Alumnos</a></li>
                <li><a href="${pageContext.request.contextPath}/ExamenesJulio" class="btn btn-edit">Exámenes Julio 2025</a></li>
                <li><a href="${pageContext.request.contextPath}/AlumnosSinExamenes" class="btn btn-edit">Alumnos sin Exámenes este Año</a></li>
                <li><a href="${pageContext.request.contextPath}/DocentesMasDeDosMaterias" class="btn btn-edit">Docentes que dictan más de dos materias</a></li>
                <li>
                    <a href="#" class="btn btn-edit">Facultades</a>
                    <ul class="submenu">
                        <c:forEach var="facu" items="${facultades}">
                            <li><a href="Facultad?codigoFacu=${facu.idfacultad}">${facu.nombre}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</body>
</html>
