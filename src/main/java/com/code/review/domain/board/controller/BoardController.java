package com.code.review.domain.board.controller;

import com.code.review.domain.board.dto.BoardModifiedRequestDto;
import com.code.review.domain.board.dto.BoardRequestDto;
import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.createBoard(boardRequestDto));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getFindById(@PathVariable Long boardId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(boardId));
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAllBoard() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findAllBoard());
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> modifyBoard(@PathVariable Long boardId,
                                                        @Valid @RequestBody BoardModifiedRequestDto boardModifiedRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.modifyBoard(boardId, boardModifiedRequestDto));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.deleteBoard(boardId));
    }
}

