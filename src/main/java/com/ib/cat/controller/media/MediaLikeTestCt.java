package com.ib.cat.controller.media;

import com.ib.cat.entity.MediaLike;
import com.ib.cat.entity.Member;
import com.ib.cat.service.media.MediaLikeService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MediaLikeTestCt {
//    @Autowired
//    static
//    MediaLikeService mediaLikeService;
//
//    @Autowired
//    static
//    MemberService memberService;

    public static void main(String[] args) {
//        MediaLikeService mediaLikeService = new MediaLikeService();
        MemberService memberService = new MemberService();

        //member table에 넣기
        Member member = Member.builder()
                        .id("testId")
                        .pw("1234")
                        .name("tester")
        .email("tester@naver.com").auth("test").imgo("a").imgs("b").build();
        System.out.println(member.getId());
        memberService.memberInsert(member);

//        //mediaLike table에 넣기
//        MediaLike mediaLike = MediaLike.builder()
//                                .member(member)
//                                .contentsNum(1234)
//                                .build();
//        mediaLikeService.mediaLikeInsert(mediaLike);
    }
}
