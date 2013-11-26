package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.util.Calendar;
import java.util.Locale;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.CalendarData;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CommitmentList;

public class MonthView extends CalendarView {
	
	private Calendar aMonth;
	private AbCalendar tcalendar;
	
	public MonthView(Calendar datecalendar, AbCalendar tcalendar) {
		super(datecalendar);
		setCalPane(new MonthPane(datecalendar, tcalendar));
		setCommitmentView(new CommitmentView(tcalendar));
		setRange(datecalendar);
	}
	
	
	public void setRange(Calendar calendar) {
		aMonth = (Calendar) calendar.clone();

		while (aMonth.get(Calendar.DAY_OF_MONTH) != aMonth.DAY_OF_MONTH) {
			aMonth.add(Calendar.DAY_OF_MONTH, -1);
		}
		
		String monthName = aMonth.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
		int startDayNum = aMonth.get(Calendar.DAY_OF_MONTH);
		int endDayNum = aMonth.get(Calendar.DAY_OF_MONTH);
		int startYear = aMonth.get(Calendar.YEAR);
		int endYear = aMonth.get(Calendar.YEAR);
		
		setLabel(monthName + " ");
				
		refresh();
	}
	
	@Override
	public void displayCalData(CalendarData calData) {
		commitments.update();
		// TODO Auto-generated method stub
		
	}

}
