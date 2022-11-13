package domain;
//DAO: Data Access Object: patrón de diseño que contiene las operaciones a realizar sobre la clase de entidad

//clase de entidad:representa un registro de la tabla person en la bbdd

public class Person {//Es una clase de entidad
    //atributos de la bbdd:

    private int idPerson;
    private String name;
    private String surname;
    private String email;
    private String phone;

    //Constructores:


    public Person() {

    }

    public Person(int idPerson) {//Para el caso en que sólo necesite el id, por ejemplo para delete
        this.idPerson = idPerson;
    }

    public Person(String name, String surname, String email, String phone) {//Para casos como create
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Person(int idPerson, String name, String surname, String email, String phone) {//Para casos como update
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
