package com.ib.cat.dto.main;

import lombok.Data;

@Data
public class SearchBoardDTO {
    private int start;
    private int end;
    private String query;
}
