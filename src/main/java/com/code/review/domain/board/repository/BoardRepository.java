package com.code.review.domain.board.repository;

import com.code.review.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {

}
