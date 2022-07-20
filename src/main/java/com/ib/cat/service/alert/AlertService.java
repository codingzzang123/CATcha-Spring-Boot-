package com.ib.cat.service.alert;

import com.ib.cat.dto.alert.AlertDto;
import com.ib.cat.entity.Alert;
import com.ib.cat.entity.Board;
import com.ib.cat.entity.Member;
import com.ib.cat.repository.AlertRepository;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.repository.MemberRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    public int alertCount(String subName){
        return alertRepository.countBySubName(subName);
    }

    public void insert(String object)throws Exception{
        Alert alert = new Alert();
        AlertDto alertDto = parserAlert(object);
        alert.setSubName(alertDto.getSubName()); alert.setPubName(alertDto.getPubName());
        alert.setBno(alertDto.getBno()); alert.setCode(alertDto.getCode());

        Alert check = alertRepository.findBySubNameAndPubNameAndBnoAndCode(
                alertDto.getSubName(),alertDto.getPubName(),alertDto.getBno(),alertDto.getCode()
        );

//        if(check == null)
            alertRepository.save(alert);
//        else
//            System.out.println("duplicated!");
    }

    public void deleteAlertBoard(String object)throws Exception{
        AlertDto alertDto = parserAlert(object);
        Alert alert = alertRepository.findBySubNameAndPubNameAndBnoAndCode(
          alertDto.getSubName(),alertDto.getPubName(),alertDto.getBno(),
          alertDto.getCode()
        );
        if(alert != null)
            alertRepository.delete(alert);
    }

    public void deleteAlertNav(Integer no){
        Optional<Alert> alert = alertRepository.findById(no);
        if (alert.isPresent()){
            alertRepository.delete(alert.get());
        }
    }


    public AlertDto parserAlert(String object)throws Exception{
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

    public List<AlertDto> getList(String name){
        List<Alert> list = alertRepository.findBySubNameOrderByNoDesc(name);
        List<AlertDto> results = new ArrayList<>();
        Member member;

        for(Alert alert : list){
            member = memberRepository.findByName(alert.getPubName());
            String img = member.getImgs();

            String title="";
            Optional<Board> board = boardRepository.findById(alert.getBno());
            if(board.isPresent())
                title = board.get().getTitle();



            AlertDto alertDto = new AlertDto(
                    alert.getNo(), alert.getSubName(), alert.getPubName(),
                    alert.getBno(),alert.getCode(),alert.getRegdate(),img,title
            );
            results.add(alertDto);
        }
        return  results;
    }
}
