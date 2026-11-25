package com.davante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Gestion segura: transaccion alumno + matricula con commit / rollback. */
public class Transaccion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/davante";
        Connection conexion = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conexion = DriverManager.getConnection(url, "DavanteUser", "1234");
            st = conexion.createStatement();
            // Desactiva el autocommit para iniciar la transaccion
            conexion.setAutoCommit(false);
            // Inserta un nuevo alumno
            st.executeUpdate(
                "INSERT INTO alumnos(nombre, edad, email) " +
                "VALUES ('Ana Garcia', 20, 'ana@gmail.com')");
            // Obtiene el ultimo id generado
            rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idAlumno = rs.getInt(1);
            // Inserta una matricula asociada al alumno creado
            st.executeUpdate(
                "INSERT INTO matriculas(curso, idAlumno) " +
                "VALUES ('Bases de Datos', " + idAlumno + ")");
            // Si todo ha ido bien, confirma la transaccion
            conexion.commit();
            System.out.println("Alumno con id " + idAlumno + " matriculado correctamente.");

            // Comprobacion: matriculas del alumno
            rs = st.executeQuery("SELECT a.idAlumno, a.nombre, m.idMatricula, m.curso FROM alumnos a "
                + "JOIN matriculas m ON a.idAlumno = m.idAlumno WHERE a.idAlumno = " + idAlumno);
            while (rs.next()) {
                System.out.println("  idAlumno=" + rs.getInt(1) + " nombre=" + rs.getString(2)
                    + " idMatricula=" + rs.getInt(3) + " curso=" + rs.getString(4));
            }
        } catch (SQLException e) {
            try {
                if (conexion != null) { conexion.rollback(); }
                System.out.println("Error. Operacion cancelada: " + e.getMessage());
            } catch (SQLException d) {
                System.out.println("Error. No se pudo realizar RollBack: " + d.getMessage());
            }
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
