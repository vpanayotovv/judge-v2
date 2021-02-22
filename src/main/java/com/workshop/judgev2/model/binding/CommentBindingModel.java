package com.workshop.judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CommentBindingModel {

    @Min(value = 2,message = "Must be more then two")
    @Max(value = 6,message = "Must be lower then six")
    private Integer score;

    @Size(min = 3,message = "Description have to be minimum 3 chars")
    @NotBlank(message = "Enter description")
    private String textContent;

    private Long homeworkId;
}
