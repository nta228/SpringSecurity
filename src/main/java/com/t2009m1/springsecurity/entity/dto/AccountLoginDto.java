package com.t2009m1.springsecurity.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginDto {
    private String username;
    private String password;
}