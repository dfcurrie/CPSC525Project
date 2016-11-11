import java.util.HashMap;
import java.util.Map;

public class UserManager {
  //the 'database'
  Map<String, User> usersDB;


  public UserManager() {
    usersDB = new HashMap<String, User>();
  }
  
  //create user and add to map of users
  public void createUser(String username, String[] passwords) {
    User user = new User(username);
    for (int i = 0; i < User.MAX_PASS_NUM; i++) {
      user.addPw(passwords[i]);
    }
    System.out.println("successfully created user: " + username);
    usersDB.put(username, user);
    System.out.println("successfully added user to 'database'");
  }
  
  //verify a user by asking for password set
  public boolean verifyUser(String username, String passwords[]) {
    //checks if user exists
    if (!usersDB.containsKey(username)) {
      System.out.println("user " + username + " doesn't exist");
      return false;
    }
    //gets the user from the "database"
    User user = usersDB.get(username);
    // found user matching username
    
    if (user.checkPw(passwords)) {
      System.out.println("correct password");
      return true;
    } else {
      System.out.println("incorrect password");
      return false;
    }
    
  }
}