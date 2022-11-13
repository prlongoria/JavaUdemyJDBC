package data;

import domain.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {
    public List<PersonDTO> read() throws SQLException;//él lo llama select.
    public int create(PersonDTO person) throws SQLException;//él lo llama insert. Variable person de tipo PersonDTO
    public int update(PersonDTO person) throws SQLException;
    public int delete(PersonDTO person) throws SQLException;
}
