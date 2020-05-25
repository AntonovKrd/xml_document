package krd.antonov.entity;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class Projects {

    @XmlElement(name = "project")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (projects != null && !projects.isEmpty()){
            projects.forEach(project -> builder.append(project.toString()));
        }
        return "Projects{" +
                "projects=" + builder.toString() +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Project {

        @XmlAttribute
        private String name;

        @XmlElement(name = "member")
        private List<Member> members;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Member> getMembers() {
            return members;
        }

        public void setMembers(List<Member> members) {
            this.members = members;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (this.members != null && !this.members.isEmpty()){
                this.members.forEach(member -> builder.append(member.toString()).append(System.lineSeparator()));
            }
            return "Project{" +
                    "name='" + name + '\'' +
                    ", members=" + builder.toString() +
                    '}';
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"role", "name"})
    public static class Member {

        @XmlAttribute
        private String name;

        @XmlAttribute
        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "name='" + name + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        }
    }
}
