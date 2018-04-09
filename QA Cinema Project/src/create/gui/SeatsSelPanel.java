/*
 * This class sets up a seats selection panel.
 * It takes values from the seats boolean array for allocated seats data.
 * 
 * The customers can finally choose which seats to allocate them before accepting.
 */

package create.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import create.gui.CinemaData;


public class SeatsSelPanel extends JPanel {
	
	static final long serialVersionUID = 1L;

	public JPanel seatsSelPanel = new JPanel();
	public ArrayList<JButton> seatBtns = new ArrayList<JButton>();
	
	
	public void loadPanel(JFrame frame, CinemaData cinemaData, MovieSelPanel movieSelPanel, PersonsSelPanel personsSelPanel, ViewingSelPanel viewingSelPanel, SalePanel salePanel) {
		
		seatsSelPanel.setBounds(0, 0, 434, 261);
		seatsSelPanel.setLayout(null);
		seatsSelPanel.setVisible(false);
		frame.getContentPane().add(seatsSelPanel);
		
		JLabel lblNewLabel_1 = new JLabel("Screen");
		lblNewLabel_1.setLocation(177, 11);
		lblNewLabel_1.setSize(80, 30);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		seatsSelPanel.add(lblNewLabel_1, "cell 3 0,grow");
		
		// Make array of buttons for seats to select using the ArrayList seatBtns
		//selectNumber = 0;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				seatBtns.add(new JButton(Integer.toString(i*4+j+1)));
				seatBtns.get(i*4+j).setBounds(j*100+27, i*40+54, 80, 23);
				seatBtns.get(i*4+j).setBackground(new Color(238 ,238,238));
				seatsSelPanel.add(seatBtns.get(i*4+j));
			}
		} 
	
		// Give seat buttons the same functionality
		for (JButton button: seatBtns) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seatButtonPress(frame, button, cinemaData);
				}
			});
		}

		JButton seatBackBtn = new JButton("Back");
		seatBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cinemaData.selectNumber = 0;
				// Reset seat buttons
				for(JButton button : seatBtns) {
					button.setEnabled(true);
					button.setBackground(new Color(238,238,238));
				}
				seatsSelPanel.setVisible(false);
				viewingSelPanel.setVisible(true);
				
			}
		});
		seatBackBtn.setLocation(100, 220);
		seatBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		seatBackBtn.setSize(80, 30);
		seatsSelPanel.add(seatBackBtn, "cell 2 5,alignx center,growy");
				
		JButton seatsSelBtn = new JButton("Select");
		seatsSelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cinemaData.selectNumber == cinemaData.totalPeople) {
					seatsSelPanel.setVisible(false);
					salePanel.setVisible(true);
					salePanel.setText(cinemaData.makeReceipt(movieSelPanel, personsSelPanel, viewingSelPanel, seatBtns));	
				}
				else {
					JOptionPane.showMessageDialog(frame, "Not enough selected");
				}
			}
		});
		seatsSelBtn.setLocation(263, 220);
		seatsSelBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		seatsSelBtn.setSize(80, 30);
		seatsSelPanel.add(seatsSelBtn, "cell 4 5,grow");
	}
	
	private void seatButtonPress(JFrame frame, JButton button, CinemaData cinemaData) {
		/*
		 * Method to change color of button for allocating seats.
		 * Produces an error message if too many selected.
		 */
		
		if(button.getBackground().getBlue() == 238) {
			if(cinemaData.selectNumber < cinemaData.totalPeople) {
				button.setBackground(new Color(0,200,100));
				cinemaData.selectNumber++;
			}
			else {
				JOptionPane.showMessageDialog(frame, "Too many people");
				//return n;
			}
		}
		else {
			button.setBackground(new Color(238,238,238));
			cinemaData.selectNumber--;
		}
	}
	
	public void setVisible(boolean arg) {
		seatsSelPanel.setVisible(arg);
	}
}
