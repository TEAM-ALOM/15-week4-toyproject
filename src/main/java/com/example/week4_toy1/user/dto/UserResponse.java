package com.example.week4_toy1.user.dto;

import com.example.week4_toy1.user.User;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

  private Long id;
  private String email;
  private String nickname;


  public static UserResponse from(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .nickname(user.getNickname())
        .build();
  }

  public static List<UserResponse> fromList(List<User> users) {
    List<UserResponse> list = new ArrayList<>();

    for(User user : users)
      list.add(from(user));

    return list;
  }
}