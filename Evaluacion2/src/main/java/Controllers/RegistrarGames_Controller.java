/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.DAO_Games;
import Model.CRUD_Games;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author angem
 */
public class RegistrarGames_Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String mensaje;
        String insertGame;

        try {
            DAO_Games game = validacionDatos(request);
            if (game != null) {
                CRUD_Games crudGames = new CRUD_Games();
                insertGame = crudGames.Create(game);
                mensaje = insertGame;
                if (Integer.parseInt(insertGame) > 0) {
                    mensaje = "Videojuego grabado con éxito";
                }
            } else {
                mensaje = "Datos de videojuego no válidos o incompletos";
            }
        } catch (NumberFormatException | ParseException ex) {
            mensaje = "Error al procesar la solicitud: " + ex.toString();
        }

        request.setAttribute("respuesta", mensaje);
        request.getRequestDispatcher("RegistrarGames.jsp").forward(request, response);
    }

    protected DAO_Games validacionDatos(HttpServletRequest request) throws ParseException {
        String nombre = request.getParameter("nombre");
        String desarrollador = request.getParameter("desarrollador");
        String plataforma = request.getParameter("plataforma");
        String fechaLanzamientoStr = request.getParameter("fechaLanzamiento");
        Date fechaLanzamiento = null;
        
        if (fechaLanzamientoStr != null && !fechaLanzamientoStr.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaLanzamiento = new Date(sdf.parse(fechaLanzamientoStr).getTime());
        }

        if (!nombre.isEmpty() && !desarrollador.isEmpty() && !plataforma.isEmpty() && fechaLanzamiento != null) {
            return new DAO_Games(0, nombre, desarrollador, plataforma, fechaLanzamiento);
        } else {
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(RegistrarGames_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(RegistrarGames_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle Game registrations";
    }
}