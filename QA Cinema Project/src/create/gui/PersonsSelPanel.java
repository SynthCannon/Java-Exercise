/*
 * This panel allows you to select which customer types and amounts
 * Each spinner changes the total price global variable and changes the price label
 * 
 * The idea is, after selecting X, the staff will ask "is this Y adults and Z children"
 * then the customer can answer "Yes but J adult(s) is a student".
 */

package create.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import create.gui.CinemaData;

public class PersonsSelPanel extends JPanel {
	
	static final long serialVersionUID = 1L;
	
	public JPanel personsSelPanel = new JPanel();
	public JSpinner adultSpinner = new JSpinner();
	public JSpinner oapSpinner = new JSpinner();
	public JSpinner studentSpinner = new JSpinner();
	public JSpinner childSpinner = new JSpinner();
	private JLabel totalPriceLbl;
	
	
	public void loadPanel(JFrame frame, CinemaData cinemaData, MovieSelPanel movieSelPanel, ViewingSelPanel viewingSelPanel) {
		
		personsSelPanel.setBounds(0, 0, 434, 261);
		personsSelPanel.setLayout(null);
		personsSelPanel.setVisible(false);
		frame.getContentPane().add(personsSelPanel);
		

		JLabel adultLabel = new JLabel("Adult");
		adultLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		adultLabel.setBounds(109, 52, 90, 17);
		personsSelPanel.add(adultLabel);
		
		JLabel oapLabel = new JLabel("OAP");
		oapLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		oapLabel.setBounds(109, 85, 90, 17);
		personsSelPanel.add(oapLabel);
		
		JLabel studentLabel = new JLabel("Student");
		studentLabel.setBounds(109, 117, 90, 17);
		personsSelPanel.add(studentLabel);
		studentLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel childLabel = new JLabel("Child");
		childLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		childLabel.setBounds(109, 148, 90, 17);
		personsSelPanel.add(childLabel);
		
		JLabel adultPriceLbl = new JLabel("\u00A3" + (8-cinemaData.discountDay));
		adultPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		adultPriceLbl.setBounds(241, 52, 37, 17);
		personsSelPanel.add(adultPriceLbl);
		
		JLabel oapPriceLbl = new JLabel("\u00A3" + (6-cinemaData.discountDay));
		oapPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		oapPriceLbl.setBounds(241, 85, 37, 17);
		personsSelPanel.add(oapPriceLbl);
		
		JLabel studentPriceLbl = new JLabel("\u00A3" + (6-cinemaData.discountDay));
		studentPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		studentPriceLbl.setBounds(241, 117, 37, 17);
		personsSelPanel.add(studentPriceLbl);
 		
		JLabel childPriceLbl = new JLabel("\u00A3" + (4-cinemaData.discountDay));
		childPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		childPriceLbl.setBounds(241, 148, 37, 17);
		personsSelPanel.add(childPriceLbl);
		
		totalPriceLbl = new JLabel("Total: \u00A30");
		totalPriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		totalPriceLbl.setBounds(305, 148, 90, 17);
		personsSelPanel.add(totalPriceLbl);
		
		
		
		adultSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateTotal(cinemaData);
			}
		});
		adultSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		adultSpinner.setBounds(202, 53, 29, 20);
		personsSelPanel.add(adultSpinner);
		
		oapSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateTotal(cinemaData);
			}
		});
		oapSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		oapSpinner.setBounds(202, 82, 29, 20);
		personsSelPanel.add(oapSpinner);
			
		studentSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateTotal(cinemaData);
			}
		});
		studentSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		studentSpinner.setBounds(202, 115, 29, 20);
		personsSelPanel.add(studentSpinner);
		
		childSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateTotal(cinemaData);
			}
		});
		childSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		childSpinner.setBounds(202, 145, 29, 20);
		personsSelPanel.add(childSpinner);
		
	    JButton personSelBtn = new JButton("Select");
		personSelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cinemaData.totalPeople = (Integer)adultSpinner.getValue() + (Integer)oapSpinner.getValue() + 
						(Integer)studentSpinner.getValue() + (Integer)childSpinner.getValue();
				if(cinemaData.totalPeople==0) {
					JOptionPane.showMessageDialog(frame, "Please input some values.");
				}
				else {
					viewingSelPanel.viewRadio0800.setText("0800 (" + Integer.toString(cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][0]) + " Remaining)");
					viewingSelPanel.viewRadio1100.setText("1100 (" + Integer.toString(cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][1]) + " Remaining)");
					viewingSelPanel.viewRadio1400.setText("1400 (" + Integer.toString(cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][2]) + " Remaining)");
					viewingSelPanel.viewRadio1700.setText("1700 (" + Integer.toString(cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][3]) + " Remaining)");
					viewingSelPanel.viewRadio2000.setText("2000 (" + Integer.toString(cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][4]) + " Remaining)");
					
					// Find number of seats remaining and disable overbooked viewings
					if(16 - cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][0] + cinemaData.totalPeople >16) {
						viewingSelPanel.viewRadio0800.setEnabled(false);
					}
					if(16 - cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][1] + cinemaData.totalPeople >16) {
						viewingSelPanel.viewRadio1100.setEnabled(false);
					}
					if(16 - cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][2] + cinemaData.totalPeople >16) {
						viewingSelPanel.viewRadio1400.setEnabled(false);
					}
					if(16 - cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][3] + cinemaData.totalPeople >16) {
						viewingSelPanel.viewRadio1700.setEnabled(false);
					}
					if(16 - cinemaData.seatsRemaining[movieSelPanel.movieList.getSelectedIndex()][4] + cinemaData.totalPeople >16) {
						viewingSelPanel.viewRadio2000.setEnabled(false);
					}
					
					personsSelPanel.setVisible(false);
					viewingSelPanel.setVisible(true);
				}
			}
		});
		personSelBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		personSelBtn.setBounds(237, 210, 89, 23);
		personsSelPanel.add(personSelBtn);
		
		
		JButton personBackBtn = new JButton("Back");
		personBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// re-initialise values
				adultSpinner.setValue(0);
				oapSpinner.setValue(0);
				studentSpinner.setValue(0);
				childSpinner.setValue(0);
				movieSelPanel.movieList.clearSelection();
				
				personsSelPanel.setVisible(false);
				movieSelPanel.setVisible(true);
			}
		});
		personBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		personBackBtn.setBounds(107, 210, 89, 23);
		personsSelPanel.add(personBackBtn);

	}
	
	private void updateTotal(CinemaData cinemaData) {
		cinemaData.totalPrice = (Integer)adultSpinner.getValue()*(8-2*cinemaData.discountDay) + (Integer)oapSpinner.getValue()*(6-2*cinemaData.discountDay) + 
				(Integer)studentSpinner.getValue()*(6-2*cinemaData.discountDay) + (Integer)childSpinner.getValue()*(4-2*cinemaData.discountDay); 
		totalPriceLbl.setText("Total: \u00A3" + cinemaData.totalPrice);
	}
	

	public void setVisible(boolean arg) {
		personsSelPanel.setVisible(arg);
	}
}

