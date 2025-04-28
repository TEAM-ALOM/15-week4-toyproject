package com.example.week4_toy1.likes;
import com.example.week4_toy1.board.Board;
import com.example.week4_toy1.board.BoardRepository;
import com.example.week4_toy1.likes.dto.LikesRequest;
import com.example.week4_toy1.likes.dto.LikesResponse;
import com.example.week4_toy1.user.User;
import com.example.week4_toy1.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
  private final LikeRepository likeRepository;
  private final UserRepository userRepository;
  private final BoardRepository boardRepository;

  public void like(LikesRequest dto) {
    likeRepository.findByBoardIdAndUserId(dto.getBoardId(), dto.getUserId())
        .ifPresent(likes -> {
          throw new RuntimeException("이미 좋아요를 했습니다.");
        });

    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("user not found"));
    Board board = boardRepository.findById(dto.getBoardId())
        .orElseThrow(() -> new RuntimeException("board not found"));

    Likes like = Likes.builder()
        .user(user)
        .board(board)
        .build();
    likeRepository.save(like);
  }

  public void unlike(LikesRequest dto) {
    Likes likes = likeRepository.findByBoardIdAndUserId(dto.getBoardId(), dto.getUserId())
        .orElseThrow(() -> new RuntimeException("likes not found"));

    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("user not found"));
    Board board = boardRepository.findById(dto.getBoardId())
        .orElseThrow(() -> new RuntimeException("board not found"));

     // user id 검증 로직
    if(!likes.getUser().equals(user)) {
      throw new RuntimeException("자신의 좋아요가 아닙니다.");
    }
    // board id 검증 로직
    if(!likes.getBoard().equals(board)) {
      throw new RuntimeException("다른 게시물의 좋아요입니다.");
    }

    likeRepository.delete(likes);
  }

  public List<LikesResponse> getBoardLikes(Long boardId) {
    return LikesResponse.fromList(
        likeRepository.findAllByBoardId(boardId)
    );
  }

  public List<LikesResponse> getUserLikes(Long userId) {
    return LikesResponse.fromList(
        likeRepository.findAllByUserId(userId)
    );
  }
}
