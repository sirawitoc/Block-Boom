package model;

import data.User;
import data.UserManager;

public final class AuthService {
    private static final UserManager USERS = new UserManager();

    private AuthService() {}

    public static String register(String username, String email,
                                  String password, String confirm) {
        if (username == null || email == null || password == null || confirm == null) {
            return "กรุณากรอกข้อมูลให้ครบ";
        }
        username = username.trim();
        email = email.trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return "กรุณากรอกข้อมูลให้ครบ";
        }
        if (!email.contains("@")) {
            return "รูปแบบอีเมลไม่ถูกต้อง";
        }
        if (password.length() < 4) {
            return "รหัสผ่านต้องมีอย่างน้อย 4 ตัวอักษร";
        }
        if (!password.equals(confirm)) {
            return "รหัสผ่านและช่องยืนยันไม่ตรงกัน";
        }
        if (USERS.findByUsername(username) != null) {
            return "ชื่อผู้ใช้นี้ถูกใช้แล้ว";
        }
        if (USERS.findByEmail(email) != null) {
            return "อีเมลนี้ถูกใช้แล้ว";
        }

        USERS.add(new User(username, email, PasswordHasher.hash(password)));
        return null;
    }

    public static User login(String identifier, String password) {
        if (identifier == null || password == null) {
            return null;
        }
        identifier = identifier.trim();

        User user = USERS.findByUsername(identifier);
        if (user == null) {
            user = USERS.findByEmail(identifier);
        }
        if (user == null) {
            return null;
        }

        String hashed = PasswordHasher.hash(password);
        return hashed.equals(user.getPasswordHash()) ? user : null;
    }
}