package com.ib.cat.controller.info;

import com.ib.cat.dto.Info.LikeCountDto;
import com.ib.cat.entity.Like;
import com.ib.cat.service.info.MemberLikeService;
import com.ib.cat.service.media.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class MemberLikeController {

    @Autowired
    MemberLikeService memberLikeService;

    @Autowired
    LikeService likeService;

    @GetMapping("/memberlike/{id}")
    public String likeForm(@PathVariable String id, Model model){
        /*
        * jsp 파일로 카테고리 별 관심목록 갯수만 넘김
        * 나머지 데이터는 jsp파일에서 ajax로 요청 */
//        LikeCountDto likeCountDto = new LikeCountDto(
//                memberLikeService.getCount(id,0),memberLikeService.getCount(id,1),
//                memberLikeService.getCount(id,2)
//        );
//        model.addAttribute("count",likeCountDto);
        return "info/memberlike";
    }

    @PostMapping("/memberlike/count") @ResponseBody
    public LikeCountDto countAjax(@RequestParam String memberId ,Model model){
        LikeCountDto likeCountDto = new LikeCountDto(
                memberLikeService.getCount(memberId,0),memberLikeService.getCount(memberId,1),
                memberLikeService.getCount(memberId,2)
        );
        HashMap<String,Object> map = new HashMap<>();
        map.put("likeCountDto",likeCountDto);
        System.out.println("ajax 통신 성공");
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
