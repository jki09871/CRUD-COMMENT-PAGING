package com.code.review.domain.board.dto;

import com.code.review.domain.comment.dto.CommentResponseDto;
import com.code.review.domain.entity.Board;
import com.code.review.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public static BoardResponseDto of(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getComments());
    }

    private BoardResponseDto(Long id, String title, String content, List<Comment> commentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentList = commentList.stream()
                .map(comment ->
                        CommentResponseDto.builder()
                                .id(comment.getId())
                                .boardId(comment.getBoard().getId())
                                .comment(comment.getComment())
                                .build())
                                .toList();
    }
}
