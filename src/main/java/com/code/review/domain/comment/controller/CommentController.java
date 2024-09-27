package com.code.review.domain.comment.controller;

import com.code.review.domain.comment.dto.CommentModifiedRequest;
import com.code.review.domain.comment.dto.CommentRequestDto;
import com.code.review.domain.comment.dto.CommentResponseDto;
import com.code.review.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{boardId}") // 댓글 작성
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long boardId, @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.createComment(boardId, commentRequestDto));
    }

    @GetMapping("/{boardId}/comment") // 해당 게시물 댓글 전체 보기
    public ResponseEntity<List<CommentResponseDto>> readComment(@PathVariable Long boardId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findByIdComment(boardId));
    }

    @PutMapping("/{boardId}/comment/{commentId}") // 해당 게시물 댓글 수정
    public ResponseEntity<CommentResponseDto> modifyComment(@PathVariable Long boardId,
                                                            @PathVariable Long commentId,
                                                            @Valid @RequestBody CommentModifiedRequest modifiedRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.modifyComment(boardId, commentId, modifiedRequest));
    }

    @DeleteMapping("/{boardId}/comment/{commentId}") // 해당 게시물 댓글 삭제
    public ResponseEntity<String> deleteComment(@PathVariable Long boardId,
                                                @PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(boardId, commentId));
    }

}
