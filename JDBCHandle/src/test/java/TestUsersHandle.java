import data.PersonDAO;
import data.UserDAO;
import domain.Person;
import domain.User;

import java.util.List;

public class TestUsersHandle {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        //Create:
        User userNew = new User("Juan", "Contraseña");
        userDAO.create(userNew);

        //Update:
        User userUpdate = new User(5, "Pepita", " Goters");
        userDAO.update(userUpdate);

        //Delete:

        User userDelete = new User(5);

        userDAO.delete(userDelete);

        //Read:
        List<User> users = userDAO.read();

        for (User user : users) {
            System.out.println("user = " + user);
        }
    }
}
