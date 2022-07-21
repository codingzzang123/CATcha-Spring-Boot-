package com.ib.cat.controller.alert;

import com.ib.cat.service.alert.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyAlertController {

    @Autowired
    AlertService alertService;


}
