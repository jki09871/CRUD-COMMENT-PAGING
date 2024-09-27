package com.code.review.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false, length = 500)
    private String comment;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;


    private Comment(String comment, Board board) {
        this.comment = comment;
        this.board = board;
    }

    public static Comment of(String comment, Board board) {
        return new Comment(comment, board);
    }

    public Long getBoardId() {
        return board != null ? board.getId() : null;
    }

    public void deleteComment(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void commentModified(String comment) {
        if (comment != null) this.comment = comment;
    }
}
