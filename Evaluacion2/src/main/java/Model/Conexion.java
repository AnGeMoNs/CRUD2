/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author angem
 */
public class Conexion {
    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String url = "jdbc:sqlserver://PCMAIN\\SQLEXPRESS:1433;databaseName=eval2;trustServerCertificate=true";
    private final String usuario = "sa";
    private final String password = "jhin1234";

    public Conexion() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver: " + e.getMessage());
        }
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
}

