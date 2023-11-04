package com.ea.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE job SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String companyName;
    private Boolean deleted = Boolean.FALSE;

    @Embedded
    private Address address;
}
