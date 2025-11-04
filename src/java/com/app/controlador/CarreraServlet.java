
package com.app.controlador;

import com.app.modelo.Carrera;
import com.app.modelo.Facultad;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import sesiones.CarreraFacade;
import sesiones.FacultadFacade;

@WebServlet("/carreras")
public class CarreraServlet extends HttpServlet {
    @EJB
    private CarreraFacade carreraFacade;
    @EJB
    private FacultadFacade facultadFacade;

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
                    insertarCarrera(request, response);
                    break;
                case "eliminar":
                    eliminarCarrera(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "actualizar":
                    actualizarCarrera(request, response);
                    break;
                default:
                    listarCarreras(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarCarreras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carrera> listaCarreras = carreraFacade.findAll();
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrera/carreras.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Facultad> listaFacultades = facultadFacade.findAll();
        request.setAttribute("listaFacultades", listaFacultades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrera/carrera_form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarCarrera(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        int idfacultad = Integer.parseInt(request.getParameter("idfacultad"));
        
        Facultad facultad = facultadFacade.find(idfacultad);

        Carrera nuevaCarrera = new Carrera();
        nuevaCarrera.setNombre(nombre);
        nuevaCarrera.setFacultad(facultad);

        carreraFacade.create(nuevaCarrera);
        response.sendRedirect("carreras");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carrera carreraExistente = carreraFacade.find(id);
        List<Facultad> listaFacultades = facultadFacade.findAll();
        
        request.setAttribute("carrera", carreraExistente);
        request.setAttribute("listaFacultades", listaFacultades);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrera/carrera_form.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarCarrera(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idcarrera = Integer.parseInt(request.getParameter("idcarrera"));
        String nombre = request.getParameter("nombre");
        int idfacultad = Integer.parseInt(request.getParameter("idfacultad"));

        Facultad facultad = facultadFacade.find(idfacultad);
        
        Carrera carrera = carreraFacade.find(idcarrera);
        carrera.setNombre(nombre);
        carrera.setFacultad(facultad);

        carreraFacade.edit(carrera);
        response.sendRedirect("carreras");
    }

    private void eliminarCarrera(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carrera carrera = carreraFacade.find(id);
        carreraFacade.remove(carrera);
        response.sendRedirect("carreras");
    }
}
