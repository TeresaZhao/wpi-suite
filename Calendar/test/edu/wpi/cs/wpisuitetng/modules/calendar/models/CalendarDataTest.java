package edu.wpi.cs.wpisuitetng.modules.calendar.models;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class CalendarDataTest {

	@Test
	public void test() {
		CalendarData data = new CalendarData();
		Commitment com1 = new Commitment("com1", new GregorianCalendar(2013, 1, 1), "the first commitment", 0, true);
		Commitment com2 = new Commitment("com2", new GregorianCalendar(2013, 2, 2), "the second commitment", 0, true);
		Commitment com3 = new Commitment("com3", new GregorianCalendar(2013, 3, 3), "the third commitment", 0, true);
		data.addCommitment(com1);
		data.addCommitment(com2);
		data.addCommitment(com3);
		
		CommitmentList list = data.getCommitments();
		System.out.println(list.getCommitments().get(1).getDescription() + data.getCommitments().getCommitments().get(1).getId());
		helper(list);
		//System.out.println(data.getCommitments().getCommitments().get(0).getDescription());
		System.out.println(data.getCommitments().getCommitments().get(1).getDescription() + data.getCommitments().getCommitments().get(1).getId());
		helper2(data.getCommitments().getCommitments().get(1));
		System.out.println(data.getCommitments().getCommitments().get(1).getDescription() + data.getCommitments().getCommitments().get(1).getId());
	}
	
	public void helper(CommitmentList list){
		Commitment toEdit = list.getCommitments().get(1);
		toEdit.setDescription(toEdit.getDescription() + "IT WORKS?!?");
		list.update(toEdit);
	}
	
	public void helper2(Commitment com){
		com.setDescription(com.getDescription() + "Still working!?!");
	}

}
