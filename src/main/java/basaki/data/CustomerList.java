package basaki.data;

import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * <tt>CustomerList</tt> is a wrapper class for a list of customers since JAXB
 * has problem unmarshalling a plain list.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomerList {

    private List<Customer> customers;

    public CustomerList() {
	customers = new ArrayList<Customer>();
    }

    public CustomerList(List<Customer> items) {
	this.customers = items;
    }

    @XmlElement(name = "customer")
    public List<Customer> getCustomers() {
	return customers;
    }

    public void setCustomers(List<Customer> items) {
	this.customers = items;
    }
}
