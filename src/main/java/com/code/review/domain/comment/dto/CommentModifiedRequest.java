package com.code.review.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentModifiedRequest {

    @NotBlank(message = "댓글 내용을 작성해 주세요.")
    private String comment;
}
