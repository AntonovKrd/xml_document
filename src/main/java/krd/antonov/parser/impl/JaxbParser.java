package krd.antonov.parser.impl;

import krd.antonov.parser.Parser;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser implements Parser {

    private static final Logger logger = Logger.getLogger(JaxbParser.class);

    @SuppressWarnings("rawtypes")
    public Object getObject(File file, Class clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        logger.info("object " + clazz.getSimpleName() + " is read from " + file.getAbsolutePath());
        return unmarshaller.unmarshal(file);
    }

    public void saveObject(File file, Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, file);
        logger.info("object" + obj.getClass().getSimpleName() + " is saved to " + file.getAbsolutePath());
    }
}
