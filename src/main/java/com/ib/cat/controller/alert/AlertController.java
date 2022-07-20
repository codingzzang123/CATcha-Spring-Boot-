package com.ib.cat.controller.alert;

import com.ib.cat.dto.alert.AlertDto;
import com.ib.cat.service.alert.AlertService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlertController {

    @Autowired
    AlertService alertService;

    @PostMapping("/alert/count")
    public String count(@RequestParam String name){
        return alertService.alertCount(name)+"";
    }

    @PutMapping("/alert/like/insert")
    public int insert(@RequestParam String object)throws Exception{
        System.out.println("insert Object : " + object);
        alertService.insert(object);
        return 1;
    }

    @DeleteMapping("/alert/like/delete")
    public int alertBoard(@RequestParam String object)throws Exception{
        alertService.deleteAlertBoard(object);
        return 1;
    }

    @GetMapping("/alert/list")
    public List<AlertDto> getList(@RequestParam String name){
        return alertService.getList(name);
    }

    @DeleteMapping("/alert/delete")
    public int alertNav(@RequestParam Integer no)throws Exception{
        alertService.deleteAlertNav(no);
        return 1;
    }
}
