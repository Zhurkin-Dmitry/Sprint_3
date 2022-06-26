package model;

public class Courier {
    public String login;
    public String password;
    public String firstName;

    public Courier(){

    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }

    public Courier setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}