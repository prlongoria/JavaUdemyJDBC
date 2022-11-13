package data;
//Tengo una clase DAO por cada clase de entidad, así separo las responsabilidades de cada clase

import domain.Person;

import java.sql.*;
import java.util.*;

public class PersonDAO { //CRUD sobre la tabla de person

    //Las sentencias sql debo probarlas directamente en la bbdd para ver que funcionan y no me den error aquí
    private static final String  SQL_SELECT = "SELECT id_person, name, surname, email, phone FROM person";
    private static final String  SQL_INSERT = "INSERT INTO person(name, surname, email, phone) VALUES(?, ?, ?, ?)";
    //Pongo ? por cada valor que tendrán mis parámetros de la sentencia

    private static final String SQL_UPDATE = "UPDATE person SET name = ?, surname = ?, email = ?, phone = ? WHERE id_person = ?";

    private static final String SQL_DELETE = "DELETE FROM person WHERE id_person = ?";

    //Método select, para mí es read:
    public List<Person> read() { //regresará lista genérnica de objetos de tipo persona

        //defino variables que voy a utlizar:
        Connection conn = null;//para hacer conexión con db
        PreparedStatement stmt = null;//para enviar la sentencia sql
        ResultSet rs = null;//para recibir la respuesta de la sentencia sql
        Person person = null;//para hacer el objeto
        List<Person> persons = new ArrayList<>();//para tener una lista de objetos tipo person

        //empiezo a ejecutar la sentencia:

        try {
            conn = Conexion.getConnection();//así ya tengo conexión con bbdd(lo primero que tengo que hacer)

            stmt = conn.prepareStatement(SQL_SELECT);//para ejecutar la sentencia sql

            rs = stmt.executeQuery();//con esto tengo el resultado(ejecuta una consulta) y ahora debo iterar cada uno
            // de los registros para ir creando cada objeto Person por cada regisro que tenga en la tabla de la bbdd
            // e ir agregándolo a la lista de personas

            while ( rs.next() ) {//meto rs y next para saber si tengo registros que iterar

                int idPerson = rs.getInt("id_person");//así voy recuperando cada variable
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                person = new Person(idPerson, name, surname, email, phone); //construyo objeto tipo persona usando el
                // constructor. Si usara hibernate esto lo haría automáticamente

                persons.add(person);//así se agrega el objeto a la lista

            }

            } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        finally {//este bloque es para cerrar los objetos que he abierto, y este siempre se va a ejecutar, pase lo que pase
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return persons;
    }

    //Método insert, para mí es create:

    public int create(Person person) { //va a recibir un objeto de tipo persona
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;//opto por iniciar en 0
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, person.getName());//1 es el parámetro de name de la sentencia sql, así
            // relleno los parámetros de la bbdd
            stmt.setString(2, person.getSurname());
            stmt.setString(3, person.getEmail());
            stmt.setString(4, person.getPhone());

            registers = stmt.executeUpdate();//ejecuta la actualización de la bbdd (insert, update o
            // delete puede ejecutar este método executeUpdate)

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

        return registers;//me retorna los registros afectados
    }

    //método update:

    public int update(Person person) {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSurname());
            stmt.setString(3, person.getEmail());
            stmt.setString(4, person.getPhone());
            stmt.setInt(5, person.getIdPerson());

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

        return registers;//me retorna los registros afectados
    }

    //Método delete:
    public int delete(Person person) {
        //variables:
        Connection conn = null;
        PreparedStatement stmt = null;
        int registers = 0;//opto por iniciar en 0
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, person.getIdPerson());//1 porque sólo voy a sustituir un parámetro que es el idperson

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
