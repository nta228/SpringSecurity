package com.t2009m1.springsecurity.entity.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialDto {
    private String accessToken;
    private String refreshToken;
    private long expiredAt;
    private String scope;


}
