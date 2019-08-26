package basaki.service.controller;

import basaki.data.ErrorInfo;
import basaki.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class ExceptionProcessor {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo handleCustomerNotFoundException(HttpServletRequest req, CustomerNotFoundException ex) {
        String errorURL = req.getRequestURL().toString();
        int code = HttpStatus.BAD_REQUEST.value();
        String type = "customerNotFound";

        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("error.customer.not.found", new Object[]{ex.getMessage()}, locale);

        return new ErrorInfo(errorURL, code, type, errorMessage);
    }

}
