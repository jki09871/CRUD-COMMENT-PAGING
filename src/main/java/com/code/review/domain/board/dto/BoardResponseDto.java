package com.code.review.domain.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static BoardResponseDto form(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new BoardResponseDto(
                id,
                title,
                content,
                createdAt,
                modifiedAt
        );
    }
}
