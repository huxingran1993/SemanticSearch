package com.interview.cognizone.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {

    private Long id;

    @NotBlank(message = "W1 can not be empty")
    private String W1;

    @NotBlank(message = "W2 can not be empty")
    private String W2;

    @NotBlank(message = "R can not be empty")
    private String R;
}
