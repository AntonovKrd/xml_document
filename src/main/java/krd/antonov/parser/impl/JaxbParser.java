package krd.antonov.parser.impl;

import krd.antonov.parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser implements Parser {

    @SuppressWarnings("rawtypes")
    public Object getObject(File file, Class clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    public void saveObject(File file, Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, file);
    }
}
