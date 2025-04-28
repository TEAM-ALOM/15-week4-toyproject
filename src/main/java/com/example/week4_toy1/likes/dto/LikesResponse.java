package com.example.week4_toy1.likes.dto;


import com.example.week4_toy1.likes.Likes;
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
public class LikesResponse {
  private Long id;
  private Long userId;
  private Long boardId;

  public static LikesResponse from(Likes likes) {
    return LikesResponse.builder()
        .id(likes.getId())
        .userId(likes.getUser().getId())
        .boardId(likes.getBoard().getId())
        .build();
  }

  public static List<LikesResponse> fromList(List<Likes> likes) {
    List<LikesResponse> list = new ArrayList<>();
    for(Likes like : likes)
      list.add(from(like));

    return list;
  }
}
