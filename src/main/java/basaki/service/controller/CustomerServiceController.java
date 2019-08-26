package basaki.service.controller;

import basaki.data.Customer;
import basaki.data.CustomerList;
import basaki.data.ErrorInfo;
import basaki.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Locale;

@Controller
@Api(value = "customers")
public class CustomerServiceController {

    @Resource(name = "customerService")
    private CustomerService service;

    @Autowired
    private MessageSource messageSource;

    @ApiOperation(value = "Gets a customer based on customer id", notes = "Retrieves a single customer", response = Customer.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, value = "customers/{id}")
    @ResponseBody
    public Customer getCustomer(@PathVariable Integer id) {
        Customer customer = service.getCustomer(id);

        return customer;
    }

    @ApiOperation(value = "Gets all the customers", notes = "Retrieves all customers", response = Customer.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, value = "customers")
    @ResponseBody
    public CustomerList getCustomers() {
        return new CustomerList(new ArrayList<Customer>(service.getCustomers()));
    }

    @ApiOperation(value = "Creates a new customer based on request body", notes = "Creates a customer", response = Customer.class)
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, value = "customers")
    public @ResponseBody
    Customer createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @ApiOperation(value = "Deletes a customer based on customer id", notes = "Deletes a customer", response = Customer.class)
    @RequestMapping(method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, value = "customers/{id}")
    public @ResponseBody
    Customer deleteCustomer(@PathVariable("id") Integer id) {
        return service.deleteCustomer(id);
    }

    @ApiOperation(value = "Updates a customer based on customer id", notes = "Updates a customer", response = Customer.class)
    @RequestMapping(method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, value = "customers/{id}")
    public @ResponseBody
    Customer updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        return service.updateCustomer(id, customer);
    }

    @ApiOperation(value = "Updates a customer partially based on customer id", notes = "Partially updates a customer", response = Customer.class)
    @RequestMapping(method = RequestMethod.PATCH, consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, value = "customers/{id}")
    public @ResponseBody
    Customer updatePartialCustomer(@PathVariable("id") Integer id,
                                   @RequestBody Customer customer) {
        Customer cust = service.updatePartialCustomer(id, customer);
        return cust;
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
        String errorURL = req.getRequestURL().toString();
        int code = HttpStatus.BAD_REQUEST.value();
        String type = ex.getErrorCode();
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("error.customer.id", new Object[]{ex.getValue()}, locale);

        return new ErrorInfo(errorURL, code, type, errorMessage);
    }
}
