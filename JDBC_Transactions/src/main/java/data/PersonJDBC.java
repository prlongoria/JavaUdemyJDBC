package data;
//Esta clase la he cambiado con respecto a la de jdbcHandle, porque ahora tengo una conexión transaccional
import domain.Person;

import java.sql.*;
import java.util.*;

//Las transacciones necesitan que sea un única conexión, no una conexión por cada método como hice hasta ahora,
// para poder hacer commit y rollback de la transacción

public class PersonJDBC {
    private Connection transactionalConnection; //necesito un atributo para unificar las conexiones

    private static final String  SQL_SELECT = "SELECT id_person, name, surname, email, phone FROM person";
    private static final String  SQL_INSERT = "INSERT INTO person(name, surname, email, phone) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE person SET name = ?, surname = ?, email = ?, phone = ? WHERE id_person = ?";
    private static final String SQL_DELETE = "DELETE FROM person WHERE id_person = ?";

    //Constructores para poder recibir la conexión transaccional:
    public PersonJDBC(){

    }

    public PersonJDBC(Connection transactionalConnection){
        this.transactionalConnection = transactionalConnection;//Esta conexión no se va a cerrar al ejecutar uno de
        // los métodos y así esta transacción se puede manejar fuera de esta clase, y esa clase externa es la que
        // decide cuándo se hará commit o rollback de toda la transacción. Así que ahora he de modificar todos los métodos:

    }

    //Método select, para mí es read:
    public List<Person> read() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person person = null;
        List<Person> persons = new ArrayList<>();

        //empiezo a ejecutar la sentencia:

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection(); //si
            //la conexión transaccional no es nula, se ejecutará dicha conexión, en caso contrario obtengo una nueva
            // conexión que se cerrará dentro de este método

            stmt = conn.prepareStatement(SQL_SELECT);

            rs = stmt.executeQuery();

            while ( rs.next() ) {

                int idPerson = rs.getInt("id_person");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                person = new Person(idPerson, name, surname, email, phone);
                persons.add(person);
            }

        }
        finally {

                Conexion.close(rs);
                Conexion.close(stmt);

                if(this.transactionalConnection == null){//sólo si la conexión transaccional es nula, se cierra la conexión dentro del método
                    Conexion.close(conn);

            }
        }
        return persons;
    }

    //Método insert, para mí es create:

    public int create(Person person) throws SQLException {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSurname());
            stmt.setString(3, person.getEmail());
            stmt.setString(4, person.getPhone());

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
    public int update(Person person) throws SQLException {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);

            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSurname());
            stmt.setString(3, person.getEmail());
            stmt.setString(4, person.getPhone());
            stmt.setInt(5, person.getIdPerson());

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
    public int delete(Person person) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);


            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, person.getIdPerson());
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
