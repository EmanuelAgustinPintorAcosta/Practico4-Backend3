
package com.app.controlador;

import com.app.modelo.Materia;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import sesiones.MateriaFacade;

@WebServlet("/materias")
public class MateriaServlet extends HttpServlet {
    @EJB
    private MateriaFacade materiaFacade;

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
                    insertarMateria(request, response);
                    break;
                case "eliminar":
                    eliminarMateria(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "actualizar":
                    actualizarMateria(request, response);
                    break;
                default:
                    listarMaterias(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarMaterias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Materia> listaMaterias = materiaFacade.findAll();
        request.setAttribute("listaMaterias", listaMaterias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("materia/materias.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("materia/materia_form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarMateria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        Materia nuevaMateria = new Materia();
        nuevaMateria.setNombre(nombre);
        materiaFacade.create(nuevaMateria);
        response.sendRedirect("materias");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Materia materiaExistente = materiaFacade.find(id);
        request.setAttribute("materia", materiaExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("materia/materia_form.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarMateria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idmateria"));
        String nombre = request.getParameter("nombre");

        Materia materia = materiaFacade.find(id);
        materia.setNombre(nombre);
        materiaFacade.edit(materia);
        response.sendRedirect("materias");
    }

    private void eliminarMateria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Materia materia = materiaFacade.find(id);
        materiaFacade.remove(materia);
        response.sendRedirect("materias");
    }
}
