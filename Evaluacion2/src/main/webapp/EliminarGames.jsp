<%-- 
    Document   : EliminarGames.jsp
    Created on : 22-04-2024, 0:01:08
    Author     : angem
--%>

<%@page import="DAO.DAO_Games"%>
<%@page import="Model.CRUD_Games"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table, th, td {
                border: 1px solid black;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Juego</title>
    </head>
    <body>
        <h1>Confirmación de Eliminar Juego</h1>
        <a href="BuscarGames.jsp">Regresar</a>
        <%
            String idGame = request.getParameter("idGame");
            CRUD_Games crudGames = new CRUD_Games();
            DAO_Games game = crudGames.Read_ID(Integer.parseInt(idGame));
        %>
        <table style="width:30%">
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>Desarrollador</td>
                <td>Plataforma</td>
                <td>Fecha Lanzamiento</td>
                <td></td>
            </tr>
            <tr>
                <td><%out.print(game.getID());%></td>
                <td><%out.print(game.getNOMBRE());%></td>
                <td><%out.print(game.getDESARROLLADOR());%></td>
                <td><%out.print(game.getPLATAFORMA());%></td>
                <td><%out.print(game.getFECHA_LANZAMIENTO());%></td>
                <td> <form action="EliminarGames_Controller" method="post">
                        <input type="hidden" id="idGame" name="idGame" value="<%out.print(game.getID());%>">
                        <button type="submit">ELIMINAR</button>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
