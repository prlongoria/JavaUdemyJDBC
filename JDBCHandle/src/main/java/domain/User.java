package domain;

public class User {
    private int idUser;
    private String userName;
    private String password;

    public User() {
    }

    public User(int idUser) {
        this.idUser = idUser;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int idUser, String user, String password) {
        this.idUser = idUser;
        this.userName = user;
        this.password = password;
    }

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
        final StringBuilder sb = new StringBuilder("UserDAO{");
        sb.append("idUser=").append(idUser);
        sb.append(", user='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
