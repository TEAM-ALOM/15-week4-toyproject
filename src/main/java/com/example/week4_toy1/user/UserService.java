package com.example.week4_toy1.user;


import com.example.week4_toy1.user.dto.UserRequest;
import com.example.week4_toy1.user.dto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<UserResponse> getUsers() {
    return UserResponse.fromList(
        userRepository.findAll()
    );
  }

  public UserResponse getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("user not found"));

    return UserResponse.from(user);
  }

  public UserResponse createUser(UserRequest dto) {
    User user = User.builder()
        .id(dto.getId())
        .email(dto.getEmail())
        .password(dto.getPassword())
        .nickname(dto.getNickname())
        .build();

    return UserResponse.from(
        userRepository.save(user)
    );
  }

  public UserResponse updateUser(UserRequest dto) {
    User user = userRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("user not found"));
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setNickname(dto.getNickname());

    return UserResponse.from(
        userRepository.save(user)
    );
  }

  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("user not found"));
    userRepository.delete(user);
  }

}
