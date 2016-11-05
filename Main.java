import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner userIn = new Scanner(System.in);
    
    //Holds all the users, will probably need to be substituted with something else eventually
    UserManager uMan = new UserManager();
    Interface userInterface = new Interface();

    String input = "";
    
    //the interaction loop
    while (!input.equals("Q")) {
      userInterface.displayMainOptions();
      input = userIn.next();
      //U for new user
      if (input.equals("U")) {
        uMan.createUser();
      //V for verify user
      } else if (input.equals("V")) {
        uMan.verifyUser();
      }
    }
  }
}