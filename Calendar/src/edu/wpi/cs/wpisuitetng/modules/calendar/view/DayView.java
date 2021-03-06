/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: CS Anonymous
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CalendarData;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CalendarDataModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CommitmentList;

public class DayView extends CalendarView {


	GregorianCalendar day;
	private DayPane dayPane;
	private GregorianCalendar endOfDay;
	
	public DayView(GregorianCalendar datecalendar) {
		super(datecalendar);
		//System.out.println("CREATING DAY");
		dayPane = new DayPane(datecalendar);
		setCalPane(dayPane);
		setCommitmentView(new CommitmentView());
		setRange(datecalendar);
		
	}

	@Override
	public void setRange(GregorianCalendar calendar) {
		day = new GregorianCalendar();
		day.setTime(calendar.getTime());
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);
		
		//set endOfDay to 23:59:59.999
		endOfDay = day;
		endOfDay.add(Calendar.DATE, 1);
		endOfDay.add(Calendar.MILLISECOND, -1);
		
		String dayName = day.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
		int dayNum = day.get(day.DAY_OF_MONTH);
		String monthName = day.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
		int year = day.get(day.YEAR);
		setLabel(dayName + ", " + monthName + " " + dayNum + "<br>" + year);
		refresh();
	}

	@Override
	public void displayCalData(CommitmentList commList, boolean showCommOnCal) {
		
		commitmentView.updateCommData(commList.getCommitments());
		// TODO filter commitments
		if (showCommOnCal)
			dayPane.displayCommitments(commList.filter(day)); //add only commitments on today to DayPane
		else
			dayPane.displayCommitments(null); //show no commitments on DayPane

	    revalidate();
	    repaint();
	    
//	    refresh();
		
	}

}
