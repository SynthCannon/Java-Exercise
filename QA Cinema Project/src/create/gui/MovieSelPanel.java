/*
 * This panel allows a movie to be selected
 * The idea is the customers will come up and say
 * "we want to see X" or "two to see X", then the 
 * staffmember can select X.
 */

package create.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;


public class MovieSelPanel extends JPanel{
	
	static final long serialVersionUID = 1L;
	
	public JList<String> movieList = new JList<String>();
	private JPanel movieSelPanel;
	private JButton selectButton;

	public void loadPanel(JFrame frame, JPanel personsSelPanel) {
		
		movieSelPanel = new JPanel();
		movieSelPanel.setBounds(0, 0, 434, 261);
		movieSelPanel.setLayout(null);
		movieSelPanel.setVisible(false);
		frame.getContentPane().add(movieSelPanel);
		
		
		movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		movieList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		movieList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			String[] movies = new String[] {"Toy Story", "The Man From The Earth", "Dead Mans Shoes", "Annihilation", "John Wick", "The Witch", "Your Name", "Two Brothers"};
			public int getSize() {
				return movies.length;
			}
			public String getElementAt(int index) {
				return movies[index];
			}
		});
		movieList.setBounds(10, 11, 397, 199);
		movieSelPanel.add(movieList);
		//RandomSeatsData();
		
		
		selectButton = new JButton("Select");
		selectButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectButton.setBounds(151, 220, 116, 30);
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (movieList.isSelectionEmpty() == true) {
					JOptionPane.showMessageDialog(frame, "Please select a movie.");
				}
				else {
					movieSelPanel.setVisible(false);
					personsSelPanel.setVisible(true);
				}
			}
		});
		movieSelPanel.add(selectButton);
	}
	
	public void setVisible(boolean arg) {
		movieSelPanel.setVisible(arg);
	}


}
