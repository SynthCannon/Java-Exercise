/*
 * Incredibly simple log in panel, no files or textfield==X used, just log in.
 * Uses JPasswordField for security purposes, ready for future development.
 */


package create.gui;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel{

	static final long serialVersionUID = 1L;
	
	private JButton loginButton;
	private JPanel loginPanel;
	private JTextField usernameTxtField;
	private JPasswordField passwordField;

	public void loadPanel(JFrame frame,JPanel movieSelPanel) {
		
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 434, 261);
		loginPanel.setLayout(null);
		loginPanel.setVisible(true);
		frame.getContentPane().add(loginPanel); 
		
		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setBounds(108, 75, 81, 21);
		usernameLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginPanel.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setBounds(108, 107, 77, 21);
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginPanel.add(passwordLbl);
		
		usernameTxtField = new JTextField();
		usernameTxtField.setBounds(199, 72, 134, 27);
		usernameTxtField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginPanel.add(usernameTxtField);
		
		// Specific password field so input letters are censored and also secure
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 107, 134, 27);
		loginPanel.add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(199, 142, 88, 29);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (credentialsCorrect(usernameTxtField.getText(), passwordField.getPassword())) {
					loginPanel.setVisible(false);
					movieSelPanel.setVisible(true);
				}
				else {} // incorrect login (impossible)
				passwordField.setText(""); // For security purposes
			}
		});
		loginPanel.add(loginButton);
	}
	
	public void setVisible(boolean arg) {
		loginPanel.setVisible(arg);
	}
	
	
	private boolean credentialsCorrect(String a, char[] b) {
		/* 
		 * Here we would check if a username matches the password in the accounts database
		 * however we are not including file i/o for time restraint purposes
		 */
		Arrays.fill(b, '0'); // For security purposes
		return true;
	}
	
}
