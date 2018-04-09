/*
 * This allows you to select which viewing the customers wants to see.
 * It catches the radio button selection and prepares the seat buttons for the next screen.
 * 
 * The customer can be told which viewing is available and then they make their choice.
 */

package create.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import create.gui.CinemaData;

public class ViewingSelPanel extends JPanel {
	
	static final long serialVersionUID = 1L;
	
	private JPanel viewingSelPanel = new JPanel();
	public JRadioButton viewRadio0800 = new JRadioButton("0800");
	public JRadioButton viewRadio1100 = new JRadioButton("1100");
	public JRadioButton viewRadio1400 = new JRadioButton("1400");
	public JRadioButton viewRadio1700 = new JRadioButton("1700");
	public JRadioButton viewRadio2000 = new JRadioButton("2000");
	public ButtonGroup buttonGroup = new ButtonGroup();
	
	public void loadPanel(JFrame frame, CinemaData cinemaData, MovieSelPanel movieSelPanel, PersonsSelPanel personsSelPanel, SeatsSelPanel seatsSelPanel) {
		
		
		viewingSelPanel.setBounds(0, 0, 434, 261);
		viewingSelPanel.setVisible(false);
		viewingSelPanel.setLayout(null);
		frame.getContentPane().add(viewingSelPanel);

		viewRadio0800.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewRadio0800.setBounds(193, 54, 216, 23);
		viewingSelPanel.add(viewRadio0800);
				
		viewRadio1100.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewRadio1100.setBounds(193, 80, 216, 23);
		viewingSelPanel.add(viewRadio1100);
				
		viewRadio1400.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewRadio1400.setBounds(193, 105, 216, 23);
		viewingSelPanel.add(viewRadio1400);
				
		viewRadio1700.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewRadio1700.setBounds(193, 129, 216, 23);
		viewingSelPanel.add(viewRadio1700);
				
		viewRadio2000.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewRadio2000.setBounds(193, 153, 216, 23);
		viewingSelPanel.add(viewRadio2000);
		
		
		buttonGroup.add(viewRadio0800);
		buttonGroup.add(viewRadio1100);
		buttonGroup.add(viewRadio1400);
		buttonGroup.add(viewRadio1700);
		buttonGroup.add(viewRadio2000);
				
		JButton selectViewButton = new JButton("Select");
		selectViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonGroup.isSelected(null)==true) {
					JOptionPane.showMessageDialog(frame, "Please select a movie.");
				}
				else {
					// Set available seats, no function found to get selected index found in buttongroup
					if(viewRadio0800.isSelected()==true) {
						cinemaData.viewing = 0;
					} else if(viewRadio1100.isSelected()==true) {
						cinemaData.viewing = 1;
					} else if(viewRadio1400.isSelected()==true) {
						cinemaData.viewing = 2;
					} else if(viewRadio1700.isSelected()==true) {
						cinemaData.viewing = 3;
					} else {
						cinemaData.viewing = 4;
					}

					cinemaData.selected = movieSelPanel.movieList.getSelectedIndex();
					//Set seats available in next panel
					for(int i=0;i<4;i++) {
						for (int j=0;j<4;j++) {
							seatsSelPanel.seatBtns.get(i*4+j).setEnabled(cinemaData.seats[cinemaData.selected][cinemaData.viewing][i*4+j]);
							if(cinemaData.seats[cinemaData.selected][cinemaData.viewing][i*4+j] == false) {
								seatsSelPanel.seatBtns.get(i*4+j).setBackground(new Color (200,0,0));
							}
						}
					}
							
					viewingSelPanel.setVisible(false);
					seatsSelPanel.setVisible(true);
				}
						
			}
		});
		selectViewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectViewButton.setBounds(249, 221, 93, 29);
		viewingSelPanel.add(selectViewButton);
				
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				buttonGroup.clearSelection();
				viewingSelPanel.setVisible(false);
				personsSelPanel.setVisible(true);
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backButton.setBounds(121, 221, 93, 29);
		viewingSelPanel.add(backButton);
		
		
	}

	public void setVisible(boolean arg) {
		viewingSelPanel.setVisible(arg);
	}

}
