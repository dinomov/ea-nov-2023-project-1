package com.ea.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE experience SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private Long from;
    @Column(name = "end_date")
    private Long to;
    private String company;
    private String title;
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User student;
}
