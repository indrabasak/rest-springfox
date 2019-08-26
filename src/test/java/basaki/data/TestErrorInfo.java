package basaki.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class TestErrorInfo {

    public static void main(String[] args) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(ErrorInfo.class);

        ErrorInfo einfo = new ErrorInfo("http://localhost:8080/rest-err-helloworld/customers/x", 400, "typeMismatch",
                                        "Some error");

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(einfo, System.out);

    }

}
