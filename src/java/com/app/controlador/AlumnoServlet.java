
package com.app.controlador;

import com.app.modelo.Alumno;
import com.app.modelo.Carrera;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import sesiones.AlumnoFacade;
import sesiones.CarreraFacade;

@WebServlet("/alumnos")
public class AlumnoServlet extends HttpServlet {
    @EJB
    private AlumnoFacade alumnoFacade;
    @EJB
    private CarreraFacade carreraFacade;

    public void init() {
        // Injection replaces manual instantiation
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "insertar":
                    insertarAlumno(request, response);
                    break;
                case "eliminar":
                    eliminarAlumno(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "actualizar":
                    actualizarAlumno(request, response);
                    break;
                default:
                    listarAlumnos(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarAlumnos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Alumno> listaAlumnos = alumnoFacade.findAllWithDetails();
        request.setAttribute("listaAlumnos", listaAlumnos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("alumno/alumnos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carrera> listaCarreras = carreraFacade.findAll();
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("alumno/alumno_form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        int registro = Integer.parseInt(request.getParameter("registro"));
        int idcarrera = Integer.parseInt(request.getParameter("idcarrera"));

        Carrera carrera = carreraFacade.find(idcarrera);

        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(nombre);
        nuevoAlumno.setRegistro(registro);
        nuevoAlumno.setCarrera(carrera);
        
        alumnoFacade.create(nuevoAlumno);
        response.sendRedirect("alumnos");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Alumno alumnoExistente = alumnoFacade.find(id);
        List<Carrera> listaCarreras = carreraFacade.findAll();

        request.setAttribute("alumno", alumnoExistente);
        request.setAttribute("listaCarreras", listaCarreras);

        RequestDispatcher dispatcher = request.getRequestDispatcher("alumno/alumno_form.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idalumno = Integer.parseInt(request.getParameter("idalumno"));
        String nombre = request.getParameter("nombre");
        int registro = Integer.parseInt(request.getParameter("registro"));
        int idcarrera = Integer.parseInt(request.getParameter("idcarrera"));

        Carrera carrera = carreraFacade.find(idcarrera);

        Alumno alumno = alumnoFacade.find(idalumno);
        alumno.setNombre(nombre);
        alumno.setRegistro(registro);
        alumno.setCarrera(carrera);

        alumnoFacade.edit(alumno);
        response.sendRedirect("alumnos");
    }

    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Alumno alumno = alumnoFacade.find(id);
        alumnoFacade.remove(alumno);
        response.sendRedirect("alumnos");
    }
}
