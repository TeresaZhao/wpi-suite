/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;


public class WeekPane extends JPanel implements ICalPane {
	JPanel mainPanel = new JPanel();
	Calendar mydate;
	TeamCalendar calendarused;
	
    /**
    * Create the panel.
    */
	public WeekPane(Calendar datecalendar, TeamCalendar tcalendar) {
		mydate = (Calendar)datecalendar.clone();
		calendarused = tcalendar;
	   	while(mydate.get(Calendar.DAY_OF_WEEK) != mydate.getFirstDayOfWeek() ){
	   		mydate.add(Calendar.DATE, -1);
	   	}

	   	
	   	if(tcalendar.getShowCommitements()){
	   		setLayout(new GridLayout(1,1));
	   		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	   		this.setMinimumSize(new Dimension(1200,1000));
	   		this.add(splitpane);
	   		
	   		JScrollPane scrollPane = new JScrollPane(mainPanel, 
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	   		scrollPane.setBorder(BorderFactory.createEmptyBorder());
			scrollPane.setMinimumSize(new Dimension(700, 300));
			JPanel finalPane = new JPanel();
			finalPane.setLayout(new BoxLayout(finalPane, BoxLayout.Y_AXIS));
			finalPane.add(this.getHeader((int)mainPanel.getPreferredSize().getWidth()));
			finalPane.add(scrollPane);
			
			
			splitpane.setLeftComponent(finalPane);
			
			SpringLayout layout = new SpringLayout();
			mainPanel.setLayout(layout);
			mainPanel.setPreferredSize(new Dimension(30, 2000));
			
			scrollPane.setRowHeaderView(getTimesBar(mainPanel.getPreferredSize().getHeight()));
			scrollPane.getVerticalScrollBar().setValue(800);
			
			JComponent days = getDays();
			layout.putConstraint(SpringLayout.WEST, days, 0, SpringLayout.WEST, mainPanel);
			layout.putConstraint(SpringLayout.NORTH, days, 0, SpringLayout.NORTH, mainPanel);
			layout.putConstraint(SpringLayout.SOUTH, days, 0, SpringLayout.SOUTH, mainPanel);
			layout.putConstraint(SpringLayout.EAST, days, 0, SpringLayout.EAST, mainPanel);
			mainPanel.add(days);
			
			//scrollPane.setColumnHeaderView(getHeader((int)mainPanel.getPreferredSize().getWidth()));
			
			scrollPane.revalidate(); 
			
			splitpane.setBottomComponent(getCommits());
	   	}
	   	else{
	   		
			setLayout(new GridLayout(1,1));
			
			      // HOURS
			JScrollPane scrollPane = new JScrollPane(mainPanel, 
											ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
											ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			scrollPane.setMinimumSize(new Dimension(700, 300));
			JPanel finalPane = new JPanel();
			finalPane.setLayout(new BoxLayout(finalPane, BoxLayout.Y_AXIS));
			finalPane.add(this.getHeader((int)mainPanel.getPreferredSize().getWidth()));
			finalPane.add(scrollPane);
			add(finalPane);
			      
			SpringLayout layout = new SpringLayout();
			mainPanel.setLayout(layout);
			mainPanel.setPreferredSize(new Dimension(30, 2000));
			      
			scrollPane.setRowHeaderView(getTimesBar(mainPanel.getPreferredSize().getHeight()));
			scrollPane.getVerticalScrollBar().setValue(800);
			      
			JComponent days = getDays();
			layout.putConstraint(SpringLayout.WEST, days, 0, SpringLayout.WEST, mainPanel);
			layout.putConstraint(SpringLayout.NORTH, days, 0, SpringLayout.NORTH, mainPanel);
			layout.putConstraint(SpringLayout.SOUTH, days, 0, SpringLayout.SOUTH, mainPanel);
			layout.putConstraint(SpringLayout.EAST, days, 0, SpringLayout.EAST, mainPanel);
			mainPanel.add(days);
				    
			//scrollPane.setColumnHeaderView(getHeader((int)mainPanel.getPreferredSize().getWidth()));
				    
			scrollPane.revalidate(); 
	   	}
	}

    protected JComponent getHeader(int width){
    	String[] weekdays = {"Sunday, ", "Monday, ", "Tuesday, ",
    						"Wednesday, ", "Thursday, ", "Friday, ", "Saturday, " };
    	int initial = mydate.get(Calendar.DATE);
    	
    	for(int i=0; i < 7; i++) {
    		weekdays[i] += (initial + i);
    	}
    	
    	JPanel apane = new JPanel();
    	SpringLayout layout = new SpringLayout();
    	GridLayout g = new GridLayout(1,7);
    	
    	apane.setLayout(g);
    	Border empty = new EmptyBorder(0, 50, 0, 0);
    	apane.setBorder(empty);
    	int height = 0;
    	
	    for(int i = 0; i<7; i++){
	    	JLabel alab = new JLabel(weekdays[i], SwingConstants.CENTER);
	    	alab.setFont(new Font("SansSerif", 1, 12));
	    	apane.add( alab );
	    	height = (int) new JLabel(weekdays[i]).getPreferredSize().getHeight();
	    }

    	apane.setMinimumSize(new Dimension(700, 30));
    
    	return apane;
    }
    
    protected JComponent getDays(){
    	JPanel apane = new JPanel();
	    apane.setLayout(new GridLayout(1,7));
    	String[] weekdays = {"Sunday, ", "Monday, ", "Tuesday, ",
				"Wednesday, ", "Thursday, ", "Friday, ", "Saturday, " };
    	int initial = mydate.get(Calendar.DATE);
    	
       	for(int i=0; i < 7; i++) {
    		weekdays[i] += (initial + i);
    	}
    	
	    for(int i = 0; i<7; i++){
	    	Calendar acal = (Calendar)mydate.clone();
	    	acal.add(Calendar.DATE, i);
//<<<<<<< HEAD
//	    	JComponent aday = new DetailedDay(acal);
//=======
	    	JLayeredPane aday = new DetailedDay(acal,weekdays[i]);
//>>>>>>> 8d54f788e1fec6a7eab547aca6afdaba2701252d
	    	aday.addMouseListener(new AMouseEvent(acal, calendarused));
	    	apane.add( aday );
	    }
    	return apane;
    }
    
    protected JComponent getCommits(){
    	JPanel firstpane = new JPanel();
    	JPanel secondpane = new JPanel();
    	SpringLayout layout = new SpringLayout();
    	firstpane.setLayout(layout);
    	//System.out.println(getTimesBar(100.0).getPreferredSize().width);
		layout.putConstraint(SpringLayout.WEST, secondpane, 
								getTimesBar(100.0).getPreferredSize().width,
								SpringLayout.WEST, firstpane);
		layout.putConstraint(SpringLayout.NORTH, secondpane, 0, SpringLayout.NORTH, firstpane);
		layout.putConstraint(SpringLayout.SOUTH, secondpane, 0, SpringLayout.SOUTH, firstpane);
		layout.putConstraint(SpringLayout.EAST, secondpane, -15, SpringLayout.EAST, firstpane);
    	firstpane.add(secondpane);
    	
		secondpane.setLayout(new GridLayout(1, 7, 0, 5));
    	
	    for(int i = 0; i<7; i++){
	    	Calendar acal = (Calendar)mydate.clone();
	    	acal.add(Calendar.DAY_OF_MONTH, 1);
	    	//TODO add function
	    	//JPanel viewpane = getWeekCommitPaneforDate( acal );
	    	
	    	CommitmentView commits = new CommitmentView(calendarused);
	    	
	    	ArrayList<Commitment> comList = new ArrayList<Commitment>(calendarused.getCalData().getCommitments().getCommitments());
	    	ArrayList<Commitment> newList = new ArrayList<Commitment>();
	    	for (Commitment c : comList) {
	    		if ((c.getDueDate().getDate()  == acal.get(Calendar.DAY_OF_MONTH) ||
	    			 c.getDueDate().getMonth() == acal.get(Calendar.MONTH)) ||
	    			 c.getDueDate().getYear()  == acal.get(Calendar.YEAR)) {
	    			newList.add(c);
	    		}
	    	}
	    	
	    	commits.setCommList(newList);
	    	
			JScrollPane ascrollpane = new JScrollPane(commits, 
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			ascrollpane.setMinimumSize(new Dimension(10,40));
			secondpane.add(ascrollpane);
	    }
	    
    	return firstpane;
    }
    
    
	protected JComponent getTimesBar(double height){
		JPanel apane = new JPanel();
		SpringLayout layout = new SpringLayout();
		apane.setLayout(layout);
		 
		String[] times = {"12:00", "1 AM","2:00","3:00","4:00","5:00","6:00",
				 		 "7:00","8:00","9:00","10:00","11:00","12 PM",
				 		 "1:00","2:00","3:00","4:00","5:00","6:00",
				 		 "7:00","8:00","9:00","10:00","11:00"};
		
		int max = 0;
		    
		for(int i = 1; i < 24; i++){
			JLabel alab = new JLabel(times[i]);
			alab.setFont(new Font("Arial", 1, 12));
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, alab, (int)(height*i/24.0), SpringLayout.NORTH, apane);
			layout.putConstraint(SpringLayout.EAST, alab, 0, SpringLayout.EAST, apane);
			max = alab.getPreferredSize().width > max ? alab.getPreferredSize().width : max;
			apane.add(alab);
		}
		 	 
		apane.setPreferredSize(new Dimension(max+5, (int)height));
		apane.setSize(new Dimension(max+5, (int)height));
		  	 
		return apane;
	}
  
  
	@Override
	public JPanel getPane() {
		// TODO Auto-generated method stub
	return this;
	}

	
	protected class AMouseEvent implements MouseListener{
		TeamCalendar tcalendar;
		Calendar adate;
		
		public AMouseEvent(Calendar adate, TeamCalendar tcalendar){
			this.adate = adate;
			this.tcalendar = tcalendar;
		}
	
		public void mousePressed(MouseEvent e) {

	    }

	    public void mouseReleased(MouseEvent e) {

	    }

	    public void mouseEntered(MouseEvent e) {
	    }

	    public void mouseExited(MouseEvent e) {

	    }

	    public void mouseClicked(MouseEvent e) {
	    	if(e.getClickCount() > 1){
	    		tcalendar.setCalsetView(adate, TeamCalendar.types.DAY);
	    	}
	    }
	}

}
