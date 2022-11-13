import data.*;
import domain.PersonDTO;
import java.sql.*;
import java.util.List;

public class TestPersonHandle {

    public static void main(String[] args) {
        Connection connection = null;//debo declararla fuera del bloque try catch para poder utilizarla en él

        try {
            connection = Conexion.getConnection();//Connection es una clase de Java, Conexion es la clase
            // que yo he hecho para las conexiones con mi bbdd, connection es la variable(objeto)
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);//porque necesito que la conexión no haga autocommit, porque quiero hacerlo
                // manualmente.No quiero que al terminar de ejecutarse una sentencia se guarden los cambios
                // automáticamente, porque quiero hacer commit de TODA la transacción
            }
            PersonDAO personDAO = new PersonDaoJDBC(connection);//utilizo la interface, en la parte derecha es el único sitio donde manejo la implementación de la interface

            //Read:en este caso sólo voy a probar este método, pero puedo hacer todos los
            List<PersonDTO> persons = personDAO.read();

            for(PersonDTO person: persons){
                System.out.println("Person DTO: " + person);
            }

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
