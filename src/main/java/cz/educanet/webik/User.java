package cz.educanet.webik;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
