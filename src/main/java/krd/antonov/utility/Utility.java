package krd.antonov.utility;

import krd.antonov.entity.Members;

import java.util.Comparator;

public class Utility {

    public static void sortMembers(Members members) {
        members.getMembers().sort(Comparator.comparing(Members.Member::getName));
        members.getMembers().forEach(member -> member.getRoles().sort(Comparator.comparing(Members.Role::getProject)));
    }
}
