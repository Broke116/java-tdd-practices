package com.amadeus.training.tdd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MemberServiceTest {
    @Mock
    MemberDao memberDao, memberDao2, memberDao3;

    @Spy
    @InjectMocks
    MemberRepository memberRepository;

    @Spy
    @InjectMocks
    MemberService memberService;

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
        memberDao = new MemberDao("123456", "ekin", 100); // arrange
        memberDao2 = new MemberDao("1234567", "aaaa", 200); // arrange
        memberDao3 = new MemberDao("8934567", "aaaa", 700); // arrange
        memberService.insertMember(memberDao);
    }

    @Test
    public void insertMemberIntoHashMap() {
         // act
        Assert.assertEquals(1, memberService.getMemberSize());
    }

    @Test
    public void checkMemberArraySizeReturnsFalse(){
        memberService.insertMember(memberDao2);
        Assert.assertNotEquals(3, memberRepository.getMemberSize());
    }

    @Test
    public void checkMemberName() {
        Mockito.when(memberRepository.getMember(anyString())).thenReturn(memberDao); // stubbing example. Imagine we are returning this value from the database.
        Assert.assertEquals("ekin", memberService.getMember("123456").getName());
    }

    @Test
    public void checkMemberMileagePoints() {
        memberService.insertMember(memberDao2);
        Assert.assertEquals(200, memberService.getMember(memberDao2.getFlyerID()).getMiles());
    }

    @Test
    public void checkMembersAreEqualOrNot() {
        Assert.assertNotEquals(memberDao, memberDao2);
    }

    @Test
    public void transferMilagePoints() {
        memberService.insertMember(memberDao3);
        memberService.transferMileagePoints(memberDao.getFlyerID(), memberDao3.getFlyerID(), 100);
        Assert.assertEquals(800,memberDao3.getMiles());
    }

    @Test
    public void senderHasInsufficientMileagePoints() {
        memberService.insertMember(memberDao3);
        Assert.assertEquals(false, memberService.transferMileagePoints(memberDao.getFlyerID(), memberDao3.getFlyerID(), 1000));
    }
}
