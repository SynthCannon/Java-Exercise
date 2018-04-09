/*
 * Stores variables to avoid using global variables. 
 * Also creates random seat data and makeReceipt provides text for a receipt text pane.
 */

package create.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JList;


import create.gui.MovieSelPanel;
import create.gui.PersonsSelPanel;
import create.gui.ViewingSelPanel;
import create.gui.SeatsSelPanel;
import create.gui.SalePanel;


public class CinemaData {
	public boolean seats[][][]; // For storing allocated seats
	public int seatsRemaining[][]; // For storing an integer regarding the seats remaining for each viewing of movies
	public int totalPeople; // Total people in sale
	public int totalPrice=0; // Total price for sale
	public int selectNumber; // number of seats selected
	public int viewing; // stores which viewing the current customers wish to see
	public int selected=0; // stores the current allocated number of seats

	
	Calendar c = Calendar.getInstance();
	private int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	public int discountDay=0;


	public void initializeData(JList<String> movieList){
		if(dayOfWeek == 4) {
			discountDay=1;
		}

		/* Create array containing seats booked and remaining seats
		 * (and fill with random data upon initialization for testing without file i/o
		 */
		seats = new boolean[movieList.getModel().getSize()][5][16];
		seatsRemaining = new int[movieList.getModel().getSize()][5];
		
		// Fill seatsRemaining with the value 16 (to be subtracted from)
		for (int i=0;i<movieList.getModel().getSize();i++) {
			for (int j=0;j<5;j++) {
				seatsRemaining[i][j] = 16;
			}
		}
		
		Random store = new Random();
		for(int x=0;x<movieList.getModel().getSize();x++) {
			for(int y=0;y<5;y++) {
				for(int z=0;z<16;z++) {
					
					// Make ~75% seats available
					if(store.nextBoolean()==false) {
						seats[x][y][z] = store.nextBoolean();
					}
					else {
						seats[x][y][z] = true;
					}
					
					if(seats[x][y][z] == false) {
						seatsRemaining[x][y]--;
					}
				}
				
			}
		}
		
		
	}
	
	
	
	public String makeReceipt(MovieSelPanel movieSelPanel, PersonsSelPanel personsSelPanel, ViewingSelPanel viewingSelPanel, ArrayList<JButton> seatBtns) {
		/*
		 * This function is to set the text in the receipt textPane
		 * Resets after each sale
		 * Finds: number of people, movie name, movie time, seats allocated
		 * Counts: different people sales
		 * 
		 * A modification I would have made provided I had more time would be to 
		 * have the sale update a file of allocated seats
		 */
		
		String timeViewing, adultNum="",oapNum="",studentNum="",childNum="",seatsString="";
		
 		// find which time being viewed
		if(viewingSelPanel.viewRadio0800.isSelected()==true) {
			timeViewing = "08:00";
		}
		else if(viewingSelPanel.viewRadio1100.isSelected()==true) {
			timeViewing = "11:00";
		}
		else if(viewingSelPanel.viewRadio1400.isSelected()==true) {
			timeViewing = "14:00";
		}
		else if(viewingSelPanel.viewRadio1700.isSelected()==true) {
			timeViewing = "17:00";
		}
		else{
			timeViewing = "20:00";
		}
		
		// Get number of people in different pay brackets and use data for string
		if((Integer)personsSelPanel.adultSpinner.getValue() != 0) {
			if((Integer)personsSelPanel.adultSpinner.getValue() == 1) {
				adultNum = "1x Adult\t\t\u00A3" + 1*(8-discountDay*2) + "\r";
			}
			else {
				adultNum = personsSelPanel.adultSpinner.getValue() + "x Adults\t\t\u00A3" + (Integer)personsSelPanel.adultSpinner.getValue()*(8-discountDay*2) + "\r";
			}
		}
		if((Integer)personsSelPanel.oapSpinner.getValue() != 0) {
			if((Integer)personsSelPanel.oapSpinner.getValue() == 1) {
				oapNum = "1x OAP\t\t\u00A3" + 1*(6-discountDay*2) + "\r";
			}
			else {
				oapNum = personsSelPanel.oapSpinner.getValue() + "x OAP's\t\t\u00A3" + (Integer)personsSelPanel.oapSpinner.getValue()*(6-discountDay*2) + "\r";
			}
		}
		if((Integer)personsSelPanel.studentSpinner.getValue() != 0) {
			if((Integer)personsSelPanel.studentSpinner.getValue() == 1) {
				studentNum = "1x Student\t\u00A3" + 1*(6-discountDay*2) + "\r";
			}
			else {
				studentNum = personsSelPanel.studentSpinner.getValue() + "x Students\t\u00A3" + (Integer)personsSelPanel.studentSpinner.getValue()*(6-discountDay*2) + "\r";
			}
		}
		if((Integer)personsSelPanel.childSpinner.getValue() != 0) {
			if((Integer)personsSelPanel.childSpinner.getValue() == 1) {
				childNum = "1x Child\t\t\u00A3" + 1*(4-discountDay*2) + "\r";
			}
			else {
				childNum = personsSelPanel.childSpinner.getValue() + "x Children\t\u00A3" + (Integer)personsSelPanel.childSpinner.getValue()*(4-discountDay*2) + "\r";
			}
		}
		
		// Find which seats booked.
		int i=1;
		for(JButton button : seatBtns) {
			if(button.getBackground().getBlue() == 100) {
				if(seatsString=="") {
					seatsString = Integer.toString(i);
				}
				else {
					seatsString = seatsString + ", " + Integer.toString(i);
				}
			}
			i++;
		}
		
		// Set text to involve anything you would find on a receipt
		return (totalPeople + " to see " + movieSelPanel.movieList.getSelectedValue() + "\r"
				+ "at time " + timeViewing +"\r\r"
				+ adultNum + oapNum + studentNum + childNum
				+ "Total:\t\t\u00A3" + totalPrice + "\r\r"
				+ "Seats: " + seatsString
				);
	}
}
