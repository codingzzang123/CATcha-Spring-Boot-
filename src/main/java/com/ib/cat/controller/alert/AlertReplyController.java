package com.ib.cat.controller.alert;

import com.ib.cat.service.alert.AlterReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertReplyController {

    @Autowired
    AlterReplyService alterReplyService;

    @PutMapping("/alert/reply/insert")
    public int insert(@RequestParam String object) throws Exception{
        alterReplyService.insertReply(object); return 1;
    }

    @DeleteMapping("/alert/reply/delete")
    public int delete(@RequestParam String object)throws Exception{
        alterReplyService.deleteReply(object); return 1;
    }
}
