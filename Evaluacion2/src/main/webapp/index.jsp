<%-- 
    Document   : index.jsp
    Created on : 21-04-2024, 23:09:02
    Author     : angem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <style>
            table, th, td {
                border:1px solid black;
            }
        </style>

        <title>VideoGames</title>
    </head>
    <body>
        <table style="width:100%">
            <tr>
                <th>Games</th>
            </tr>           
            <tr>
                <td><a href="RegistrarGames.jsp">Registrar Juegos</a></td>
            </tr>           
            <tr>
                <td><a href="BuscarGames.jsp">Buscar Juegos</a></td>
            </tr>
        </table>        
    </body>
</html>
