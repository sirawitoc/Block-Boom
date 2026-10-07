package model;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * PasswordHasher - แปลงรหัสผ่าน (plain text) ให้เป็นค่าแฮช (hash)
 * เพื่อไม่ให้เก็บรหัสผ่านจริงไว้ในระบบ (ความปลอดภัย)
 */
public class PasswordHasher {
 
    private static final String ALGORITHM = "SHA-256";
 
    public PasswordHasher() {
    }
 
    /**
     * แปลงรหัสผ่านให้เป็นค่าแฮชแบบ hex string
     * เช่น "1234" -> "03ac674216f3e15c761ee1a5e255f067..."
     */
    public String hash(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password ต้องไม่เป็น null");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hashedBytes = digest.digest(password.getBytes("UTF-8"));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("ไม่สามารถแฮชรหัสผ่านได้", e);
        }
    }
 
    /** เช็คว่ารหัสผ่านที่ผู้ใช้กรอก ตรงกับ hash ที่เก็บไว้ไหม */
    public boolean verify(String rawPassword, String hashedPassword) {
        String computed = hash(rawPassword);
        return computed.equals(hashedPassword);
    }
 
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
 