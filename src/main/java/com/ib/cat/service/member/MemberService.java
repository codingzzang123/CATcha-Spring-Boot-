package com.ib.cat.service.member;

import com.ib.cat.entity.Member;
import com.ib.cat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;
    public void memberInsert(Member member){
        memberRepository.save(member);
    }
    public void memberUpdate(Member member){
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username).get();

        if(member == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getId())
                .password(member.getPw())
                .authorities(Collections.emptyList())
                .build();
    }
}
