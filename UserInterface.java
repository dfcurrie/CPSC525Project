import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface {
  
  private JFrame frame;
  private JPanel mainPanel;
  private GridBagConstraints pos;
  private UserManager uMan;
  
  public UserInterface() {
    frame = new JFrame("Login");
    mainPanel = new JPanel();
    pos = new GridBagConstraints();
    
    mainPanel.setLayout(new GridBagLayout());
    
    frame.getContentPane().add(mainPanel);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    
    JButton createButton = new JButton("Create User");
    createButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JPanel createUserPanel = new JPanel();
        createUserPanel.setLayout(new GridBagLayout());
        
        final JTextField usernameField = new JTextField("username");
        final JPasswordField passwordField = new JPasswordField("password");
        JButton submitButton = new JButton("Submit");
        
        submitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            uMan.createUser(usernameField.getText(), new String(passwordField.getPassword()));
            displayMain();
          }
        } );
        
        createUserPanel.add(usernameField);
        createUserPanel.add(passwordField);
        createUserPanel.add(submitButton);
        
        frame.getContentPane().removeAll();
        frame.getContentPane().add(createUserPanel);
        frame.revalidate();
        frame.repaint();
      }
    } );
    
    JButton verifyButton = new JButton("Verify User");
    verifyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        uMan.verifyUser();
        displayMain();
      }
    } );
 
    mainPanel.add(createButton);
    mainPanel.add(verifyButton);
    
    frame.setVisible(true);
  }
  
  public void displayMain() {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(mainPanel);
    frame.revalidate();
    frame.repaint();
  }
  
  public void addUserManager(UserManager uMan) {
    this.uMan = uMan;
  }

}