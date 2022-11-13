package data;

import domain.User;

import java.sql.*;
import java.util.*;

public class UserJDBC {
    private Connection transactionalConnection; //necesito un atributo para unificar las conexiones

    private static final String  SQL_SELECT = "SELECT id_user, user, password FROM user";
    private static final String  SQL_INSERT = "INSERT INTO user(user, password) VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE user SET user = ?, password = ? WHERE id_user = ?";

    private static final String SQL_DELETE = "DELETE FROM user WHERE id_user = ?";



    //Constructores para poder recibir la conexión transaccional:
    public UserJDBC(){

    }

    public UserJDBC(Connection transactionalConnection){
        this.transactionalConnection = transactionalConnection;//Esta conexión no se va a cerrar al ejecutar uno de
        // los métodos y así esta transacción se puede manejar fuera de esta clase, y esa clase externa es la que
        // decide cuándo se hará commit o rollback de toda la transacción. Así que ahora he de modificar todos los métodos:

    }

    //Método select, para mí es read:
    public List<User> read() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<>();

        //empiezo a ejecutar la sentencia:

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection(); //si
            //la conexión transaccional no es nula, se ejecutará dicha conexión, en caso contrario obtengo una nueva
            // conexión que se cerrará dentro de este método

            stmt = conn.prepareStatement(SQL_SELECT);

            rs = stmt.executeQuery();

            while ( rs.next() ) {

                int idUser = rs.getInt("id_user");
                String userName = rs.getString("user");
                String password = rs.getString("password");

                user = new User(idUser, userName, password);
                users.add(user);
            }

        }
        finally {

            Conexion.close(rs);
            Conexion.close(stmt);

            if(this.transactionalConnection == null){//sólo si la conexión transaccional es nula, se cierra la conexión dentro del método
                Conexion.close(conn);

            }
        }
        return users;
    }

    //Método insert, para mí es create:

    public int create(User user) throws SQLException {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());

            System.out.println("ejecutando query:" + SQL_INSERT);
            registers = stmt.executeUpdate();
            System.out.println("Registros afectados:" + registers);

        }
        finally {

            Conexion.close(stmt);
            if(this.transactionalConnection == null){//sólo si la conexión transaccional es nula, se cierra la conexión dentro del método
                Conexion.close(conn);


            } /*catch (SQLException e) {
                e.printStackTrace(System.out);
            }*/
        }
        return registers;
    }

    //método update:
    public int update(User user) throws SQLException {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);

            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getIdUser());

            registers = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + registers);


        }
        finally {

            Conexion.close(stmt);
            if(this.transactionalConnection == null){//sólo si la conexión transaccional es nula, se cierra la conexión dentro del método
                Conexion.close(conn);


            } /*catch (SQLException e) {
                e.printStackTrace(System.out);
            }*/
        }
        return registers;
    }
    //Método delete:
    public int delete(User user) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);


            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, user.getIdUser());
            registers = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + registers);

        }
        finally {

            Conexion.close(stmt);
            if(this.transactionalConnection == null){//sólo si la conexión transaccional es nula, se cierra la conexión dentro del método
                Conexion.close(conn);


            } /*catch (SQLException e) {
                e.printStackTrace(System.out);
            }*/
        }
        return registers;
    }
}
