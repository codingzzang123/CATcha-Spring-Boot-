package com.ib.cat.controller.info;

import com.ib.cat.dto.Info.LikeCountDto;
import com.ib.cat.entity.Like;
import com.ib.cat.service.info.MemberLikeService;
import com.ib.cat.service.media.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberLikeController {

    @Autowired
    MemberLikeService memberLikeService;

    @Autowired
    LikeService likeService;

    @GetMapping("/memberlike/{id}")
    public String likeForm(){
        return "info/memberlike";
    }

    @PostMapping("/memberlike/count") @ResponseBody
    public LikeCountDto countAjax(@RequestParam String memberId ,Model model){
        LikeCountDto likeCountDto = new LikeCountDto(
                memberLikeService.getCount(memberId,0),memberLikeService.getCount(memberId,1),
                memberLikeService.getCount(memberId,2)
        );
        return likeCountDto;
    }

    @PostMapping ("/memberlike/like") @ResponseBody
    public List<Like> movieAjax(@RequestParam String memberId,@RequestParam Integer code){
        return memberLikeService.getList(memberId,code);
    }

    @DeleteMapping("/memberlike/delete") @ResponseBody
    public void delete(@RequestParam String memberId
            ,@RequestParam Integer contentsNum, @RequestParam Integer code){
        likeService.cancelLike(memberId,contentsNum,code);
    }

}
