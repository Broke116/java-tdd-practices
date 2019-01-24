package com.amadeus.training.tdd;

import java.util.HashMap;
import java.util.Map;

class MemberRepository {
    private static MemberRepository memberRepository = new MemberRepository();
    private Map<String, MemberDao> members = new HashMap<>();

    private MemberRepository(){
    }

    static MemberRepository getRepo(){
        if (memberRepository == null) return new MemberRepository();
        else return memberRepository;
    }

    void insertMember(MemberDao memberDao) {
        members.put(memberDao.getFlyerID(), memberDao);
    }

    int getMemberSize() {
        return members.size();
    }

    MemberDao getMember(String flyerID) {
        return members.get(flyerID);
    }

    String getMemberName(MemberDao memberDao) {
        return members.get(memberDao.getName()).getFlyerID();
    }

    boolean checkMemberExist(String flyerID) {
        if (members.get(flyerID) != null) {
            return false;
        }
        return true;
    }

    boolean transferMileagePoints(String source, String target, int amount) {
        int sourceCurrentMile = members.get(source).getMiles();
        int targetCurrentMile = members.get(target).getMiles();

        members.get(source).setMiles(sourceCurrentMile - amount);
        members.get(target).setMiles(targetCurrentMile + amount);

        return true;
    }
}
