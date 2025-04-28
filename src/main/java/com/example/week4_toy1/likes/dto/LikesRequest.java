package com.example.week4_toy1.likes.dto;


import lombok.Data;

@Data
public class LikesRequest {
  private Long id;
  private Long userId;
  private Long boardId;
}
