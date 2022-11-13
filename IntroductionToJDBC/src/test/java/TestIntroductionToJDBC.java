import java.sql.*;
import java.sql.Driver;

//ESTO NO ES REUTILIZBLE, SÓLO ES PARA ENTENDER CÓMO SE HACE LA CONEXIÓN

public class TestIntroductionToJDBC {
    public static void main(String[] args) {
        //voy a empezar a trabajar con mysql:
        //Primero escribo la cadena de conexión:

        //var url = "jdbc:mysql://localhost:3306/test";

        var url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        //localhost es donde estoy trabajando con mysql, podría ser 127.0.0.1
        //3306 es el puerto de conexión que tengo y test es el nombre de la bbdd con la que voy a trabajar, en la que
        // tengo las tablas person y person1. ? es el símbolo de cierre pero quiero ponerle que no voy a utilizar el
        //puerto seguro, por eso pongo ese = false y useTImezone=true para indicar que voy a usar una zona horaria y le
        // pongo la zona UTC y añado el atributo de seguridad de la publicKey para que no me dé errores la conexión

        //Segundo: especifico la clase con la que voy a trabajar con mysql:
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");//cuando trabajo con apps locales no
            // sería necesario, pero en apps web puede que sí que lo necesite

            Connection connection = DriverManager.getConnection(url, "root", "");//requiere la url,
            // el usuario y la contraseña(en mi caso es vacía porque en xampp no la metí)

            //Creo un objeto tipo statement(interface) para que me permita ejecutar sentencia sobre mi bbdd:

            Statement instruction = connection.createStatement();//el lado derecho es la implementación de mysql

            var sql = "SELECT id_person, name, surname, email, phone FROM person";//sentencia sql que quiero ejecutar

            //ahora debo ejecutar esa sentencia sql:

            ResultSet result = instruction.executeQuery(sql);

            //debo iterar result con un ciclo while:

            while (result.next()){//el ciclo se ejecutará mientras tenga results que iterar
                System.out.println("Id person: " + result.getInt("id_person"));
                System.out.print("Name person: " + result.getString("name"));//sin ln para que imprima todo en la misma línea
                System.out.print("Surname person: " + result.getString("surname"));
                System.out.print("Email person: " + result.getString("email"));
                System.out.print("Phone person: " + result.getString("phone"));
                System.out.println(" ");
            }

            //ahora debo ir cerrando todos esos objetos en orden inverso:

            result.close();
            instruction.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}

