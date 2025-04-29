package com.example.week4_toy1.likes;


import com.example.week4_toy1.likes.dto.LikesRequest;
import com.example.week4_toy1.likes.dto.LikesResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
  private final LikeService likeService;
  private final LikeRepository repository;
  @GetMapping("/api/board/{boardId}/likes")
  public ResponseEntity<List<LikesResponse>> getBoardLikeCount(@PathVariable Long boardId) {
    return ResponseEntity.ok(likeService.getBoardLikes(boardId));
  }

  @GetMapping("/api/users/{userId}/likes")
  public ResponseEntity<List<LikesResponse>> getUserLikeCount(@PathVariable Long userId) {
    return ResponseEntity.ok(likeService.getBoardLikes(userId));
  }


  @PostMapping("/api/board/{boardId}/likes")
  public ResponseEntity<String> like(@PathVariable Long boardId,
      @RequestBody LikesRequest dto) {
    dto.setBoardId(boardId);
    likeService.like(dto);
    return ResponseEntity.ok("좋아요 등록 완료");
  }

  @DeleteMapping("/api/board/{boardId}/likes")
  public ResponseEntity<String> deleteLike(@PathVariable Long boardId,
      @RequestBody LikesRequest dto) {
    dto.setBoardId(boardId);
    likeService.unlike(dto);
    return ResponseEntity.ok("좋아요 취소 완료");
  }
}
