package data;


import domain.User;

import java.sql.*;
import java.util.*;

public class UserDAO {

    private static final String  SQL_SELECT = "SELECT id_user, user, password FROM user";
    private static final String  SQL_INSERT = "INSERT INTO user(user, password) VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE user SET user = ?, password = ? WHERE id_user = ?";

    private static final String SQL_DELETE = "DELETE FROM user WHERE id_user = ?";

    //Método select, para mí es read:
    public List<User> read() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<>();

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_SELECT);

            rs = stmt.executeQuery();

            while ( rs.next() ) {

                int idUser = rs.getInt("id_user");
                String userName = rs.getString("user");
                String password = rs.getString("password");


                user = new User(idUser, userName, password);

                users.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return users;
    }

    //Método insert, para mí es create:

    public int create(User user) {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());

            registers = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return registers;
    }

    //método update:

    public int update(User user) {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getIdUser());

            registers = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return registers;
    }

    //Método delete:
    public int delete(User user) {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, user.getIdUser());

            registers = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return registers;
    }



}
