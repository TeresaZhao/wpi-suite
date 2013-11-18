/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.wpi.cs.wpisuitetng.modules.calendar.CalendarMonth;
import edu.wpi.cs.wpisuitetng.modules.calendar.CalendarYear;
import edu.wpi.cs.wpisuitetng.modules.calendar.WeekView;
import edu.wpi.cs.wpisuitetng.modules.calendar.models.event.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.maintab.MainTabView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.maintab.secondarytabs.CommitmentTab;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.toolbar.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.toolbar.buttons.ButtonsPanel_Create;

public class GUIEventController {
	private static GUIEventController instance = null;
	private MainTabView main = null;
	private ToolbarView toolbar = null;

	/**
	 * Default constructor for ViewEventController.  Is protected to prevent instantiation.
	 */
	private GUIEventController() {}

	/**
	 * Returns the singleton instance of the vieweventcontroller.
	
	 * @return The instance of this controller. */
	public static GUIEventController getInstance() {
		if (instance == null) {
			instance = new GUIEventController();
		}
		return instance;
	}

	/**
	 * Sets the main view to the given view.
	
	 * @param mainview MainView
	 */
	public void setMainView(MainTabView mainview) {
		main = mainview;
	}

	/**
	 * Sets the toolbarview to the given toolbar
	 * @param tb the toolbar to be set as active.
	 */
	public void setToolBar(ToolbarView tb) {
		toolbar = tb;
		toolbar.repaint();
	}

	/**
	
	 * @return toolBar */
	public ToolbarView getToolbar() {
		return toolbar;
	}

	
	/**
	 * Returns the main view
	
	 * @return the main view */
	public MainTabView getMainView() {
		return main;
	}

	public void createCommitment() {
		CommitmentTab newCommit = new CommitmentTab();
		main.addTab("New Commitment", null, newCommit, "New Commitment");
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
		main.setSelectedComponent(newCommit);
	}

	public void createEvent() {

	}
	
	public void switchView(Calendar acal, TeamCalendar.types switchtype, TeamCalendar ateamcal){
		ateamcal.setCalsetView(acal, switchtype);
	}
	
}
