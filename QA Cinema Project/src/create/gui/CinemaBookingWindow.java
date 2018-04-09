/*
 * Cinema Booking System by Nicholas Sumner (8/4/18)
 * 
 * This system will create random allocated seats upon execution
 * as I did not have time to give file allocation. 
 * I have a selection of some of my favourite movies, shown 5 times a day 
 * (each movie has its own room in my imaginary cinema so no need to allocate that). 
 * The different persons can be selected, and seats can be allocated. 
 * I have not rigourously tested the inputs but I think that it rejects most invalid 
 * usages and functions correctly.
 * The username and password field is pointless, no database behind it so you can login
 * without entering anything. It was a nice test at the start when I was learning how to
 * use Eclipse.
 * 
 * 
 * I have found java to be a fairly different language to C++ with the same generic language structure.
 * I would have used pointers within class non-object values had I coded in C++ (and had time),
 * not sure if I missed something. I will look into more differences later when I have time.
 * 
 */

package create.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.lang.String;

import create.gui.CinemaData;


public class CinemaBookingWindow {

	private JFrame frame = new JFrame();
	private CinemaData cinemaData = new CinemaData();
	private LoginPanel loginPanel = new LoginPanel();
	private MovieSelPanel movieSelPanel = new MovieSelPanel();
	private PersonsSelPanel personsSelPanel = new PersonsSelPanel();
	private ViewingSelPanel viewingSelPanel = new ViewingSelPanel();
	private SeatsSelPanel seatsSelPanel = new SeatsSelPanel();
	private SalePanel salePanel = new SalePanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CinemaBookingWindow window = new CinemaBookingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CinemaBookingWindow() {
		initialize();
	}

	
	private void initialize() {
		/*
		 * Create a frame and call a function to build panels for different layouts
		 */
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Very simple login panel, just press log in
		loginPanel.loadPanel(frame, movieSelPanel);
				
		// Select which movie the customer(s) want to  see
		movieSelPanel.loadPanel(frame, personsSelPanel);
				
		// Initialize data, would be done first but no data file used so using movieSelPanel's movieList contents
		cinemaData.initializeData(movieSelPanel.movieList);
				
		// Select the customers seeing the movie
		personsSelPanel.loadPanel(frame, cinemaData, movieSelPanel, viewingSelPanel);
				
		// Select which viewing of the movie they want to see
		viewingSelPanel.loadPanel(frame, cinemaData, movieSelPanel, personsSelPanel, seatsSelPanel);

		// Allocate them seats
		seatsSelPanel.loadPanel(frame, cinemaData, movieSelPanel, personsSelPanel, viewingSelPanel, salePanel);

		// View the receipt and make a sale
		salePanel.loadPanel(frame, cinemaData, movieSelPanel, personsSelPanel, viewingSelPanel, seatsSelPanel);
	}
	
 }
