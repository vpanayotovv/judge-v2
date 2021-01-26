package com.workshop.judgev2.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
public class Exercise extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "stared_on")
    private LocalDateTime startedOn;

    @Column(name = "due_date")
    private LocalDateTime dueDate;


}
