package domain;

public class User {
    private int idUser;
    private String userName;
    private String password;

    //Constructores:


    public User() {

    }

    public User(int idUser) {//Para el caso en que sólo necesite el id, por ejemplo para delete

        this.idUser = idUser;
    }

    public User(int idUser, String user, String password) {
        this.idUser = idUser;
        this.userName = user;
        this.password = password;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //getter y setter de cada atributo para cuando necesite utilizar un atributo en concreto:


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("idUser=").append(idUser);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
