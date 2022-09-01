package com.ib.cat.controller.alert;

import com.ib.cat.service.alert.AlertLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertLikeController {

    @Autowired
    AlertLikeService alertLikeService;

    @PutMapping("/alert/like")
    public ResponseEntity insert(@RequestParam String object)throws Exception{
        alertLikeService.insertLike(object);
        return new ResponseEntity(1, HttpStatus.OK);
    }

    @DeleteMapping("/alert/like")
    public ResponseEntity alertBoard(@RequestParam String object)throws Exception{
        alertLikeService.deleteLike(object);
        return new ResponseEntity(1, HttpStatus.OK);
    }




}
