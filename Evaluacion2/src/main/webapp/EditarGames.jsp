<%-- 
    Document   : EditarGames
    Created on : 21-04-2024, 22:30:58
    Author     : angem
--%>

<%@page import="DAO.DAO_Games"%>
<%@page import="Model.CRUD_Games"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar Juego</title>
</head>
<body>
    <h1>Actualizar Juego</h1>
    <a href="BuscarGames.jsp">Regresar</a>
    <%
        String idGameStr = request.getParameter("idGame");
        int idGame = 0;
        try {
            idGame = Integer.parseInt(idGameStr);
        } catch (NumberFormatException e) {
            // Manejo del error si no se puede convertir a entero
            response.sendRedirect("errorPage.jsp"); 
            return; 
        }
        CRUD_Games crudGames = new CRUD_Games();
        DAO_Games game = crudGames.Read_ID(idGame);
        if (game == null) {
            // Manejo del error si no se encuentra el juego con el ID proporcionado
            response.sendRedirect("errorPage.jsp");
            return;
        }
    %>

    <form action="EditarGames_Controller" method="post" onsubmit="return Validar_Formulario()">
        <input type="hidden" name="idGame" id="idGame" value="<%= game.getID() %>">
        <label for="nombreGame">Nombre:</label>
        <input type="text" name="nombreGame" id="nombreGame" value="<%= game.getNOMBRE() %>">
        <label for="desarrolladorGame">Desarrollador:</label>
        <input type="text" name="desarrolladorGame" id="desarrolladorGame" value="<%= game.getDESARROLLADOR() %>">
        <label for="plataformaGame">Plataforma:</label>
        <input type="text" name="plataformaGame" id="plataformaGame" value="<%= game.getPLATAFORMA() %>">
        <label for="fechaLanzamientoGame">Fecha de Lanzamiento:</label>
        <input type="date" name="fechaLanzamientoGame" id="fechaLanzamientoGame" value="<%= game.getFECHA_LANZAMIENTO().toString() %>">
        <button type="submit">Actualizar</button>
    </form>

    <footer>
        <script>
            function Validar_Formulario() {
                var mensaje = "";
                if (document.getElementById("nombreGame").value === "") {
                    mensaje += "- Nombre del Juego \n";
                }
                if (document.getElementById("desarrolladorGame").value === "") {
                    mensaje += "- Desarrollador \n";
                }
                if (document.getElementById("plataformaGame").value === "") {
                    mensaje += "- Plataforma \n";
                }
                if (document.getElementById("fechaLanzamientoGame").value === "") {
                    mensaje += "- Fecha de Lanzamiento \n";
                }
                if (mensaje !== "") {
                    mensaje = "FALTA POR INGRESAR: \n" + mensaje;
                    alert(mensaje);
                    return false;
                }
                return true;
            }
        </script>
    </footer>
</body>
</html>