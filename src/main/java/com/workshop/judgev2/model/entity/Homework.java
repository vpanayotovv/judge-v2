package com.workshop.judgev2.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
}
