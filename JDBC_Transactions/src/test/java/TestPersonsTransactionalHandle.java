import data.Conexion;
import data.PersonJDBC;
import domain.Person;

import java.sql.Connection;
import java.sql.SQLException;

public class TestPersonsTransactionalHandle {
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
            PersonJDBC personDAO = new PersonJDBC(connection);

            //Update:
            Person changePerson = new Person();//creo un nuevo objeto tipo Person para cambiarle los datos que
            // quiero de la bbdd

            changePerson.setIdPerson(2);
            changePerson.setName("Karla Ivonne");
            changePerson.setSurname("Gomez");
            changePerson.setEmail("kgomez@mail.com");
            changePerson.setPhone("456789132");
            personDAO.update(changePerson);

            //Create:(insert para el del video)
            Person newPerson = new Person();
            newPerson.setName("Alex");
            newPerson.setSurname("Ramirez");//en 294 caracteres hace el rollback, no entiendo por qué no lo hace a los 256
            newPerson.setEmail("aramirez@mail.com");
            newPerson.setPhone("951621357");
            personDAO.create(newPerson);

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
