package model;

public class User {

    private long id;
    private String login;
    private String password;
    private String role;
    private int age;

    public User() {
    }

    public User(String login, String password, String role, int age) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
