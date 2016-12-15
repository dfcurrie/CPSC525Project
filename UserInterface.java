import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.Random;

public class UserInterface {
  
  //the frame that will be displayed
  private JFrame frame;
  
  //a panel is made for each 'page'
  private JPanel mainPanel;
  private JPanel createPanel;
  private JPanel userPanel;
  private JPanel verifyPanel;
  
  //for layout FEEL FREE TO CHANGE LAYOUT MANAGER
  private GridBagConstraints pos;
  
  //the usermanager so the gui can tell it to do commands when buttons are pressed
  private UserManager uMan;
  
  //holds name of selected images when creating password or verifying
  private String user;
  private String[] passwordsToCheck = new String[Constants.MAX_PASS_NUM];
  private int p = 0;
  
  private Random rand = new Random();
  
  //constructor
  public UserInterface() {
    //create the frame
    frame = new JFrame("Login");
    
    //make the panels
    makeMainPanel();
    makeCreatePanel();
    makeUserPanel();
   
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
        displayPanel(userPanel);
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

    //create button to collect info and pass info onto UserManager for creating user
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        uMan.createUser(usernameField.getText(), passwordsToCheck);
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

	File path = new File("images");
    File[] files = path.listFiles();
    
    for (int i = 0; i < 15; i ++) {
		createPanel.add(makeClickImage(files[rand.nextInt(100)].getName()));
	}
    createPanel.add(submitButton);
    createPanel.add(homeButton);
  }
  
  
  private void makeUserPanel() {
	  userPanel = new JPanel();
	  userPanel.setLayout(new GridBagLayout());
	  
	JLabel userLabel = new JLabel("username:");
    final JTextField usernameField = new JTextField(10);
    
    JButton nextButton = new JButton("Next");
    nextButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			makeVerifyPanel(usernameField.getText());
			displayPanel(verifyPanel);
		}
	} );
	
	userPanel.add(userLabel);
	userPanel.add(usernameField);
	userPanel.add(nextButton);
  }
  
  
  //the panel that displays the verify user interface. displays main if verification works
  private void makeVerifyPanel(String username) {
    verifyPanel = new JPanel();
    verifyPanel.setLayout(new GridBagLayout());
    
    //create fields for collecting info

    JLabel passwordLabel = new JLabel("passwords:");

    
    String[] userPasswords = uMan.getUserPasswords(username);
    
    //create button to collect info and pass info onto UserManager for verification
    JButton verifyButton = new JButton("Verify");
    verifyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
		if (p == Constants.MAX_PASS_NUM) {
			boolean result = uMan.verifyUser(username, passwordsToCheck);
			for (int i = 0; i < p; i++) {
				passwordsToCheck[i] = "";
			}
			p = 0;
			if (result == true) {
			  displayPanel(mainPanel);
			} 
		} else {
			DisplayRound(userPasswords[p], username, userPasswords);
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
    verifyPanel.add(passwordLabel);

	File path = new File("images");
    File[] files = path.listFiles();
    

	DisplayRound(userPasswords[p], username, userPasswords);
  }
  
  
  private void DisplayRound(String password, String username, String[] userPasswords) {
	  	File path = new File("images");
    File[] files = path.listFiles();
	  verifyPanel.removeAll();
	  JPanel imageSpace = new JPanel();
	  imageSpace.add(makeClickImage(password));
	  for (int i = 0; i < 9; i ++) {
		imageSpace.add(makeClickImage(files[rand.nextInt(100)].getName()));
		}
		
		JButton verifyButton = new JButton("Verify");
    verifyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
		if (p == Constants.MAX_PASS_NUM) {
			boolean result = uMan.verifyUser(username, passwordsToCheck);
			for (int i = 0; i < p; i++) {
				passwordsToCheck[i] = "";
			}
			p = 0;
			if (result == true) {
			  displayPanel(mainPanel);
			} 
		} else {
			DisplayRound(userPasswords[p], username, userPasswords);
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
	  verifyPanel.add(imageSpace);
	  verifyPanel.add(verifyButton);
	  verifyPanel.add(homeButton);
	  verifyPanel.revalidate();
	  verifyPanel.repaint();
  }
  
  
  //makes clickable image
  private JLabel makeClickImage(final String imageName) {
    ImageIcon image = new ImageIcon("images/" + imageName);
    JLabel imgLabel = new JLabel(image);
    imgLabel.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e) {
        passwordsToCheck[p] = imageName;
        p++;
      }
    });
    return imgLabel;
  }

  
  //generic method to switch the displayed panel to provided panel
  private void displayPanel(JPanel display) {
	  for (int i = 0; i < p; i++) {
			passwordsToCheck[i] = "";
		}
		p = 0;
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
