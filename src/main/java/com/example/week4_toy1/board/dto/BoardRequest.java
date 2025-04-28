package com.example.week4_toy1.board.dto;

import lombok.Data;

@Data
public class BoardRequest {
  private Long id;
  private String title;
  private String content;
  private Long userId;
}
