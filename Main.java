import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    
    //Holds all the users, will probably need to be substituted with something else eventually
    UserManager uMan = new UserManager();
    
    //creates the gui that the user interacts with
    UserInterface gui = new UserInterface();
    
    //lets the GUI know about the user manager so it can tell it when events happen
    gui.addUserManager(uMan);
    
  }
}