package com.code.review.domain.board.repository;

import com.code.review.domain.entity.Board;
import com.code.review.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndDeletedAtIsNull(Long boardId);

    Page<Board> findAllByDeletedAtIsNull(Pageable pageable);
}
