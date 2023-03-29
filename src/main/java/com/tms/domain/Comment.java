package com.tms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"user"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "com_id_seq_gen")
    @SequenceGenerator(name = "com_id_seq_gen", sequenceName = "comments_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
