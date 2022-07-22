package com.ib.cat.controller.board;

import com.ib.cat.dto.Board.BoardDetailDto;
import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import com.ib.cat.service.member.FileService;
import com.ib.cat.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ActionController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PagingUtil pagingUtil;

    @PostMapping(value = "/board/delete")
    @ResponseBody
    public String deleteAction(@RequestParam("no") Integer no){
        System.out.println("controller - 받은 번호: "+no);
        boardService.delete(boardService.findBoard(no));
        return "redirect:/board";
    }

    @PostMapping(value = "/board/edit/{no}")
    public String updateAction(@PathVariable Integer no, Board board){
        boardService.update(board);
        return "redirect:/board/"+no;
    }

//    @PostMapping(value = "/board/write")
//    public String write(Board board,
//                        MultipartFile file) throws IllegalStateException, IOException {
//
//        if (!file.isEmpty()) {
//            String path = System.getProperty("user.dir") + "/src/main/resources/static/img/board/";
//            String[] img = fileService.fileUpload(file, path);
//            board.setImgo(img[0]);
//            board.setImgs(img[1]);
//        }
//
//        boardService.insert(board);
//        return "redirect:/board";
//    }

    @PostMapping(value="/board/write")
    public String write(@ModelAttribute("writeForm") Board board) {
        board.setImgo("default");
        board.setImgs("default");
        boardService.insert(board);
        return "redirect:/board";
    }

}

