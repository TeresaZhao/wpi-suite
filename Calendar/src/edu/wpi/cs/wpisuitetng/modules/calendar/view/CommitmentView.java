
/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.JSplitPane;



import javax.swing.border.EmptyBorder;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CalendarData;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CalendarDataModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.Commitment.Status;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.CommitmentList;

/**
 * @author cttibbetts
 *
 */
public class CommitmentView extends JPanel {

	JPanel commitPanel;

	List<Commitment> commitmentList = new ArrayList();
	private CalendarData calData;

//	private List<CommitmentViewPanel> commitmentPanelList;
	
	public CommitmentView(CalendarData calData) {
		this.calData = calData;
		


		commitPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(commitPanel, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER );
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, this);
		scrollPane.setViewportView(commitPanel);

//		setCommList();      	
//		update();
		//test data will be where event data is handled



		/*  String testData = new String("Commitment1: did a commitment here it is move on to next");
	        for(int i = 0; i< 150; i++){
	        	JLabel commit = new JLabel(testData);
	        	JLabel line = new JLabel("\n");
	        	commitPanel.add(commit);
	        }
		 */
	}

	public void setCommList(CommitmentList commList, CommitmentList teamCommList) {
		//@TODO add team list
		commitmentList = teamCommList.getCommitments();
		update();
	}

// Now done in DayView, WeekView, etc.
//	public void setCommList(boolean showTeamData) {
//		if(calData != null){
//			System.out.println("got COMMITMENTS FOR VIEW");
//			commitmentList = new ArrayList<Commitment>();
//			CalendarData teamCommitments = CalendarDataModel.getInstance().getCalendarData(ConfigManager.getConfig().getProjectName());
//			if(GUIEventController.getInstance().getSelectedCalendar().getShowTeamData()) {
//				commitmentList = calData.getCommitments().getCommitments();
//				commitmentList.addAll(teamCommitments.getCommitments().getCommitments());
//			}
//			else{
//				commitmentList = calData.getCommitments().getCommitments();
//			}
//		}
//	}

	public void update(){
		commitPanel.removeAll();
		SpringLayout commPanelLayout = new SpringLayout();
		commitPanel.setLayout(commPanelLayout);
		List<CommitmentViewPanel> commPanelList = new ArrayList<CommitmentViewPanel>();
		int n = 0;//adjusted index to take hidden commitments into account
		for(int i = 0; i < commitmentList.size(); i++){
			if (commitmentList.get(i).getStatus().id != 2) {//Skips over completed commitments
				//Commitment commit = new Commitment();
				// commit.setName("Commitment Name");
				// commit.setDueDate(new Date());
				// commit.setDescription("The description of this commitment is right here. This will be shown as the description.");
				CommitmentViewPanel commitmentPanel = new CommitmentViewPanel(commitmentList.get(i));
				commitmentPanel.setBackground(Color.LIGHT_GRAY);
				//commitmentPanel.setBorder((BorderFactory.createMatteBorder(
				//        -2, -2, -2, -2, Color.GRAY)));
				JLabel name = new JLabel("Name: "+commitmentList.get(i).getName());
				JLabel date = new JLabel("Due Date: "+ commitmentList.get(i).getDueDate());
				JLabel description = new JLabel("<HTML>Description: "+ commitmentList.get(i).getDescription()+"</HTML>");
				JLabel status = new JLabel("Status: " + Status.convertToString(commitmentList.get(i).getStatus().id));
				commitmentPanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.anchor = GridBagConstraints.LINE_START;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 1;
				c.gridx = n;
				commitmentPanel.add(name,c);
				commitmentPanel.add(date,c);
				commitmentPanel.add(description,c);
				commitmentPanel.add(status,c);
				//  description.setMaximumSize(new Dimension(285,300));
				commitmentPanel.setBorder(new EmptyBorder(10, 5, 10 , 20));
				// commitmentPanel.setPreferredSize(new Dimension(280,300));
				// commitmentPanel.setMinimumSize(new Dimension(290, 400));
				// commitmentPanel.setMaximumSize(new Dimension(3000,1000));
				//  SpringLayout layoutDescription = new SpringLayout();
				//   description.setLayout(layoutDescription);
				// layoutDescription.putConstraint(SpringLayout.WEST, description, 0, SpringLayout.WEST, commitmentPanel);
				//  layoutDescription.putConstraint(SpringLayout.EAST, description, 0, SpringLayout.EAST, commitmentPanel);
				//  layoutDescription.putConstraint(SpringLayout.NORTH, description, 0, SpringLayout.NORTH, commitmentPanel);
				//   layoutDescription.putConstraint(SpringLayout.SOUTH, description, 0, SpringLayout.SOUTH, commitmentPanel);
				//	        commitmentPanelList.add(i,commitmentPanel);
				commitmentPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() > 1)
							GUIEventController.getInstance().editCommitment(((CommitmentViewPanel)e.getComponent()).getCommitment(), calData);
					}		
				});

				commPanelList.add(n, commitmentPanel);
				commitmentPanel.setMaximumSize(new Dimension(2000,100));
				if(n > 0)
					commPanelLayout.putConstraint(SpringLayout.NORTH, commitmentPanel, 1, SpringLayout.SOUTH, commPanelList.get(n-1));
				else
					commPanelLayout.putConstraint(SpringLayout.NORTH, commitmentPanel, 0, SpringLayout.NORTH, commitPanel);

				commPanelLayout.putConstraint(SpringLayout.WEST, commitmentPanel, 0, SpringLayout.WEST, commitPanel);
				commPanelLayout.putConstraint(SpringLayout.EAST, commitmentPanel, 0, SpringLayout.EAST, commitPanel);

				commitPanel.add(commitmentPanel);
				JSeparator separator = new JSeparator();
				separator.setOrientation(JSplitPane.VERTICAL_SPLIT);
				commPanelLayout.putConstraint(SpringLayout.NORTH, separator, 1, SpringLayout.SOUTH, commitmentPanel);
				commPanelLayout.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, commitPanel);
				commPanelLayout.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, commitPanel);

				commitPanel.add(separator);
				if (n == commitmentList.size() - 1)
					commPanelLayout.putConstraint(SpringLayout.SOUTH, commitPanel, 0, SpringLayout.SOUTH, separator);

				n++;
			}
		}

		revalidate();
		repaint();
	}

}
