package com.ib.cat.dto.main;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter @Getter
public class SearchCountDTO {
    private int movie;
    private int tv;
    private int board;
}
