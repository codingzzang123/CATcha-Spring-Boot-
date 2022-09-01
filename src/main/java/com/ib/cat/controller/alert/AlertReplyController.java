package com.ib.cat.controller.alert;

import com.ib.cat.service.alert.AlterReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertReplyController {

    @Autowired
    AlterReplyService alterReplyService;

    @PutMapping("/alert/reply/insert")
    public ResponseEntity insert(@RequestParam String object) throws Exception{
        alterReplyService.insertReply(object);
        return new ResponseEntity(1, HttpStatus.OK);
    }

    @DeleteMapping("/alert/reply/delete")
    public ResponseEntity delete(@RequestParam String object)throws Exception {
        alterReplyService.deleteReply(object);
        return new ResponseEntity(1, HttpStatus.OK);
    }
}
