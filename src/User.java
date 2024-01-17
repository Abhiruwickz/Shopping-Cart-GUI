public class User {

    public String username;
    public String password;

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }
// Getters
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
// Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}