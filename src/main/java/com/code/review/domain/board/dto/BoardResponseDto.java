package com.code.review.domain.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;

    public static BoardResponseDto form(Long id, String title, String content) {
        return new BoardResponseDto(
                id,
                title,
                content
        );
    }
}
