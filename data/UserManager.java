import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private String FILE_NAME = "users.csv";

    public User findByUsername(String username){
       for (User user : loadAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public  User findByEmail(String email){
        for (User user : loadAll()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public  void add(User user){
        
    }
    private List<User> loadAll(){
         return new ArrayList<>();
    }
    
}
