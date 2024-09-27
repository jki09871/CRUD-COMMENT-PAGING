package com.code.review.domain.board.service;

import com.code.review.domain.board.dto.BoardModifiedRequestDto;
import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.entity.Board;
import com.code.review.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // 게시물 작성
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = Board.of(
                boardRequestDto.getTitle(),
                boardRequestDto.getContent());
        Board save = boardRepository.save(board);
        return BoardResponseDto.form(save.getId(), save.getTitle(), save.getContent(),save.getCreatedAt(),save.getModifiedAt());
    }


    // 게시물 단건 조회
    public BoardResponseDto getBoard(Long boardId) {
        Board post = findPost(boardId);
        return BoardResponseDto.form(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getModifiedAt());
    }

    // 게시물 다건 조회
    public List<BoardResponseDto> findAllBoard(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        Page<Board> boardList = boardRepository.findAllByDeletedAtIsNull(pageable);
        List<BoardResponseDto> boardResponseDto =
                boardList.stream().map(board -> BoardResponseDto.form(
                                board.getId(),
                                board.getContent(),
                                board.getTitle(),
                                board.getCreatedAt(),
                                board.getModifiedAt()))
                                .collect(Collectors.toList());
        return  boardResponseDto;
    }

    // 게시물 수정
    @Transactional
    public BoardResponseDto modifyBoard(Long boardId, BoardModifiedRequestDto boardModifiedRequestDto) {
        Board post = findPost(boardId);
        post.boardModified(boardModifiedRequestDto.getTitle(), boardModifiedRequestDto.getContent());
        return BoardResponseDto.form(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getModifiedAt());
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