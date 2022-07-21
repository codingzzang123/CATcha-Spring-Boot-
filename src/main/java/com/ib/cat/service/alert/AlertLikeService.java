package com.ib.cat.service.alert;

import com.ib.cat.dto.alert.AlertDto;
import com.ib.cat.entity.Alert;
import com.ib.cat.entity.Board;
import com.ib.cat.repository.AlertRepository;
import com.ib.cat.repository.BoardRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertLikeService {
    @Autowired
    AlertRepository alertRepository;

    @Autowired
    BoardRepository boardRepository;

    public void insertLike(String object)throws Exception{
        /*  Alert table add tuple */
        Alert alert = new Alert();
        AlertDto alertDto = parserAlert(object);
        alert.setSubName(alertDto.getSubName()); alert.setPubName(alertDto.getPubName());
        alert.setBno(alertDto.getBno()); alert.setCode(alertDto.getCode());

        alertRepository.save(alert);

        /* Update Board Table likes +1 */
        Optional<Board> board = boardRepository.findById(alertDto.getBno());
        board.ifPresent(e -> {
            e.setLikes(e.getLikes() + 1);
        });
        boardRepository.save(board.get());
    }

    public void deleteLike(String object)throws Exception{
        /*  Alert table delete tuple */
        AlertDto alertDto = parserAlert(object);
        Alert alert = alertRepository.findBySubNameAndPubNameAndBnoAndCode(
                alertDto.getSubName(),alertDto.getPubName(),alertDto.getBno(),
                alertDto.getCode()
        );
        if(alert != null)
            alertRepository.delete(alert);

        /* Update Board Table likes -1 */
        Optional<Board> board = boardRepository.findById(alertDto.getBno());
        board.ifPresent(e ->{
            e.setLikes(e.getLikes()-1);
        });
        boardRepository.save(board.get());
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
}
