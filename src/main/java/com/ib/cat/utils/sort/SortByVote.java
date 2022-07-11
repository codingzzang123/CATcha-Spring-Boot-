package com.ib.cat.utils.sort;

import com.ib.cat.dto.media.ContentsDto;

import java.util.Comparator;
/* 평점 내림차순 정렬 */
public class SortByVote implements Comparator<ContentsDto> {
    @Override
    public int compare(ContentsDto o1, ContentsDto o2) {
        float first = o1.getVoteAverage();
        float second = o2.getVoteAverage();
        if (first < second) {
            return 1;
        } else if (first > second) {
            return -1;
        } else {
            return 0;
        }
    }
}
