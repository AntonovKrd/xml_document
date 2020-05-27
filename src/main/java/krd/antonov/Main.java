package krd.antonov;

import krd.antonov.entity.Members;
import krd.antonov.entity.Projects;
import krd.antonov.parser.Parser;
import krd.antonov.parser.impl.JaxbParser;
import krd.antonov.utility.Utility;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);
    private final static String FILE_PROJECTS = "projects.xml";
    private final static String FILE_PROJECTS_TWO = "projects_two.xml";
    private final static String RESULT_PROJECTS = "result_projects.xml";
    private final static String RESULT_PROJECTS_TWO = "result_projects_two.xml";

    public static void main(String[] args) {
        Parser parser = new JaxbParser();
        Projects projects = readTaskXml(parser, FILE_PROJECTS);
        if (projects != null) {
            Members members = new Members();
            members.setMembers(getMembersList(projects));
            Utility.sortMembers(members);
            writeResultXml(parser, members, RESULT_PROJECTS);
        }
        Projects projects_two = readTaskXml(parser, FILE_PROJECTS_TWO);
        if (projects_two != null) {
            Members members = new Members();
            members.setMembers(getMembersList(projects_two));
            Utility.sortMembers(members);
            writeResultXml(parser, members, RESULT_PROJECTS_TWO);
        }
    }

    private static Projects readTaskXml(Parser parser, String fileName) {
        File projectsFile = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource(fileName)).getFile());
        Projects projects = null;
        if (projectsFile.canRead()) {
            try {
                projects = (Projects) parser.getObject(projectsFile, Projects.class);
            } catch (JAXBException e) {
                logger.error("error parse object" + Projects.class.getSimpleName(), e);
            }
        }
        return projects;
    }

    private static void writeResultXml(Parser parser, Members members, String fileName) {
        try {
            parser.saveObject(new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + fileName), members);
        } catch (JAXBException e) {
            logger.error("error save object" + Members.class.getSimpleName(), e);
        }
    }

    private static List<Members.Member> getMembersList(Projects projects) {
        List<Members.Member> memberList = new ArrayList<>();
        for (Projects.Project project : projects.getProjects())
            for (Projects.Member projMember : project.getMembers())
                addMemberToMemberList(memberList, projMember, project);
            return memberList;
    }

    private static void addMemberToMemberList(List<Members.Member> memberList, Projects.Member projMember, Projects.Project project) {
        Members.Member member = memberList.stream().filter(memb -> memb.getName().equals(projMember.getName())).findFirst().orElse(new Members.Member());
        boolean isNewMember = member.getName() == null;
        List<Members.Role> roles = new ArrayList<>();
        if (!member.getRoles().isEmpty()) roles = member.getRoles();
        Members.Role role = new Members.Role();
        role.setName(projMember.getRole());
        role.setProject(project.getName());
        roles.add(role);
        if (isNewMember) {
            member.setName(projMember.getName());
            member.setRoles(roles);
            memberList.add(member);
        }
    }
}
