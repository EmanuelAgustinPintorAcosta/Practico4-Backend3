package com.app.controlador;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sesiones.FacultadFacade;

/**
 * Servlet que se carga al inicio de la aplicación para preparar datos globales,
 * como la lista de facultades para el menú dinámico.
 */
@WebServlet(name = "Manejador", loadOnStartup = 1, urlPatterns = {"/Facultad", "", "/ExamenesJulio", "/AlumnosSinExamenes", "/DocentesMasDeDosMaterias"})
public class Manejador extends HttpServlet {

    @EJB
    private FacultadFacade facu;
    @EJB
    private sesiones.CarreraFacade carreraFacade;
    @EJB
    private sesiones.ExamenFacade examenFacade;
    @EJB
    private sesiones.AlumnoFacade alumnoFacade; // Inyectar AlumnoFacade
    @EJB
    private sesiones.DocenteFacade docenteFacade; // Inyectar DocenteFacade

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, java.io.IOException {
        
        String url = "/index.jsp";
        String servletPath = request.getServletPath();

        switch (servletPath) {
            case "/Facultad":
                Integer nroFacultad = Integer.parseInt(request.getParameter("codigoFacu"));
                com.app.modelo.Facultad miFacu = facu.find(nroFacultad);
                java.util.List<com.app.modelo.Carrera> carreras = carreraFacade.findByFacultad(miFacu);
                request.setAttribute("facultad", miFacu);
                request.setAttribute("carreras", carreras);
                url = "/WEB-INF/vista/ListarCarreras.jsp";
                break;
            case "/ExamenesJulio":
                java.util.List<Object[]> examenesJulio = examenFacade.findExamenesJulioConAlumnoNota();
                request.setAttribute("examenesJulio", examenesJulio);
                url = "/WEB-INF/vista/ListarExamenesJulio.jsp";
                break;
            case "/AlumnosSinExamenes":
                java.util.List<com.app.modelo.Alumno> alumnosSinExamenes = alumnoFacade.findAlumnosSinExamenesEsteAnio();
                request.setAttribute("alumnosSinExamenes", alumnosSinExamenes);
                url = "/WEB-INF/vista/ListarAlumnosSinExamenes.jsp";
                break;
            case "/DocentesMasDeDosMaterias":
                java.util.List<String> docentes = docenteFacade.findDocentesConMasDeDosMaterias();
                request.setAttribute("docentesMasDeDosMaterias", docentes);
                url = "/WEB-INF/vista/ListarDocentesMasDeDosMaterias.jsp";
                break;
            case "":
                // Para la página de inicio, siempre cargamos una lista fresca de facultades
                request.setAttribute("facultades", facu.findAll());
                url = "/index.jsp";
                break;
            default:
                // Manejar rutas no reconocidas o redirigir a la página de inicio
                response.sendRedirect(request.getContextPath() + "/");
                return;
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }
}
