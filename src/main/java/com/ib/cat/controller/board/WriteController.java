package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import com.ib.cat.service.member.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class WriteController {

    @Autowired
    private BoardService boardService;

    @Autowired
    FileService fileService;

    //새글 작성 화면을 위한 요청처리
    @RequestMapping(value = "/board/write", method = RequestMethod.GET)
    public String write() throws Exception {

        return "/board/write";
    }

    //새글 등록 위한 요청처리 //파일 업로드
    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public String write(Board board,
                        //@Valid WriteForm writeForm,
                        //BindingResult bindingResult,
                        MultipartFile file) throws IllegalStateException, IOException {

//        if (bindingResult.hasErrors()) {
//            model.addAttribute("write", write);
//            return "/board/write";
//        }
        if (!file.isEmpty()) {
            String path = System.getProperty("user.dir") + "/src/main/resources/static/img/board/";
            String[] img = fileService.fileUpload(file, path);
            board.setImgo(img[0]);
            board.setImgs(img[1]);
        }
        boardService.insert(board);
        return "redirect:/board";
    }
}



