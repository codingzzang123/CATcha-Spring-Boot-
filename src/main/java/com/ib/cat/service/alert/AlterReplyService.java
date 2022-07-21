package com.ib.cat.service.alert;

import com.ib.cat.dto.alert.AlertDto;
import com.ib.cat.entity.Alert;
import com.ib.cat.repository.AlertRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterReplyService {
    @Autowired
    AlertRepository alertRepository;

    public AlertDto parserAlert(String object) throws Exception{
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(object);

        AlertDto alertDto = new AlertDto(
                jsonObject.get("subName").toString(),
                jsonObject.get("pubName").toString(),
                Integer.parseInt(String.valueOf(jsonObject.get("bno"))),
                Integer.parseInt(String.valueOf(jsonObject.get("code")))
        );

        return alertDto;
    }

    public void insertReply(String object)throws Exception{
        Alert alert = new Alert();
        AlertDto alertDto = parserAlert(object);

        alert.setSubName(alertDto.getSubName()); alert.setPubName(alertDto.getPubName());
        alert.setBno(alertDto.getBno()); alert.setCode(alertDto.getCode());

        Alert check = alertRepository.findBySubNameAndPubNameAndBnoAndCode(
          alertDto.getSubName(),alertDto.getPubName(),alertDto.getBno(),alertDto.getCode()
        );

        if(check == null)
            alertRepository.save(alert);
    }

    public void deleteReply(String object)throws Exception{
        AlertDto alertDto = parserAlert(object);
        Alert alert = alertRepository.findBySubNameAndPubNameAndBnoAndCode(
                alertDto.getSubName(),alertDto.getPubName(),alertDto.getBno(),
                alertDto.getCode()
        );
        if(alert != null)
            alertRepository.delete(alert);
    }
}
