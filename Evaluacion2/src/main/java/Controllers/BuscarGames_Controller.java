/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.DAO_Games;
import Model.CRUD_Games;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementación del Servlet BuscarGames_Controller
 */
public class BuscarGames_Controller extends HttpServlet {

    /**
     * Procesa las solicitudes para los métodos HTTP <code>GET</code> y <code>POST</code>.
     *
     * @param request solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String respuesta = "";
        String nombreGame = request.getParameter("nombreGame");
        CRUD_Games gamesCrud = new CRUD_Games();
        List<DAO_Games> listaGames = gamesCrud.Read_Nombre(nombreGame);
        if (!listaGames.isEmpty()) {
            for (DAO_Games game : listaGames) {
                respuesta += elementBuilder(game);
            }

            String openTable = "<table style=\"width:50%\">";
            String closeTable = "</table>";
            String titulos = "<tr><th>ID</th><th>Nombre</th><th>Desarrollador</th><th>Plataforma</th><th>Editar</th><th>Eliminar</th></tr>";
            respuesta = openTable + titulos + respuesta + closeTable;

            request.setAttribute("respuesta", "<div class='formulario'>" + respuesta + "</div>");
            request.getRequestDispatcher("BuscarGames.jsp").forward(request, response);
        } else {
            String mensaje = "<div class='formulario'><label class = \"label-item\"> <b>No se han encontrado juegos para la búsqueda: " + nombreGame + "</b></label></div>";
            request.setAttribute("respuesta", mensaje);
            request.getRequestDispatcher("BuscarGames.jsp").forward(request, response);
        }
    }

    protected String elementBuilder(DAO_Games game) {
        String id = "<tr><td><label class=\"label-item\"><b>" + game.getID() + "</b></label></td>";
        String nombre = "<td><label class=\"label-item\"><b>" + game.getNOMBRE() + "</b></label></td>";
        String desarrollador = "<td><label class=\"label-item\">" + game.getDESARROLLADOR() + "</label></td>";
        String plataforma = "<td><label class=\"label-item\">" + game.getPLATAFORMA() + "</label></td>";

        String editar = "<td><form action='EditarGames.jsp' method='POST'>"
                + "<input type='hidden' name='idGame' value='" + game.getID() + "'>"
                + "<button type='submit'>Editar</button></form></td>";

        String eliminar = "<td><form action='EliminarGames.jsp' method='POST'>"
                + "<input type='hidden' name='idGame' value='" + game.getID() + "'>"
                + "<button type='submit'>Eliminar</button></form></td></tr>";

        return id + nombre + desarrollador + plataforma + editar + eliminar;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar las solicitudes de búsqueda de juegos";
    }
}

