package com.ib.cat.controller.media;

import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.service.media.MediaReplyService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MediaReplyController {

    @Autowired
    private MediaReplyService mediaReplyService;

    @PostMapping(value="/addReply")
    @ResponseBody
    public String addReply(@RequestParam("object") String data) {
        System.out.println("contentReply 컨트롤러 작동");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(data);

//            ContentReplyDto crd = new ContentReplyDto(
//                    Integer.parseInt(String.valueOf(jsonObject.get("contentsNum"))),
//                    jsonObject.get("writer").toString(),
//                    jsonObject.get("content").toString(),
//                    Integer.parseInt(String.valueOf(jsonObject.get("code")))
//            );
            ContentReply contentReply = new ContentReply(
                    Integer.parseInt(String.valueOf(jsonObject.get("contentsNum"))),
                    jsonObject.get("writer").toString(),
                    jsonObject.get("content").toString(),
                    Integer.parseInt(String.valueOf(jsonObject.get("code")))
            );
            mediaReplyService.writeMediaReply(contentReply);

            /////
            mediaReplyService.getMediaReplyPage(
                    Integer.parseInt(jsonObject.get("code").toString()),
                    Integer.parseInt(String.valueOf(jsonObject.get("contentsNum"))) );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return "movie/content";

    }


}
