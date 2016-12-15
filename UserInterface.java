import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface {
  
  //the frame that will be displayed
  private JFrame frame;
  
  //a panel is made for each 'page'
  private JPanel mainPanel;
  private JPanel createPanel;
  private JPanel verifyPanel;
  
  //for layout FEEL FREE TO CHANGE LAYOUT MANAGER
  private GridBagConstraints pos;
  
  //the usermanager so the gui can tell it to do commands when buttons are pressed
  private UserManager uMan;
  
  //holds name of selected images when creating password or verifying
//  private String[] passwordsToCheck = new String[Constants.MAX_PASS_NUM];
//  private int p = 0;
  
  //constructor
  public UserInterface() {
    //create the frame
    frame = new JFrame("Login");
    
    //make the panels
    makeMainPanel();
    makeCreatePanel();
    makeVerifyPanel();
    
    pos = new GridBagConstraints();
    
    //set defaults for frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setVisible(true);
    
    //display main panel
    displayPanel(mainPanel);
  }
  
  
  //the panel that displays the main page verify or create user
  private void makeMainPanel() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());    
    
    //button for creating user, displays create user panel
    JButton createButton = new JButton("Create User");
    createButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayPanel(createPanel);
      }
    } );
    
    //button for verifying user
    JButton verifyButton = new JButton("Verify User");
    verifyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayPanel(verifyPanel);
      }
    } );
 
    //add the buttons to the panel
    mainPanel.add(createButton);
    mainPanel.add(verifyButton);
  }
  
  //the panel that displays the create user? interface. displays main when finished
  private void makeCreatePanel() {
    createPanel = new JPanel();
    createPanel.setLayout(new GridBagLayout());
   
    //create fields for collecting info
    final JTextField usernameField = new JTextField("username", 5);
    final JPasswordField[] passwordFields = new JPasswordField[Constants.MAX_PASS_NUM];
    for (int i = 0; i < Constants.MAX_PASS_NUM; i++) {
      passwordFields[i] = new JPasswordField("pass" + (i+1),5);
    }
  
    //create button to collect info and pass info onto UserManager for creating user
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] passwords = new String[Constants.MAX_PASS_NUM];
        for (int i = 0; i < Constants.MAX_PASS_NUM; i++) {
          passwords[i] = new String(passwordFields[i].getPassword());
        }
        uMan.createUser(usernameField.getText(), passwords);
        displayPanel(mainPanel);
      }
    } );
    //create button to navigate to main page
    JButton homeButton = new JButton("Home");
    homeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayPanel(mainPanel);
      }
    } );
    
    //add fields and buttons to panel
    createPanel.add(usernameField);
    for (int i = 0; i < Constants.MAX_PASS_NUM; i++) {
      createPanel.add(passwordFields[i]);
    }
    createPanel.add(submitButton);
    createPanel.add(homeButton);
  }
  
  //the panel that displays the verify user interface. displays main if verification works
  private void makeVerifyPanel() {
    verifyPanel = new JPanel();
    verifyPanel.setLayout(new GridBagLayout());
    
    //create fields for collecting info
    JLabel userLabel = new JLabel("username:");
    final JTextField usernameField = new JTextField(5);
    JLabel passwordLabel = new JLabel("passwords:");
    final JPasswordField[] passwordFields = new JPasswordField[Constants.MAX_PASS_NUM];
    for (int i = 0; i < Constants.MAX_PASS_NUM; i++) {
      passwordFields[i] = new JPasswordField(5);
    }
    //create button to collect info and pass info onto UserManager for verification
    JButton verifyButton = new JButton("Verify");
    verifyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] passwords = new String[Constants.MAX_PASS_NUM]; 
        for (int i = 0; i < Constants.MAX_PASS_NUM; i++) {
          passwords[i] = new String(passwordFields[i].getPassword());
        }
        boolean result = uMan.verifyUser(usernameField.getText(), passwords);
        if (result == true) {
          displayPanel(mainPanel);
        } 
      }
    } );
    //create button to navigate to main page
    JButton homeButton = new JButton("Home");
    homeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayPanel(mainPanel);
      }
    } );
    
    //add fields and buttons to panel
    verifyPanel.add(userLabel);
    verifyPanel.add(usernameField);
    verifyPanel.add(passwordLabel);
    for (int i = 0; i < Constants.MAX_PASS_NUM; i++) {
      verifyPanel.add(passwordFields[i]);
    }
  //  verifyPanel.add(makeClickImage("pass1"));
  //  verifyPanel.add(makeClickImage("pass2"));
  //  verifyPanel.add(makeClickImage("incorrect"));
  
    verifyPanel.add(verifyButton);
    verifyPanel.add(homeButton);
  }
  
/*  
  //makes clickable image
  private JLabel makeClickImage(final String imageName) {
    ImageIcon image = new ImageIcon("images/" + imageName + ".png");
    JLabel imgLabel = new JLabel(image);
    imgLabel.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e) {
        passwordsToCheck[p] = imageName;
        p++;
      }
    });
    return imgLabel;
  }
 */ 
  
  //generic method to switch the displayed panel to provided panel
  private void displayPanel(JPanel display) {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(display);
    frame.revalidate();
    frame.repaint();
  }
  
  //adds te user manager to this class
  public void addUserManager(UserManager uMan) {
    this.uMan = uMan;
  }

}