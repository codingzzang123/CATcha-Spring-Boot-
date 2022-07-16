package com.ib.cat.controller.media;

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
    public JSONArray addReply(@RequestParam("object") String data) {
        System.out.println("contentReply 컨트롤러 작동");
        List<ContentReply> crList = new ArrayList<ContentReply>();
        JSONArray returnR;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
//        int contentsNum = 0;
//        String writer="";
//        String content ="";
//        int code = 0;
        try { /*  DB에 데이터 삽입  */
            jsonObject = (JSONObject) parser.parse(data); //object parse
            int contentsNum = Integer.parseInt(String.valueOf(jsonObject.get("contentsNum")));
            String writer = jsonObject.get("writer").toString();
            String content = jsonObject.get("content").toString();
            int code = Integer.parseInt(String.valueOf(jsonObject.get("code")));
            System.out.println("controller-데이터 parse 결과" + contentsNum + writer + content + code);
            ContentReply contentReply = new ContentReply(
                    contentsNum, writer, content, code);
            System.out.println("controller-contentReply상태"+contentReply);

            mediaReplyService.writeMediaReply(contentsNum, writer, content, code);
            System.out.println("write 성공");

            //        /*  읽어오기  */
            crList = mediaReplyService.getMediaReplyPage(code, contentsNum);
            returnR = JSONArray.fromObject(crList);
            System.out.println(returnR);
            return returnR;

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
