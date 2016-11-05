import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
  Map<String, User> usersDB;


  public UserManager() {
    usersDB = new HashMap<String, User>();
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
    usersDB.put(username, user);
  }
  
  //verify a user by asking for password set
  public boolean verifyUser() {
    Scanner userIn = new Scanner(System.in);
    
    System.out.print("Which user is authenticating?: ");
    String username = userIn.next();
    //asks until valid username is supplied
    while (!usersDB.containsKey(username)) {
      System.out.println("That is an invalid username");
      System.out.print("Which user is authenticating?: ");
      username = userIn.next();
    }
    String[] enter = new String[User.MAX_PASS_NUM];
    
    //asks for the correct passwords
    System.out.println("Now you must verify. Enter all the passwords you entered in any order.");
    for (int i = 0; i < User.MAX_PASS_NUM; i++) {
      System.out.print("enter a password: ");
      enter[i] = userIn.next();
    }  
    
    //gets the user from the "database"
    User user = usersDB.get(username);
    
    if (user.checkPw(enter)) {
      System.out.println("correct");
      return true;
    } else {
      System.out.println("error");
      return false;
    }
    
  }
}