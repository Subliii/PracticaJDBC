# PracticaJDBC

Aplicación de consola en **Java** que se conecta a una base de datos **MySQL** usando **JDBC**.
Práctica del módulo de Acceso a Datos de 2.º de DAM (Davante, curso 2026/2027).

## Qué hace

| Clase | Qué demuestra |
|---|---|
| `Main` | Abre la conexión y lista todos los alumnos (`SELECT`) recorriendo el `ResultSet`. |
| `InsertarMarta` | Inserta un alumno con `executeUpdate` y muestra la tabla usando `ResultSetMetaData`. |
| `Transaccion` | Inserta un alumno y su matrícula en una transacción con `commit` y `rollback`. |
| `MetaDatos` | Muestra cualquier tabla sin conocer sus columnas gracias a `ResultSetMetaData`. |
| `Actividad5` | Ejercicio completo del ciclo JDBC: conexión, `Statement`, consulta, recorrido y cierre en `finally`. |

## Tecnologías

- Java 25
- Maven
- MySQL + `mysql-connector-j` 9.5.0

## Cómo ejecutarlo

1. Arranca MySQL y ejecuta `00_setup_root.sql` como `root` para crear la base de datos `davante` y su usuario.
2. Abre la carpeta `JDBC_Maven` en NetBeans (o VS Code con la extensión de Java).
3. Ejecuta la clase que quieras probar, por ejemplo `com.davante.Main`.
