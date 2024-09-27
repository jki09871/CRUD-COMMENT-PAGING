package com.code.review.domain.board.service;

import com.code.review.domain.board.dto.BoardModifiedRequestDto;
import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.board.repository.BoardRepository;
import com.code.review.domain.comment.repository.CommentRepository;
import com.code.review.domain.entity.Board;
import com.code.review.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional // 게시물 작성
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = Board.of(
                boardRequestDto.getTitle(),
                boardRequestDto.getContent());
        Board save = boardRepository.save(board);
        List<Comment> allByBoard_id = commentRepository.findAllByBoard_Id(save.getId());

        return BoardResponseDto.of(save);
    }


    // 게시물 단건 조회
    public BoardResponseDto getBoard(Long boardId) {
        Board post = findPost(boardId);
        return BoardResponseDto.of(post);
    }

    // 게시물 다건 조회
    public Page<BoardResponseDto> findAllBoard(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        Page<Board> boardList = boardRepository.findAllByDeletedAtIsNull(pageable);

        return  boardList.map(BoardResponseDto::of);
    }

    // 게시물 수정
    @Transactional
    public BoardResponseDto modifyBoard(Long boardId, BoardModifiedRequestDto boardModifiedRequestDto) {
        Board post = findPost(boardId);
        post.boardModified(boardModifiedRequestDto.getTitle(), boardModifiedRequestDto.getContent());
        return BoardResponseDto.of(post);
    }


    @Transactional    // 게시물 삭제
    public String deleteBoard(Long boardId) {
        Board post = findPost(boardId);
        post.boardDeletedAt(LocalDateTime.now());
        return boardId + "번을 가진 게시물을 삭제 했습니다.";
    }

    // 게시물 찾기
    public Board findPost(Long id) {
        return boardRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                    new NullPointerException("해당 댓글이 없습니다.")
                );
    }

}