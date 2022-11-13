package domain;
//CLASE EN MI OPINIÓN GENÉRICA YA QUE ES DE DONDE SACA LA INTERFACE LOS DATOS PARA LOS MÉTODOS

//CLase que manda los objetos a la interface(es la clase en donde tengo las variables de lso campos de la bbdd y sus
// constructores, getters, setters y toString para poder manejar esos datos desde la clase PersonDaoJDBC
public class PersonDTO {
    private int idPerson;
    private String name;
    private String surname;
    private String email;
    private String phone;

    //Constructores:


    public PersonDTO() {

    }

    public PersonDTO(int idPerson) {//Para el caso en que sólo necesite el id, por ejemplo para delete
        this.idPerson = idPerson;
    }

    public PersonDTO(String name, String surname, String email, String phone) {//Para casos como create
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public PersonDTO(int idPerson, String name, String surname, String email, String phone) {//Para casos como update
        this.idPerson = idPerson;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    //getter y setter de cada atributo para cuando necesite utilizar un atributo en concreto:


    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //toString para poder imprimir el estado del objeto en cualquier momento:

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("idPerson=").append(idPerson);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
