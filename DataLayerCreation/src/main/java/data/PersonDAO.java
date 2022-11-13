package data;
import domain.PersonDTO;
import java.sql.SQLException;
import java.util.List;

//INTERFACE GENÉRICA PARA CUALQUIER PROYECTO

//Esta interface es la que van a utilizar el resto de paquetes, y es independiente de la tecnología que se usa en la app

//Aquí sólo se definen los métodos, y por eso es reutilizable para cualquier
// tecnlogía que implemente a esta interface (JDBC; JPA...)
public interface PersonDAO {
    //Defino los métodos (Utilizan el objeto personDTO de la clase que contiene las variables de la bbdd, clase PersonDTO)

    public List<PersonDTO> read() throws SQLException;//él lo llama select.
    public int create(PersonDTO person) throws SQLException;//él lo llama insert. Variable person de tipo PersonDTO
    public int update(PersonDTO person) throws SQLException;
    public int delete(PersonDTO person) throws SQLException;
}
