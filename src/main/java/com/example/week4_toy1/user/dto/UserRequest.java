package com.example.week4_toy1.user.dto;


import lombok.Data;

@Data
public class UserRequest {
  private Long id;

  private String email;
  private String password;
  private String nickname;
}
