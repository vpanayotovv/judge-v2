package com.workshop.judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class HomeworkAddBindingModel {

    private String exercise;

    @Pattern(regexp = "(?:git@|https://)github.com[:/](.*)" , message = "github address is not valid")
    private String gitAddress;

}
