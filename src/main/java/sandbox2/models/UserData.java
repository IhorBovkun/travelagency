package sandbox2.models;

import java.sql.Date;

public class UserData extends Container {

    /**
     * All fields are iterates by iterator
     */
     private int id;
     private String role;
     private String login;
     private String password;
     private String email;
     private String name;
     private String last_name;
     private Date birth;

     public UserData() {
         id = 1;
         role = "admin";
         login = "varlamov";
         password = "123";
         email = "var@gmail.com";
         name = "Ilya";
         last_name = "Varlamov";
         birth = new Date(System.currentTimeMillis());
     }

    /**
     * GETTERS-SETTERS
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * END
     * GETTERS-SETTERS
     */

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birth=" + birth +
                '}';
    }
}
