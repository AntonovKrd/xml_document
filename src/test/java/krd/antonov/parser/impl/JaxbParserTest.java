package krd.antonov.parser.impl;

import krd.antonov.entity.Projects;
import krd.antonov.parser.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbParserTest {

    private static Parser parser;

    @BeforeAll
    static void init() {
        parser = new JaxbParser();
    }

    @Test
    public void testGetObject() throws JAXBException {
        File file = new File("projects.xml");
        Projects projects = (Projects) parser.getObject(file, Projects.class);
        System.out.println(projects);
    }
}
