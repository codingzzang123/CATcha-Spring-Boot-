package com.ib.cat.controller.media;

import com.ib.cat.dto.media.ContentReplyDto;
import com.ib.cat.entity.ContentReply;
import com.ib.cat.service.media.MediaReplyService;
//import org.json.simple.JSONArray;
import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MediaReplyController {

    @Autowired
    private MediaReplyService mediaReplyService;

    @PostMapping(value="/addReply")
    @ResponseBody
    public List<ContentReplyDto> addReply(@RequestParam("object") String data) {
        System.out.println("contentReply 컨트롤러 작동");
        List<ContentReplyDto> crList = new ArrayList<>();
//        JSONArray returnR;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try { /*  DB에 데이터 삽입  */
            jsonObject = (JSONObject) parser.parse(data); //object parse
            int contentsNum = Integer.parseInt(String.valueOf(jsonObject.get("contentsNum")));
            String writer = jsonObject.get("userName").toString();
            String content = jsonObject.get("content").toString();
            String title = jsonObject.get("title").toString();
            int code = Integer.parseInt(String.valueOf(jsonObject.get("code")));

            ContentReplyDto crd = new ContentReplyDto(contentsNum,writer,content,title,code);
            mediaReplyService.writeMediaReply(crd);
            System.out.println("write 성공");
            System.out.println("contentsNum:"+contentsNum);
            System.out.println("code:"+code);

            //        /*  읽어오기  */
            crList = mediaReplyService.getMediaReplyPage(contentsNum, code);
            System.out.println("crList: "+crList);
//            returnR = JSONArray.fromObject(crList);
//            System.out.println(returnR);
            return crList;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }




//        List<ContentReply> tmpList
//        = mediaReplyService.getMediaReplyPage(
//                Integer.parseInt(jsonObject.get("code").toString()),
//                Integer.parseInt(String.valueOf(jsonObject.get("contentsNum"))) );
//        JSONObject jsonObResult = new JSONObject();
//        for (int i = 0 ; i < tmpList.size() ; i++){
//
//        }
//        JSONArray jsonArray = new JSONArray();
//        return returnR;

    }


}
