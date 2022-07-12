package com.ib.cat.service.member;

import com.ib.cat.entity.Member;
import com.ib.cat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService{
    @Autowired
    MemberRepository memberRepository;
    public void memberInsert(Member member){
        memberRepository.save(member);
    }
    public Member findById(String id){
        return memberRepository.findById(id).get();
    }
    public int idCheck(String id) {
        return memberRepository.countById(id);
    }

    public int nameCheck(String name) {
        return memberRepository.countByName(name);
    }
}