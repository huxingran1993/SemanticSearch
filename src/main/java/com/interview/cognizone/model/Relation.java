package com.interview.cognizone.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

@Document("relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    @Id
    String id;
//    private Long id;

    @Field("W1")
    @NotBlank(message = "W1 can not be empty")
    private String W1;

    @Field("W2")
    @NotBlank(message = "W2 can not be empty")
    private String W2;

    @Field("R")
    @NotBlank(message = "R can not be empty")
    private String R;
}
