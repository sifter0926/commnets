package com.example.demo.domain;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="tbl_board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="regdate")
    private Date regdate;

    @ColumnDefault("0")
    private Long hitcount;
    @ColumnDefault("0")
    private Long replycount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("board")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user; //유저정보

    @PrePersist
    public void prePersist() {
        this.hitcount= this.hitcount==null? 0 : this.hitcount;
        this.replycount= this.replycount==null? 0 : this.replycount;
    }

}
