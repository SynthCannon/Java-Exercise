/*
 * This class provides the sale panel which presents the user 
 * with all selections made for this sale, and can execute a sale which
 * allocates the customers their seats.
 */

package create.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import create.gui.CinemaData;

public class SalePanel extends JPanel {
	
	static final long serialVersionUID = 1L;
	
	public JPanel salePanel = new JPanel();
	public JTextPane textPane = new JTextPane();

	public void loadPanel(JFrame frame, CinemaData cinemaData, MovieSelPanel movieSelPanel, PersonsSelPanel personsSelPanel, ViewingSelPanel viewingSelPanel, SeatsSelPanel seatsSelPanel) {
		
		
		salePanel.setBounds(0, 0, 434, 261);
		salePanel.setLayout(null);
		salePanel.setVisible(false);
		frame.getContentPane().add(salePanel);
		
		
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane.setBounds(10, 21, 302, 217);
		salePanel.add(textPane);
		
		// Button to execute a sale
		JButton saleBtn = new JButton("Sale");
		saleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Set global variable seats boolean for which seats are now taken
				for(int x=0;x<16;x++) {
					if(seatsSelPanel.seatBtns.get(x).getBackground().getBlue() == 100) {
						cinemaData.seats[movieSelPanel.movieList.getSelectedIndex()][cinemaData.viewing][x]=false;
					}
				}
				cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][cinemaData.viewing]-=cinemaData.totalPeople;
				JOptionPane.showMessageDialog(frame, "Sale has been made.");
				
				// Reset to defaults
				cinemaData.totalPrice=0;
				cinemaData.viewing=0;
				cinemaData.selectNumber=0;
				cinemaData.totalPeople=0;
				cinemaData.totalPrice=0;
				viewingSelPanel.buttonGroup.clearSelection();
				movieSelPanel.movieList.clearSelection();
				personsSelPanel.adultSpinner.setValue(0);
				personsSelPanel.oapSpinner.setValue(0);
				personsSelPanel.studentSpinner.setValue(0);
				personsSelPanel.childSpinner.setValue(0);
				for(JButton button : seatsSelPanel.seatBtns) {
					button.setEnabled(true);
					button.setBackground(new Color(238,238,238));
				}
				
				salePanel.setVisible(false);
				movieSelPanel.setVisible(true);
			}
		});
		saleBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		saleBtn.setBounds(335, 58, 89, 23);
		salePanel.add(saleBtn);
		
		JButton saleBackBtn = new JButton("Back");
		saleBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
				salePanel.setVisible(false);
				seatsSelPanel.setVisible(true);
			}
		});
		saleBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		saleBackBtn.setBounds(335, 92, 89, 23);
		salePanel.add(saleBackBtn);
		
	}
	
	public void setText(String text) {
		textPane.setText(text);
	}
	
	public void setVisible(boolean arg) {
		salePanel.setVisible(arg);
	}
}
