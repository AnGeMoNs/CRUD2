/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.DAO_Games;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD_Games {

    private Conexion conexion = new Conexion();
    private Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        Class.forName(conexion.getDriver());
        return DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getPassword());
    }

    public String Create(DAO_Games game) {
        String query = "INSERT INTO dbo.Games (Nombre, Desarrollador, Plataforma, FechaLanzamiento) VALUES (?, ?, ?, ?)";
        try (Connection connection = obtenerConexion();
             PreparedStatement pst = connection.prepareStatement(query)) {
            if (!validarNombreUnico(game.getNOMBRE(), game.getID())) {
                return "Nombre del videojuego ya existe.";
            }
            pst.setString(1, game.getNOMBRE());
            pst.setString(2, game.getDESARROLLADOR());
            pst.setString(3, game.getPLATAFORMA());
            pst.setDate(4, game.getFECHA_LANZAMIENTO());
            int result = pst.executeUpdate();
            return Integer.toString(result);
        } catch (ClassNotFoundException | SQLException ex) {
            return ex.toString();
        }
    }

    public List<DAO_Games> Read() {
        String query = "SELECT * FROM dbo.Games";
        List<DAO_Games> listaGames = new ArrayList<>();
        try (Connection connection = obtenerConexion();
             PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                DAO_Games game = new DAO_Games(
                    rs.getInt("ID"),
                    rs.getString("Nombre"),
                    rs.getString("Desarrollador"),
                    rs.getString("Plataforma"),
                    rs.getDate("FechaLanzamiento")
                );
                listaGames.add(game);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database error in Read: " + ex.getMessage());
        }
        return listaGames;
    }

    public List<DAO_Games> Read_Nombre(String nombre) {
        String query = "SELECT * FROM dbo.Games WHERE Nombre LIKE ?";
        List<DAO_Games> listaGames = new ArrayList<>();
        try (Connection connection = obtenerConexion();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    DAO_Games game = new DAO_Games(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Desarrollador"),
                        rs.getString("Plataforma"),
                        rs.getDate("FechaLanzamiento")
                    );
                    listaGames.add(game);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database error in Read_Nombre: " + ex.getMessage());
        }
        return listaGames;
    }
    public DAO_Games Read_ID(int id) {
    String query = "SELECT * FROM dbo.Games WHERE ID = ?";
    DAO_Games game = null;
    try (Connection connection = obtenerConexion();
         PreparedStatement pst = connection.prepareStatement(query)) {
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                game = new DAO_Games(
                    rs.getInt("ID"),
                    rs.getString("Nombre"),
                    rs.getString("Desarrollador"),
                    rs.getString("Plataforma"),
                    rs.getDate("FechaLanzamiento")
                );
            }
        }
    } catch (ClassNotFoundException | SQLException ex) {
        System.out.println("Database error in Read_ID: " + ex.getMessage());
    }
    return game;
}

    public String Update(DAO_Games game) {
    String query = "UPDATE dbo.Games " +
                   "SET Nombre = ?, " +
                   "Desarrollador = ?, " +
                   "Plataforma = ?, " +
                   "FechaLanzamiento = ? " +
                   "WHERE ID = ?";
    try (Connection connection = obtenerConexion();
         PreparedStatement pst = connection.prepareStatement(query)) {
        if (!validarNombreUnico(game.getNOMBRE(), game.getID())) {
            return "El nombre del videojuego ya existe para otro ID.";
        }
        pst.setString(1, game.getNOMBRE());
        pst.setString(2, game.getDESARROLLADOR());
        pst.setString(3, game.getPLATAFORMA());
        pst.setDate(4, new java.sql.Date(game.getFECHA_LANZAMIENTO().getTime()));
        pst.setInt(5, game.getID());

        int affectedRows = pst.executeUpdate();
        if (affectedRows == 0) {
            return "No se actualizó ningún registro, verifique el ID del juego.";
        }
        return "Actualización exitosa.";
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        return "Error al actualizar: " + ex.getMessage();
    }
}


    public String Delete(int id) {
        String query = "DELETE FROM dbo.Games WHERE ID = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            return "DELETE exitoso";
        } catch (ClassNotFoundException | SQLException ex) {
            return ex.getMessage();
        }
    }
    private boolean validarNombreUnico(String nombre, int id) throws SQLException, ClassNotFoundException {
    String query = "SELECT COUNT(*) FROM dbo.Games WHERE Nombre = ? AND ID <> ?";
    try (Connection connection = obtenerConexion();
         PreparedStatement pst = connection.prepareStatement(query)) {
        pst.setString(1, nombre);
        pst.setInt(2, id);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            }
        }
    } catch (SQLException | ClassNotFoundException ex) {
        System.out.println("Error en validarNombreUnico: " + ex.getMessage());
        throw ex;
    }
    return false;
}


}

