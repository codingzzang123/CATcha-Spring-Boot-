package com.ib.cat.controller.alert;

import com.ib.cat.dto.alert.AlertDto;
import com.ib.cat.service.alert.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlertController {

    @Autowired
    AlertService alertService;

    @GetMapping("/alert/list")
    public List<AlertDto> getList(@RequestParam String name){
        return alertService.getList(name);
    }

    @PostMapping("/alert/count")
    public String count(@RequestParam String name){
        return alertService.alertCount(name)+"";
    }

    @DeleteMapping("/alert/delete")
    public int alertNav(@RequestParam Integer no)throws Exception{
        alertService.deleteAlertNav(no);
        return 1;
    }
}
