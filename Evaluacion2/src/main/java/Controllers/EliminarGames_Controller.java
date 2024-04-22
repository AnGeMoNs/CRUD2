/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Model.CRUD_Games;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EliminarGames_Controller extends HttpServlet {

    /**
     * Procesa las solicitudes para los métodos HTTP <code>GET</code> y <code>POST</code>.
     *
     * @param request solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String mensaje = "";

        try {
            int idGame = Integer.parseInt(request.getParameter("idGame"));
            CRUD_Games crudGames = new CRUD_Games();
            mensaje = crudGames.Delete(idGame);
        } catch (NumberFormatException ex) {
            mensaje = "Error al procesar la solicitud: " + ex.getMessage();
        }

        request.setAttribute("respuesta", mensaje);
        request.getRequestDispatcher("BuscarGames.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Maneja el método HTTP <code>GET</code>.
     *
     * @param request solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarGames_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Maneja el método HTTP <code>POST</code>.
     *
     * @param request solicitud del servlet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarGames_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una descripción breve del servlet.
     *
     * @return una cadena con la descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "Controlador para eliminar juegos";
    }// </editor-fold>

}
