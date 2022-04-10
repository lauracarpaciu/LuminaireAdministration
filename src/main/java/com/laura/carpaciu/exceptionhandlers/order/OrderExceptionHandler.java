package com.laura.carpaciu.exceptionhandlers.order;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.laura.carpaciu.errors.invoice.InvoiceException;
import com.laura.carpaciu.errors.luminaire.PieceNotFoundException;
import com.laura.carpaciu.errors.luminaire.PieceOrderException;
import com.laura.carpaciu.errors.order.BadIntegerNumberException;
import com.laura.carpaciu.errors.order.ClientNotSelectedException;
import com.laura.carpaciu.errors.order.LuminaireNotSelectedException;
import com.laura.carpaciu.errors.order.NotEnoughPartsException;
import com.laura.carpaciu.errors.order.OrderIsClosedException;
import com.laura.carpaciu.errors.order.SelectOrderException;
import com.laura.carpaciu.errors.order.SelectPartException;
import com.laura.carpaciu.errors.work.WorkOrderException;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(LuminaireNotSelectedException.class)
    public String vehicleNotSelectedException(LuminaireNotSelectedException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder";

    }

    @ExceptionHandler(ClientNotSelectedException.class)
    public String clientNotFoundException(ClientNotSelectedException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder";
    }

    @ExceptionHandler(OrderIsClosedException.class)
    public String orderIsClosedException(OrderIsClosedException e){
        e.printStackTrace();
        return "redirect:/app/main";

    }

    @ExceptionHandler(PieceNotFoundException.class)
    public String partNotFoundException(PieceNotFoundException e){

        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?part";

    }

    @ExceptionHandler(NotEnoughPartsException.class)
    public String notEnoughPartsException(NotEnoughPartsException e){

        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?noPart";
    }

    @ExceptionHandler(BadIntegerNumberException.class)
    public String validIntegerNumberException(BadIntegerNumberException e){
        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?invalid";
    }

    @ExceptionHandler(SelectPartException.class)
    public String noPartWasSelectedException(SelectPartException e){
        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?select";

    }

    @ExceptionHandler(SelectOrderException.class)
    public String processSelectOrderException(SelectOrderException e){
        e.printStackTrace();
        return "redirect:/app/main";
    }


    @ExceptionHandler(InvoiceException.class)
    public String processInvoiceException(InvoiceException e){
        e.printStackTrace();
        return "redirect:/app/main";
    }


    @ExceptionHandler(PieceOrderException.class)
    public String processPartOrderException(PieceOrderException e){
        e.printStackTrace();
        return "redirect:/orderPart/addPart-page";
    }


    @ExceptionHandler(WorkOrderException.class)
    public String processWorkOrderException(WorkOrderException e){
        e.printStackTrace();
        return "redirect:/workOrder/workOrderPage";
    }







}