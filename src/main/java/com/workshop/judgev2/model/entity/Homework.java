package com.workshop.judgev2.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "homeworks")
@Getter
@Setter
@NoArgsConstructor
public class Homework extends BaseEntity {

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @Column(name = "git_address",nullable = false)
    private String gitAddress;

    @ManyToOne
    private User author;

    @ManyToOne
    private Exercise exercise;

    @OneToMany(mappedBy = "homework",fetch = FetchType.EAGER)
    private List<Comment> comments;
}
