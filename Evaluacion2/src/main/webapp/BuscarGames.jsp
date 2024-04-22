<%-- 
    Document   : BuscarGames
    Created on : 21-04-2024, 22:18:06
    Author     : angem
--%>

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
        <title>Buscar Juegos</title>
    </head>
    <body>
        <h1>Buscar Juegos</h1>
        <a href="index.jsp">Regresar</a>
        <form action="BuscarGames_Controller" method="post" onsubmit="return validarForm()">        
            <label>Ingresar el nombre del juego:</label>
            <input type="text" name="nombreGame" placeholder="Nombre del juego" id="nombreGame">
            <button type="submit">Buscar</button>
        </form>
        <div>
            ${respuesta}
        </div>
    </body>
    <script>
        function validarForm() {
            var nombre = document.getElementById('nombreGame').value;
            if (nombre.trim() === "") {
                alert('Por favor, ingrese el nombre del juego.');
                return false;
            }
            return true;
        }
    </script>
</html>