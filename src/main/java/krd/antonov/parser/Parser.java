package krd.antonov.parser;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface Parser {

    @SuppressWarnings("rawtypes")
    Object getObject(File file, Class clazz) throws JAXBException;

    void saveObject(File file, Object obj) throws JAXBException;
}
