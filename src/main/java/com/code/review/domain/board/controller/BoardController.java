package com.code.review.domain.board.controller;

import com.code.review.domain.board.dto.BoardModifiedRequestDto;
import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping // 게시물 작성
    public ResponseEntity<BoardResponseDto> createBoard(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.createBoard(boardRequestDto));
    }

    @GetMapping("/{boardId}") // 게시물 단건 조회
    public ResponseEntity<BoardResponseDto> readBoard(@PathVariable Long boardId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(boardId));
    }

    @GetMapping// 게시물 전체 조회
    public ResponseEntity<List<BoardResponseDto>> findAllBoard(@RequestParam(defaultValue = "1", required = false) int page,
                                                               @RequestParam(defaultValue = "10", required = false) int size) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findAllBoard(page, size));
    }

    @PutMapping("/{boardId}") // 게시물 수정
    public ResponseEntity<BoardResponseDto> modifyBoard(@PathVariable Long boardId,
                                                        @Valid @RequestBody BoardModifiedRequestDto boardModifiedRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.modifyBoard(boardId, boardModifiedRequestDto));
    }

    @DeleteMapping("/{boardId}") // 게시물 삭제
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.deleteBoard(boardId));
    }
}

