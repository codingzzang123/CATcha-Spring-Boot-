package com.ib.cat.controller.main;

import com.ib.cat.dto.media.MediaLikeDto;
import com.ib.cat.service.media.TestService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String testForm(){
        return "main/test";
    }

    @PostMapping("/test")
    @ResponseBody
    public String testForm2(@RequestParam("object") String data,
    @RequestParam String flag)throws Exception{
        System.out.println("flag = " + flag);
        System.out.println("Date = "+data);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(data);


        LikeMediaDto  mld = new LikeMediaDto(
                jsonObject.get("name").toString(),Integer.parseInt(String.valueOf(jsonObject.get("num"))),jsonObject.get("poster").toString(),
                jsonObject.get("title").toString(),jsonObject.get("overview").toString(),
                Integer.parseInt(String.valueOf(jsonObject.get("type")))
        );
                
        if(flag.equals("true")){ //-> 저장
            testService.insert(mld);
        }else{ //->삭제
            testService.delete(mld.getNum());
        }
        
        return "zz";
    }
}
