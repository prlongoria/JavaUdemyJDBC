import data.PersonDAO;
import domain.Person;

import java.util.List;

public class TestPersonsHandle { //para ejecutar los métodos de PersonDAO
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();
        //PersonDAO es una clase concreta, no tiro de una interface, aunque más adelante veré que es mejor una
        // Interfaz y luego la clase que la implemente

        //Inserto un nuevo objeto(person) en la bbdd (create):

        Person personNew = new Person("Mariano", "Ortiz", "mortinz@mail.com", "647852157");
        personDAO.create(personNew);
        //System.out.println("personNew = " + personNew);

        //Actualizar, modificar un objeto, update:

        Person personUpdate = new Person(3, "Pepito", " Gotera", "pgotera@mail.com", "123456789");
        personDAO.update(personUpdate);

        //Delete:

        Person personDelete = new Person(13); //meto el id que quiero eliminar, en este caso el 13

        personDAO.delete(personDelete);

        //Imprimo la lista de personas:lo pongo el útlimo para que me salga en consola el listado de personas una vez
        // hecha la acción que quiero

        List<Person> persons = personDAO.read();//recupero la lista de objetos en una variable

        //Para imprimr la list necesito un ciclo forEach:

        for (Person person : persons) { //tipo de variable, y nombre de la lista
            System.out.println("person = " + person);
        }

        //se puede hacer con función flecha en lugar del for de arriba:
       // persons.forEach(person -> {
        //    System.out.println("person = " + person);
        //});



    }
}
