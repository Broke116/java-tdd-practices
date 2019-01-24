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
        MemberDao sender = memberRepository.getMember(source);
        MemberDao receiver = memberRepository.getMember(target);

        if (sender.getMiles() < amount)
            return false;

        memberRepository.transferMileagePoints(source,target, amount);
        return true;
    }
}
