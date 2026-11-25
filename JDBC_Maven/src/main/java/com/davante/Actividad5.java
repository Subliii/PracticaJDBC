package com.davante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Actividad 5 - Codigo completado.
 * A = DriverManager.getConnection   B = createStatement   C = executeQuery
 * D = next                          E = getString         F = finally
 * (La tabla de la practica se llama "alumnos" en nuestra BD davante.)
 */
public class Actividad5 {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/davante";
        String usuario = "DavanteUser";
        String password = "1234";
        Connection conexion = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conexion = DriverManager.getConnection(URL, usuario, password);   // A
            st = conexion.createStatement();                                  // B
            rs = st.executeQuery("SELECT nombre FROM alumnos");               // C
            while (rs.next()) {                                               // D
                System.out.println(rs.getString("nombre"));                   // E
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        } finally {                                                           // F
            try {
                if (rs != null) { rs.close(); }
                if (st != null) { st.close(); }
                if (conexion != null) { conexion.close(); }
            } catch (SQLException f) {
                System.out.println("Error. No se pudo cerrar la conexion con la base de datos: " + f.getMessage());
            }
        }
    }
}
