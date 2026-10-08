
package data;

public class User { //ที่ใส่ข้อมูลผู้ใช้
    private String username;
    private String email;
    private String passwordhash;

    public User(String username, String email, String passwordhash) {
        this.username=username;
        this.email=email;
        this.passwordhash=passwordhash;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordhash;
    }

}
