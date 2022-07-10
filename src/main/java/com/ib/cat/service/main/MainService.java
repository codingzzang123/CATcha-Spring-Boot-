package com.ib.cat.service.main;

import com.ib.cat.dto.main.BoardTopDto;
import com.ib.cat.entity.Board;
import com.ib.cat.entity.Visit;
import com.ib.cat.repository.BoardRepository;
import com.ib.cat.repository.VisitRepository;
import com.ib.cat.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MainService {
    @Autowired
    CookieUtil cookieUtil;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    VisitRepository visitRepository;

    /* 쿠키 -> 방문자 카운트 */
    public void isCookie(HttpServletRequest request, HttpServletResponse response){
//        if(!cookieUtil.isCookie(request)) {
//            cookieUtil.createCookie(response);
            Visit visit = new Visit();
            visit.setReg(LocalDate.now());
            visitRepository.save(visit);
            System.out.printf("실행");
//        }
    }

    public Integer getTotal(){
        return visitRepository.countAllBy();
    }

    @Transactional
    public Long getToday() {
        return visitRepository.countToday(LocalDate.now());

    }

    /* 최근 게시물 5개 */
    public List<BoardTopDto> getBoardTop(){
        List<Board> ls = boardRepository.findTop5ByOrderByNoDesc();
        List<BoardTopDto> btd = new ArrayList<>();

        for(Board b : ls){
            btd.add(new BoardTopDto(
                    b.getNo(), b.getTitle(),b.getName(),b.getRegDate()
            ));
        }

        return btd;
    }

}
