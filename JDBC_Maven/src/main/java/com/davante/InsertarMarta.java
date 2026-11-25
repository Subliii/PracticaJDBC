package com.davante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/** Actividad 4 - Insertar una fila (executeUpdate) antes de leer la tabla con SELECT. */
public class InsertarMarta {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/davante";
        Connection conexion = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conexion = DriverManager.getConnection(url, "DavanteUser", "1234");
            st = conexion.createStatement();
            // Insertar una fila en la tabla alumnos (devuelve el numero de filas afectadas)
            int n = st.executeUpdate(
                "INSERT INTO alumnos (nombre, edad, email) VALUES ('Marta', 19, 'marta@gmail.com')");
            System.out.println(n + " fila(s) insertada(s)");
            // ResultSet contiene la salida de la sentencia
            rs = st.executeQuery("SELECT * FROM alumnos");
            ResultSetMetaData meta = rs.getMetaData();
            int numColumnas = meta.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= numColumnas; i++) {
                    System.out.print(meta.getColumnName(i) + "=" + rs.getObject(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error. Operacion cancelada: " + e.getMessage());
        } finally {
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
