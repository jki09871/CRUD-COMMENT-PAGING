package com.code.review.domain.comment.dto;

import com.code.review.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {

    private Long id;
    private Long boardId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;


    public static CommentResponseDto of(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getBoardId(),
                comment.getComment(), comment.getCreatedAt(), comment.getModifiedAt(), comment.getDeletedAt());
    }


    private CommentResponseDto(Long id, Long boardId,
                                    String comment, LocalDateTime createdAt,
                                    LocalDateTime modifiedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.boardId = boardId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

}
