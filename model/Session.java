package model;
import data.User;

public class Session {
    private User user;
    private String playerName;

    // ผู้ใช้ login สำเร็จ เก็บ User และตั้งชื่อผู้เล่นเริ่มต้นเป็น username
    public void login(User user) {
        this.user = user;
        this.playerName = user.getUsername();
    }

    // ออกจากระบบ ล้างข้อมูลทั้งหมด
    public void logout() {
        this.user = null;
        this.playerName = null;
    }

    public User getUser() {
        return user;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
    

