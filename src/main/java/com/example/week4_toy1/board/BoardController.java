package com.example.week4_toy1.board;


import com.example.week4_toy1.board.dto.BoardRequest;
import com.example.week4_toy1.board.dto.BoardResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ResponseBody + @Controller
@RequiredArgsConstructor
//@RequestMapping
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/api/board")
  public ResponseEntity<List<BoardResponse>> getUsers() {
    return ResponseEntity.ok(boardService.getBoards());
  }

  @GetMapping("/api/board/{boardId}")
  public ResponseEntity<BoardResponse> getBoards(@PathVariable Long boardId) {
    return ResponseEntity.ok(boardService.getBoardById(boardId));
  }

  @PostMapping("/api/board")
  public ResponseEntity<BoardResponse> getBoard(@RequestBody BoardRequest dto) {
    return ResponseEntity.ok(boardService.createBoard(dto));
  }

  @PutMapping("/api/board/{boardId}")
  public ResponseEntity<BoardResponse> updateBoard(@PathVariable Long boardId,
      @RequestBody BoardRequest dto) {
    dto.setId(boardId);
    return ResponseEntity.ok(boardService.updateBoard(dto));
  }


  @DeleteMapping("/api/board/{boardId}")
  public ResponseEntity<String> deleteBoard(@RequestParam Long boardId) {
    boardService.deleteBoard(boardId);
    return ResponseEntity.ok("삭제 완료");
  }

}
