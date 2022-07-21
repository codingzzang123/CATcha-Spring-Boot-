package com.ib.cat.controller.board;

import com.ib.cat.entity.Board;
import com.ib.cat.service.board.BoardService;
import com.ib.cat.service.member.FileService;
import com.ib.cat.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(value = "/board/delete/{no}")
    public String deleteAction(@PathVariable Integer no){
        boardService.delete(boardService.getOne(no));
        return "redirect:/board";
    }

    @PostMapping(value = "/board/edit/{no}")
    public String updateAction(@PathVariable Integer no, Board board){
        boardService.update(board);
        return "redirect:/board/"+no;
    }

    @PostMapping(value = "/board/write")
    public String write(Board board,
                        MultipartFile file) throws IllegalStateException, IOException {

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

