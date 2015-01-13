package teste;


import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author deivid
 */
public class fabrica extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;

    public fabrica(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

   

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result = new teste(parent.getExceptionHandler());
        return result;
    }
}
