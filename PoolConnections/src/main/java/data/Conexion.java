package data;

import com.navercorp.pinpoint.plugin.commons.dbcp2.CommonsDbcp2Plugin;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Conexion {
    private static final String  JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String  JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; //(en mi caso es vacía porque en xampp no la metí)

    //Como quiero utilizar un pool de conexiones, primero me descargué la librería necesaria(aunque no me ha dejado
    // descargarme dbcp2 y he improvisado), ahora debo meter otro método aquí:
    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();

        //Ahora proporciono los valores para configurar el pool de conexiones:
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        //defino el tamño inicial del pool de conexiones:
        ds.setInitialSize(5);//no debo empezar con un tamaño grande, de momento pongo 5 conexiones
        return ds;
    }

    //Método para obtener la conexión hacia la bbdd:
    public static Connection getConnection() throws SQLException {//reporto la excepción para no meter un try catch

        return getDataSource().getConnection();
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
