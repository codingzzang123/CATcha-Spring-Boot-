package com.ib.cat.controller.alert;

import com.ib.cat.service.alert.AlertLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertLikeController {

    @Autowired
    AlertLikeService alertLikeService;

    @PutMapping("/alert/like/insert")
    public int insert(@RequestParam String object)throws Exception{
        alertLikeService.insertLike(object); return 1;
    }

    @DeleteMapping("/alert/like/delete")
    public int alertBoard(@RequestParam String object)throws Exception{
        alertLikeService.deleteLike(object); return 1;
    }




}
