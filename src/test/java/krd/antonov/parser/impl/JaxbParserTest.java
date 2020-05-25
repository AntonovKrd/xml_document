package krd.antonov.parser.impl;

import krd.antonov.entity.Projects;
import krd.antonov.parser.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class JaxbParserTest {

    private static Parser parser;

    @BeforeAll
    static void init() {
        parser = new JaxbParser();
    }

    @Test
    void testGetObject() throws JAXBException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("projects.xml")).getFile());
        Projects projects = (Projects) parser.getObject(file, Projects.class);
        System.out.println(projects);
    }

    @Test
    void testSaveObject() throws JAXBException {
        Projects projects = new Projects();
        Projects.Project project_one = new Projects.Project();
        project_one.setName("test");

        Projects.Member member_one = new Projects.Member();
        member_one.setName("Vladimir");
        member_one.setRole("developer");

        Projects.Member member_two = new Projects.Member();
        member_two.setName("Aleksandr");
        member_two.setRole("Analyst");

        ArrayList <Projects.Member> membersList = new ArrayList<>();
        Collections.addAll(membersList, member_one, member_two);
        project_one.setMembers(membersList);

        ArrayList <Projects.Project> projectsList = new ArrayList<>();
        projectsList.add(project_one);

        projects.setProjects(projectsList);

        File file = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "testProjects.xml");
        parser.saveObject(file,projects);
    }
}
