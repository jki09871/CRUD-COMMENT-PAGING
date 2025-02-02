package com.code.review.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "boards")
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, length = 3000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();


    private Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Board of(String title, String content) {
        return new Board(title, content);
    }

    

    public void boardModified(String title, String content) {
       if (title != null) this.title = title;
       if (content != null) this.content = content;
    }

    public void boardDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
