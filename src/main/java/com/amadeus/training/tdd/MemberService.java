package com.amadeus.training.tdd;

class MemberService {

    private MemberRepository memberRepository;

    MemberService() {
        memberRepository = MemberRepository.getRepo();
    }

    void insertMember(MemberDao memberDao) {
        memberRepository.insertMember(memberDao);
    }

    int getMemberSize() {
        return memberRepository.getMemberSize();
    }

    MemberDao getMember(String flyerID) {
        return memberRepository.getMember(flyerID);
    }

    boolean transferMileagePoints(String source, String target, int amount) {
        if (memberRepository.checkMemberExist(source) && memberRepository.checkMemberExist(target)) {
            if (memberRepository.getMember(source).getMiles() > amount)
                memberRepository.transferMileagePoints(source,target, amount);
            else
                return false;
        } else {
            return false;
        }
        return true;
    }
}
