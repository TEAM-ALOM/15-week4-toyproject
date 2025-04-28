package com.example.week4_toy1.board.dto;

import com.example.week4_toy1.board.Board;
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
public class BoardResponse {
  private Long id;

  private String title;
  private String content;
  private Long userId;
  private String username;

  public static BoardResponse from(Board board) {
    return BoardResponse.builder()
        .content(board.getContent())
        .id(board.getId())
        .title(board.getTitle())
        .userId(board.getUser().getId())
        .username(board.getUser().getNickname())
        .build();
  }
  public static List<BoardResponse> fromList(List<Board> boards) {
    List<BoardResponse> list = new ArrayList<>();

    for (Board board : boards)
      list.add(from(board));

    return list;
  }
}
