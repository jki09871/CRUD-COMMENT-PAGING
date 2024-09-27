package com.code.review.domain.comment.dto;

import com.code.review.domain.board.dto.BoardResponseDto;
import com.code.review.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {

    private Long id;
    private Long boardId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getBoardId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

}
