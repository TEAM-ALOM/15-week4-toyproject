package com.example.week4_toy1.user;


import com.example.week4_toy1.user.dto.UserRequest;
import com.example.week4_toy1.user.dto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;


  @GetMapping("/api/users")
  public ResponseEntity<List<UserResponse>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  //URI 경로
  @GetMapping("/api/users/{userId}")
  public ResponseEntity<UserResponse> getUsers(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.getUserById(userId));
  }

  /*
  *
  쿼리스트링 : /api/users?userId=2 로 요청받기

  @GetMapping("/api/users")
  public ResponseEntity<UserResponse> getUsers(@RequestParam Long userId) {
   ~~~~~~~~~~~
  *
  * */

  @PostMapping("/api/users")
  public ResponseEntity<UserResponse> getUsers(@RequestBody UserRequest dto) {
    return ResponseEntity.ok(userService.createUser(dto));
  }

  @PutMapping("/api/users/{userId}")
  public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                      @RequestBody UserRequest dto) {
    dto.setId(userId);
    return ResponseEntity.ok(userService.updateUser(dto));
  }


  @DeleteMapping("/api/users/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.ok("삭제 완료");
  }

}
