package com.ib.cat.controller.info;

import com.ib.cat.entity.Like;
import com.ib.cat.service.info.MemberLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberLikeController {

    @Autowired
    MemberLikeService memberLikeService;
    @GetMapping("/memberlike/{id}")
    public String likeForm(@PathVariable String id, Model model){

//        /* 1. movie */ List<Like> movie = memberLikeService.getMovie(id,0);
//        /* 2. tv */  List<Like> tv = memberLikeService.getTv(id,1);
//        /* 3. board */ List<Like> board = memberLikeService.getBoard(id,2);
//
//        model.addAttribute("movie",movie);
//        model.addAttribute("tv",tv);
//        model.addAttribute("board",board);
        model.addAttribute("cNum",id);
        model.addAttribute("code",0);
        return "info/memberlike";
    }

    @PostMapping ("/memberlike/movie")
    @ResponseBody
    public List<Like> movieAjax(@RequestParam String cNum,@RequestParam Integer code){

        return memberLikeService.getMovie(cNum,code);
    }
}
