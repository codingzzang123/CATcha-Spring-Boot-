package com.ib.cat.dto.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
public class MemberDto {
    @NotEmpty
    @Pattern(regexp ="^[0-9a-zA-Z][0-9a-zA-Z]{6,12}$")
    private String id;
    @NotEmpty
    @Pattern(regexp ="^^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")
    private String pw;
    @NotEmpty
    @Pattern(regexp ="^(?=.*[A-Za-z0-9가-힣])[A-Za-z0-9가-힣]{2,16}$")
    private String name;
    @NotEmpty
    @Pattern(regexp ="^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")
    private String email;
    private String imgo;
    private String imgs;
    private int auth;
}
