package com.ib.cat.service;

import com.ib.cat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService{
    @Autowired
    MemberRepository memberRepository;

    public int idCheck(String id) {
        return memberRepository.countById(id);
    }

    public int nameCheck(String name) {
        return memberRepository.countByName(name);
    }
}
