package com.code.review.domain.board.service;

import com.code.review.domain.board.dto.BoardModifiedRequestDto;
import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.entity.Board;
import com.code.review.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return BoardResponseDto.form(save.getId(), save.getTitle(), save.getContent());
    }


    // 게시물 단건 조회
    public BoardResponseDto getBoard(Long boardId) {
        Board post = findPost(boardId);
        return BoardResponseDto.form(post.getId(), post.getTitle(), post.getContent());
    }

    // 게시물 다건 조회
    public List<BoardResponseDto> findAllBoard() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDto =
                boardList.stream().map(board -> BoardResponseDto.form(
                                board.getId(),
                                board.getContent(),
                                board.getTitle()))
                        .collect(Collectors.toList());
        return boardResponseDto;
    }

    // 게시물 수정
    @Transactional
    public BoardResponseDto modifyBoard(Long boardId, BoardModifiedRequestDto boardModifiedRequestDto) {
        Board post = findPost(boardId);
        post.boardModified(boardModifiedRequestDto.getTitle(), boardModifiedRequestDto.getContent());
        return BoardResponseDto.form(post.getId(), post.getTitle(), post.getContent());
    }

    // 게시물 삭제
    public String deleteBoard(Long boardId) {
        boardRepository.deleteById(findPost(boardId).getId());
        return boardId + "번을 가진 게시물을 삭제 했습니다.";
    }

    // 게시물 찾기
    public Board findPost(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new NullPointerException(id + "를 가진 게시물이 없습니다."));
    }
}