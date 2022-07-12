package com.ib.cat.controller.media;

import com.ib.cat.entity.MediaLike;
import com.ib.cat.entity.Member;
import com.ib.cat.service.media.MediaLikeService;
import com.ib.cat.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaLikeController {
    @Autowired
    MediaLikeService mediaLikeService;

    @PostMapping("/like")
    public String addLikeList(@RequestParam("contentsNum") int contentsNum, @RequestParam("contentsType") String contentsType,
                              @RequestParam("contentsTitle") String contentsTitle, @RequestParam("contentsOverview") String contentsOverview,
                              @RequestParam("posterPath") String posterPath, @RequestParam("userNum") int userNum,
                              @RequestParam String flag) {
        System.out.println("flag: "+flag);
        System.out.println("contentsNum: "+contentsNum);
        System.out.println(contentsType + contentsTitle + contentsOverview);
        //save
//        MediaLike mediaLike = MediaLike.builder()
//                .contentsNum(data.contentsNum)
//                .id(sessionId)
//                .build();
//        mediaLikeService.mediaLikeInsert(mediaLike);
        return "addLikeList controller";
    }


}
