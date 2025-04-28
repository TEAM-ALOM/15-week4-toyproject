package com.example.week4_toy1.board;

import com.example.week4_toy1.board.dto.BoardRequest;
import com.example.week4_toy1.board.dto.BoardResponse;
import com.example.week4_toy1.user.User;
import com.example.week4_toy1.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  public List<BoardResponse> getBoards() {

    return BoardResponse.fromList(
        boardRepository.findAll()
    );
  }

  public BoardResponse getBoardById(Long id) {

    return BoardResponse.from(
        boardRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("user not found"))
    );
  }


  public BoardResponse createBoard(BoardRequest dto) {
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("user not found"));

    Board board = Board.builder()
        .content(dto.getContent())
        .title(dto.getTitle())
        .user(user)
        .build();

    return BoardResponse.from(
        boardRepository.save(board)
    );
  }

  public BoardResponse updateBoard(BoardRequest dto) {
    // 유저 확인 로직
    userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("user not found"));

    Board board = boardRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("user not found"));

    // 객체 정보 수정
    board.setTitle(dto.getTitle());
    board.setContent(dto.getContent());

    // 글쓴이는 바뀌지 않음
    //board.setUser(user);

    return BoardResponse.from(
        boardRepository.save(board)
    );
  }

  public void deleteBoard(Long id) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("user not found"));
    boardRepository.delete(board);
  }
}
