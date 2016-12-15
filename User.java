import org.mindrot.jbcrypt.BCrypt;

public class User{
  
  //user's info
  private String username;
  private String[] pwHashArray;
  private int numPw;
  //salt removed as BCrypt stores salt in hash
  //private String salt;
  
  
  //default constructor
  public User() {
    username = "default";
    pwHashArray = new String[Constants.MAX_PASS_NUM];
    numPw = 0;
    
  }
  
  //username provided constructor
  public User(String username) {
    this.username = username;
    pwHashArray = new String[Constants.MAX_PASS_NUM];
    numPw = 0;
    
  }
  
  
  //change username, keep password
  public void changeUsername(String newUsername) {
    username = newUsername;
  }
  
  //add a hashed password to array of passwords WILL NEED TO BE CHANGED TO ACCEPT IMAGE
  //uses slow hashing algorithm
  public void addPw(String addPw) {
    if (numPw >= Constants.MAX_PASS_NUM) {
      System.out.println("Password length is at max size");
    } else {
      String hashedPw = BCrypt.hashpw(addPw, BCrypt.gensalt());
      pwHashArray[numPw] = hashedPw;
      numPw++;
    }
  }
  
  //checks if the password set entered equals the user's password set
  public boolean checkPw(String[] unVerPwArray) {
    System.out.println("checking passwords...");
    
    //rejects if set not long enough
    if (unVerPwArray.length < Constants.MAX_PASS_NUM) {
      return false;
    }
    
    //creates array to hold verified individual passwords
    int[] matchArray = new int[Constants.MAX_PASS_NUM];
    
    //for every password in unverified set, checks if in user's set
    for (int i = 0; i < unVerPwArray.length; i++) {
      int ver = checkIndiv(unVerPwArray[i]);
      if (ver == -1) {
        return false;
      } 
      matchArray[ver] = ver;      
    }
    
    //check to make sure all passwords in set were verified
    for (int i = 0; i < matchArray.length; i++) {
      if (i != matchArray[i]) {
        return false;
      }
    }
    return true;
  }
  
  //checks whether the individual password is in the user's password set
  private int checkIndiv(String unVerPw) {
    for (int i = 0; i < numPw; i++) {
      if (BCrypt.checkpw(unVerPw, pwHashArray[i])) {
        return i;
      }
    }    
    return -1;
  }
  
}