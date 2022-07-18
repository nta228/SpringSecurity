package com.t2009m1.springsecurity.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Credential {
    private String accessToken;
    private String refreshToken;
    private long expiredAt;
    private String scope;
}
