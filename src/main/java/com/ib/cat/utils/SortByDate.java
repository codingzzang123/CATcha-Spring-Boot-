package com.ib.cat.utils;

import com.ib.cat.dto.ContentsDto;

import java.util.Comparator;
import java.util.Date;

public class SortByDate implements Comparator<ContentsDto> {

    @Override
    public int compare(ContentsDto o1, ContentsDto o2) {
        Date first = o1.getReleaseDate();
        Date second = o2.getReleaseDate();
        return second.compareTo(first);
    }

}
