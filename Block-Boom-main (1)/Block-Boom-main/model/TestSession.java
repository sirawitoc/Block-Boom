package model;

import data.User;

public class TestSession {
    public static void main(String[] args) {
        System.out.println(AuthService.register("", "a@b.com", "1234", "1234"));         // กรุณากรอกข้อมูลให้ครบ
        System.out.println(AuthService.register("tom", "tom-mail", "1234", "1234"));     // รูปแบบอีเมลไม่ถูกต้อง
        System.out.println(AuthService.register("tom", "tom@mail.com", "12", "12"));     // รหัสผ่านต้องมีอย่างน้อย 4 ตัวอักษร
        System.out.println(AuthService.register("tom", "tom@mail.com", "1234", "9999")); // รหัสผ่านและช่องยืนยันไม่ตรงกัน

        System.out.println(AuthService.register("tom", "tom@mail.com", "1234", "1234")); // null (สำเร็จ)
        System.out.println(AuthService.register("tom", "other@mail.com", "1234", "1234")); // ชื่อผู้ใช้นี้ถูกใช้แล้ว
        System.out.println(AuthService.register("jerry", "tom@mail.com", "1234", "1234")); // อีเมลนี้ถูกใช้แล้ว

        User u1 = AuthService.login("tom", "1234");
        System.out.println(u1 != null ? u1.getUsername() : "null");                      // tom
        User u2 = AuthService.login("tom@mail.com", "1234");
        System.out.println(u2 != null ? u2.getUsername() : "null");                      // tom
        System.out.println(AuthService.login("tom", "wrong"));                           // null
        System.out.println(AuthService.login("nobody", "1234"));                         // null
    }
}