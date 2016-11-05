import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner userIn = new Scanner(System.in);
    
    UserManager uMan = new UserManager();
    Interface userInterface = new Interface();

    userInterface.displayMainOptions();
    String input = "p";
    
    while (!input.equals("Q")) {
      userInterface.displayMainOptions();
      input = userIn.next();
      if (input.equals("U")) {
        uMan.createUser();
      } else if (input.equals("V")) {
        uMan.verifyUser();
      }
    }
  }
}