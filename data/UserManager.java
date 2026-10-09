
package data;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserManager { //ค้นหาผู้ใช้จากชื่อผู้ใช้
    private  static final String FILE_NAME = "users.csv";

    public static User findByUsername(String username) {
        for (User user : loadAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


    public static  void add(User user) { //บันทึกผู้ใช้ใหม่ต่อท้ายไฟล์
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_NAME, true), StandardCharsets.UTF_8))) {
            w.write(encode(user.getUsername()) + "," + user.getPassword());
            w.newLine();
        } catch (IOException e) {
         e.printStackTrace();
        }
    }

    private  static  List<User> loadAll() { //username กับ email กลับมาสร้างเป็น User ถ้าไม่มีไฟล์ก็คืนลิสต์ว่าง
        List<User> users = new ArrayList<>();
        File f = new File(FILE_NAME);
        if (!f.exists())
            return users;
        try (BufferedReader r = new BufferedReader(new InputStreamReader(
                new FileInputStream(f), StandardCharsets.UTF_8))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 2)
                    continue;
                users.add(new User(decode(parts[0]), decode(parts[1])));
            }
        } catch (IOException e) {
         e.printStackTrace();
        }
        return users;
    }

    private static  String encode(String s) { //แปลงข้อความให้ปลอดภัยก่อนเขียนลงไฟล์
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static  String decode(String s) { //แปลงข้อความกลับเป็นของเดิม
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

}
