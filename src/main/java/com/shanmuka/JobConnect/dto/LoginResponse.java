package com.shanmuka.JobConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse
{
    private String token;
    private Integer userId;
    private String role;

    public LoginResponse(String token,Integer userId, String role)
    {
        this.token = token;
        this.userId = userId;
        this.role = role;
    }
}
