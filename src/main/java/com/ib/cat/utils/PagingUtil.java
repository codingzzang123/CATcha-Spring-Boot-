package com.ib.cat.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PagingUtil {

    private static final int pageSize = 20;
    private static final int pageBlock = 10; //블럭 갯수는 10개

    private int curPage;
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    private int total;


    public void startPaging(int pageNum, int total) {
        setCurPage(pageNum);
        makeLastPageNum(total);
        makeBlock(pageNum);
    }

    public void makeBlock(int curPage){
        int blockNum = 0;

        blockNum = (int)Math.floor((curPage-1)/ pageBlock);
        blockStartNum = (pageBlock * blockNum) + 1;

        blockLastNum = blockStartNum + (pageBlock-1);

        if(blockLastNum != 0 && lastPageNum !=0) {
            if(blockLastNum / lastPageNum > 0)
                blockLastNum = lastPageNum;
        }
    }

    public void makeLastPageNum(int total) {
        if( total % pageSize == 0 ) {
            lastPageNum = (int)Math.floor(total/pageSize);
        }
        else {
            lastPageNum = (int)Math.floor(total/pageSize) + 1;
        }
    }
}
