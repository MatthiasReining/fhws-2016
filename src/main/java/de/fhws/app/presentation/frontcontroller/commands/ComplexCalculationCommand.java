package de.fhws.app.presentation.frontcontroller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.business.calculation.boundary.Calculation;
import de.fhws.app.presentation.frontcontroller.Command;

public class ComplexCalculationCommand implements Command{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		
		double complextResult = new Calculation().calc(1);
		
		request.setAttribute("complexResult", complextResult);
		return "calculation-result";
	}


}
