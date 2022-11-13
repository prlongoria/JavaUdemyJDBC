import data.Conexion;
import data.UserJDBC;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUsers {
    public static void main(String[] args) {

        Connection connection = null;//debo declararla fuera del bloque try catch para poder utilizarla en él

        try {
            connection = Conexion.getConnection();//Connection es una clase de Java, Conexion es la clase
            // que yo he hecho para las conexiones con mi bbdd, connection es la variable(objeto)
            if(connection.getAutoCommit()){
                connection.setAutoCommit(false);//porque necesito que la conexión no haga autocommit, porque quiero hacerlo
                // manualmente.No quiero que al terminar de ejecutarse una sentencia se guarden los cambios
                // automáticamente, porque quiero hacer commit de TODA la transacción
            }
            UserJDBC userDAO =new UserJDBC(connection);

            //Update:
            User changeUser = new User();//creo un nuevo objeto tipo Person para cambiarle los datos que
            // quiero de la bbdd

            changeUser.setIdUser(1);
            changeUser.setUserName("Karla Ivonne");
            changeUser.setPassword("Gomez");
            userDAO.update(changeUser);

            //Create:(insert para el del video)
            User newUser = new User();
            newUser.setUserName("Alex");
            newUser.setPassword("password");//No entiendo hasta 294 caracteres no hace el rollback
            userDAO.create(newUser);

            //si todo funciona hago commit de la transacción:
            connection.commit();//hasta este momento se guardarán los cambios porque no se va a hacer commit de manera
            // automática al haberlo yo modificado a false
            System.out.println("Se ha hecho commit de la transacción");


        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");//en caso de error se imprimirá esto
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

}
