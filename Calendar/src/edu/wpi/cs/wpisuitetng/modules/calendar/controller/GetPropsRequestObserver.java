/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: CS Anonymous
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.controller;

import java.util.Date;

import edu.wpi.cs.wpisuitetng.modules.calendar.models.CalendarProps;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all categories
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class GetPropsRequestObserver implements RequestObserver {
	
	private GetPropsController controller;
	
	/**
	 * Constructs the observer given a GetCalendarPropsController
	 * @param controller the controller used to retrieve categories
	 */
	public GetPropsRequestObserver(GetPropsController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the CalendarProps out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Convert the JSON array of CalendarProps to a CalendarProps object array
		CalendarProps[] calData = CalendarProps.fromJsonArray(iReq.getResponse().getBody());
		
		// Pass these Events to the controller
		controller.receivedCalendarProps(calData);
		
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	/**
	 * 
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		CalendarProps[] errorCalData = { new CalendarProps("Error") };
		controller.receivedCalendarProps(errorCalData);
	}

}
