package data;
//CONEXIÓN GENÉRICA PARA CUALQUIER PROYECTO
import java.sql.*;

public class Conexion {
    private static final String  JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String  JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; //(en mi caso es vacía porque en xampp no la metí)

    //Mëtodo para obtener la conexión hacia la bbdd:
    public static Connection getConnection() throws SQLException {//reporto la excepción para no meter un try catch

        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    //añado métodos para cerrar esos objetos:

    public static void close(ResultSet rs) throws SQLException {//propago excepción en lugar de try catch
        rs.close();
    }

    public static void close(Statement smtm) throws SQLException {
        smtm.close();
    }

    public static void close(PreparedStatement smtm) throws SQLException {
        smtm.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
