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
 * Servlet implementation class EditarGames_Controller
 */
public class EditarGames_Controller extends HttpServlet {
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

            String openTable = "<table style='width:50%'>";
            String closeTable = "</table>";
            String titulos = "<tr><th>ID</th><th>Nombre</th><th>Desarrollador</th><th>Plataforma</th><th>Fecha de Lanzamiento</th><th>Editar</th><th>Eliminar</th></tr>";
            respuesta = openTable + titulos + respuesta + closeTable;

            request.setAttribute("respuesta", "<div class='formulario'>" + respuesta + "</div>");
            request.getRequestDispatcher("EditarGames.jsp").forward(request, response);
        } else {
            String mensaje = "<div class='formulario'><label class='label-item'><b>No se han encontrado juegos para la búsqueda: " + nombreGame + "</b></label></div>";
            request.setAttribute("respuesta", mensaje);
            request.getRequestDispatcher("EditarGames.jsp").forward(request, response);
        }
    }

    protected String elementBuilder(DAO_Games game) {
        String id = "<tr><td><label class='label-item'><b>" + game.getID() + "</b></label></td>";
        String nombre = "<td><label class='label-item'><b>" + game.getNOMBRE() + "</b></label></td>";
        String desarrollador = "<td><label class='label-item'>" + game.getDESARROLLADOR() + "</label></td>";
        String plataforma = "<td><label class='label-item'>" + game.getPLATAFORMA() + "</label></td>";
        String fechaLanzamiento = "<td><label class='label-item'>" + game.getFECHA_LANZAMIENTO() + "</label></td>";

        String editar = "<td><form action='EditarGame.jsp' method='POST'> "
                + "<input type='hidden' name='idGame' id='idGame' value='" + game.getID() + "'>"
                + "<button type='submit'>Editar</button></form></td>";

        String eliminar = "<td><form action='EliminarGame.jsp' method='POST'> "
                + "<input type='hidden' name='idGame' id='idGame' value='" + game.getID() + "'>"
                + "<button type='submit'>Eliminar</button></form></td></tr>";

        return id + nombre + desarrollador + plataforma + fechaLanzamiento + editar + eliminar;
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
        return "Servlet to handle game edition requests";
    }
}