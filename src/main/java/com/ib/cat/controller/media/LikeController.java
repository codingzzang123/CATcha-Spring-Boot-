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
    public String addLike(@RequestParam("object") String data,
                          @RequestParam String flag) throws Exception {

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

        if(flag.equals("true")){ //-> 저장
            likeService.addLike(mld);
        }else{ //->삭제
            likeService.cancelLike(mld.getNum(), mld.getCode());
        }

        return "success";
    }
}
