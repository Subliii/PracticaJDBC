package com.davante;

// 1. Cargar el driver (import)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Actividad 4 - Listado de alumnos con do-while (ID, nombre, edad, email). */
public class Main {
    public static void main(String[] args) {
        // jdbc:  mysql:  //localhost  :3306  /davante
        String url = "jdbc:mysql://localhost:3306/davante";
        String usuario = "DavanteUser";
        String password = "1234";

        Connection conexion = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 2. Abrir conexion
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion establecida con la base de datos davante");
            // 3. Objeto de intercambio
            st = conexion.createStatement();
            // ResultSet contiene la salida de la sentencia
            rs = st.executeQuery("SELECT * FROM alumnos");
            // Si ResultSet no esta vacio
            if (rs.next()) {
                System.out.println("Listado de alumnos:");
                // 4. Leer: recorre todos los registros. next() avanza a la siguiente fila
                do {
                    System.out.println(
                        "------------------------------------\n" +
                        " ID: " + rs.getInt("idAlumno") + "\n" +
                        " Nombre: " + rs.getString("nombre") + "\n" +
                        " Edad: " + rs.getInt("edad") + "\n" +
                        " email: " + rs.getString("email") + "\n" +
                        "------------------------------------");
                    // rs.next() devuelve false cuando no quedan mas registros
                } while (rs.next());
            } else {
                System.out.println("La consulta no ha devuelto registros.");
            }
        } catch (SQLException e) {
            System.out.println("Error. Operacion cancelada: " + e.getMessage());
        } finally { // 5. Cerrar los objetos
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
