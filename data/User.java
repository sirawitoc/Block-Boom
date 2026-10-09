
package data;

public class User { //ที่ใส่ข้อมูลผู้ใช้
    private String username;
    private String password;

    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

}
