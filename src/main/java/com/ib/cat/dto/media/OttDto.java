package com.ib.cat.dto.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OttDto {

    private String logoPath;
    private int providerId;
    private String providerName;
    private String ottAdd;

}
