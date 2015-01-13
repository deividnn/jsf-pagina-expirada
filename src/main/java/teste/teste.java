package teste;


import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author deivid
 */
public class teste extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public teste(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }
    
    
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

          //  Throwable t = context.getException();
            FacesContext fc = FacesContext.getCurrentInstance();
            try {
                NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler();
                navigationHandler.handleNavigation(fc, null, "error?faces-redirect=true");
                fc.renderResponse();
            } finally {
                i.remove();
            }
        }
// Call the parent exception handler’s handle() method
        getWrapped().handle();
    }

    
}
