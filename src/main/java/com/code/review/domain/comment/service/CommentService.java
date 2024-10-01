package com.code.review.domain.comment.service;

import com.code.review.domain.board.repository.BoardRepository;
import com.code.review.domain.comment.dto.CommentModifiedRequest;
import com.code.review.domain.comment.dto.CommentRequestDto;
import com.code.review.domain.comment.dto.CommentResponseDto;
import com.code.review.domain.comment.repository.CommentRepository;
import com.code.review.domain.entity.Board;
import com.code.review.domain.entity.Comment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository  boardRepository;

    @Transactional // 댓글 작성
    public CommentResponseDto createComment(Long boardId, CommentRequestDto commentRequestDto) {
        Board post = findPost(boardId);
        Comment save = commentRepository.save(Comment.of(commentRequestDto.getComment(), post));
        return CommentResponseDto.of(save);
    }

    //댓글 전체 조회
    public List<CommentResponseDto> findByIdComment(Long boardId) {
        findPost(boardId);
        List<Comment> comments = commentRepository.findAllByBoard_Id(boardId);

        return comments.stream().map(CommentResponseDto::of).collect(Collectors.toList());
    }

    @Transactional // 댓글 수정
    public CommentResponseDto modifyComment(Long boardId, Long commentId, CommentModifiedRequest modifiedRequest) {
        findPost(boardId);
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() ->
                 new NullPointerException(commentId + "번을 가진 댓글을 찾을 수 없습니다.")
                );
        findComment.commentModified(modifiedRequest.getComment());

        return CommentResponseDto.of(findComment);
    }

    @Transactional // 댓글 삭제
    public String deleteComment(Long boardId, Long commentId) {
        findPost(boardId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException(commentId + "번을 가진 댓글을 찾을 수 없습니다."));
        comment.deleteComment(LocalDateTime.now());

        return "댓글 삭제 성공";
    }

    // 게시물 찾기
    public Board findPost(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new NullPointerException(id + "를 가진 게시물이 없습니다."));
    }
}
