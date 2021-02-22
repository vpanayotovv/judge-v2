package com.workshop.judgev2.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileViewModel {

    private String username;
    private List<String> homeworks;
    private String email;
    private String git;

}
