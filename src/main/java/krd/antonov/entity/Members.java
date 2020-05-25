package krd.antonov.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "members")
@XmlAccessorType(XmlAccessType.FIELD)
public class Members {

    @XmlElement
    private List<Member> member = new ArrayList<>();

    public List<Member> getMembers() {
        return member;
    }

    public void setMembers(List<Member> members) {
        this.member = members;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"name", "roles"})
    public static class Member {

        @XmlAttribute
        private String name;

        @XmlElement
        private List<Role> roles = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"name", "project"})
    public static class Role {

        @XmlAttribute
        private String name;

        @XmlAttribute
        private String project;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }
    }
}
