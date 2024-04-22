<%-- 
    Document   : index.jsp
    Created on : 21-04-2024, 18:22:44
    Author     : angem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Videojuegos</title>
</head>
<body>
    <h1>Registro de Videojuegos</h1>
    <a href="index.jsp">Regresar</a>
    <form action="RegistrarGames_Controller" method="POST">
        <div>
            <label for="nombre">Nombre del Videojuego:</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>
        <div>
            <label for="desarrollador">Desarrollador:</label>
            <input type="text" id="desarrollador" name="desarrollador" required>
        </div>
        <div>
            <label for="plataforma">Plataforma:</label>
            <input type="text" id="plataforma" name="plataforma" required>
        </div>
        <div>
            <label for="fechaLanzamiento">Fecha de Lanzamiento:</label>
            <input type="date" id="fechaLanzamiento" name="fechaLanzamiento" required>
        </div>
        <div>
            <button type="submit">Registrar Videojuego</button>
        </div>
    </form>
    <div>
        <p>${respuesta}</p>
    </div>
</body>
</html>
