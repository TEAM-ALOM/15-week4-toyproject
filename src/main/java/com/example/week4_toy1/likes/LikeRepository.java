package com.example.week4_toy1.likes;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
  List<Likes> findAllByBoardId(Long boardId);
  List<Likes> findAllByUserId(Long userId);
  Optional<Likes> findByBoardIdAndUserId(Long boardId, Long userId);
}
