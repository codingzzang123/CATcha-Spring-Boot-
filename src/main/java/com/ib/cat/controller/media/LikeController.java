package com.ib.cat.controller.media;

import com.ib.cat.dto.media.LikeDto;
import com.ib.cat.service.media.LikeService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

    @PostMapping("/like")
    public String addLike(@RequestParam("object") String data) throws Exception {
//        System.out.println("likeController - /like");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(data);

        LikeDto mld = new LikeDto(
                jsonObject.get("userId").toString(), //String
                Integer.parseInt(String.valueOf(jsonObject.get("contentsNum"))), //int
                jsonObject.get("posterPath").toString(), //String
                jsonObject.get("title").toString(), //String
                jsonObject.get("overview").toString(), //String
                Integer.parseInt(String.valueOf(jsonObject.get("code"))) //int
        );
//        System.out.println("object-userId: "+jsonObject.get("userId").toString());
//        System.out.println("object-code: "+Integer.parseInt(String.valueOf(jsonObject.get("code"))));
        String flag = likeService.checkLike(mld);
//        System.out.println("object-flag:" +flag);

        if(flag.equals("false")){ //-> 저장
            likeService.addLike(mld); //db에 저장하고 나서
            flag="true"; //flag값 바꿔줌 (좋아요 햇으니까 true 반환해주기)
        }else{ //->삭제
            likeService.cancelLike(mld.getName(),mld.getNum(), mld.getCode());
            flag="false";
        }
        return flag;
    }
    @PostMapping("/likeCheck")
    public String likeCheck(@RequestParam("object") String data) throws Exception {
//        System.out.println("likeController - /likeCheck");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(data);

        LikeDto lcd = new LikeDto();
        lcd.setName(jsonObject.get("userId").toString());
        lcd.setNum(Integer.parseInt(String.valueOf(jsonObject.get("contentsNum"))));
        lcd.setCode(Integer.parseInt(String.valueOf(jsonObject.get("code"))));

//        System.out.println("/likecheck - userId: " + jsonObject.get("userId").toString());

        String flag = likeService.checkLike(lcd);
//        System.out.println("likeService.checkLike- flag 확인: "+flag);

    return flag;
    }
}
