import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
  Map<String, User> users;


  public UserManager() {
    users = new HashMap<String, User>();
  }
  
  //create user and add to map of users
  public void createUser() {
    Scanner userIn = new Scanner(System.in);
    System.out.print("enter a username: ");
    String username = userIn.next();
    User user = new User(username);
  for (int i = 0; i < User.MAX_PASS_NUM; i++) {
    System.out.print("enter password: ");
    user.addPw(userIn.next());
  }
    users.put(username, user);
  }
  
  
  
  //verify a user by asking for password set
  public boolean verifyUser() {
    Scanner userIn = new Scanner(System.in);
    
    System.out.print("Which user is authenticating?: ");
    String username = userIn.next();
    while (!users.containsKey(username)) {
      System.out.println("That is an invalid username");
      System.out.print("Which user is authenticating?: ");
      username = userIn.next();
    }
    String[] enter = new String[3];
    
    System.out.println("Now you must verify. Enter all the passwords you entered in any order.");
    for (int i = 0; i < User.MAX_PASS_NUM; i++) {
      System.out.print("enter a password: ");
      enter[i] = userIn.next();
    }  
    User user = users.get(username);
    
    if (user.checkPw(enter)) {
      System.out.println("correct");
      return true;
    } else {
      System.out.println("error");
      return false;
    }
    
  }
}