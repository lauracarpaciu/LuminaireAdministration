package com.laura.carpaciu.exceptionhandlers.work;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.laura.carpaciu.errors.luminaire.InvalidPriceException;
import com.laura.carpaciu.errors.order.WorkNotFoundException;
@ControllerAdvice
public class WorkExceptionHandler {

	 @ExceptionHandler(InvalidPriceException.class)
	    public String processInvalidPriceException(InvalidPriceException e){
	        e.printStackTrace();

	        return "redirect:/prices/showPrices?invalid";

	    }


	    @ExceptionHandler
	    public String processWorkNotFoundException(WorkNotFoundException e){
	        e.printStackTrace();

	        return "redirect:/workOrder/workOrderPage?deleted";
	    }
	}