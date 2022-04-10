package com.laura.carpaciu.exceptionhandlers.order;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.laura.carpaciu.errors.order.BadIntegerNumberException;
import com.laura.carpaciu.errors.order.ClientNotSelectedException;
import com.laura.carpaciu.errors.order.NotEnoughPartsException;
import com.laura.carpaciu.errors.order.OrderIsClosedException;
import com.laura.carpaciu.errors.order.SelectPartException;

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


    @ExceptionHandler(PartOrderException.class)
    public String processPartOrderException(PartOrderException e){
        e.printStackTrace();
        return "redirect:/orderPart/addPart-page";
    }


    @ExceptionHandler(LaborOrderException.class)
    public String processLaborOrderException(LaborOrderException e){
        e.printStackTrace();
        return "redirect:/laborOrder/laborOrderPage";
    }







}