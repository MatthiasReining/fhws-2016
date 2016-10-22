package de.fhws.app.presentation.frontcontroller.commands;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.presentation.frontcontroller.Command;

public class ServerTimeCommand implements Command{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		Date serverTime = new Date();
		
		request.setAttribute("serverTime", serverTime);
		
		return "server-time";
	}

}
