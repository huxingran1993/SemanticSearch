package com.interview.cognizone.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Document("relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    @Id
    String id;

    @Field("W1")
    @NotBlank(message = "W1 can not be empty")
    /**
     * Task 6: Limit allowed characters
     * */
    @Pattern(regexp = "^[A-Za-z ]*$", message = "Only characters from A to Z (both lower and uppercase) and space are allowed")
    private String W1;

    @Field("W2")
    @NotBlank(message = "W2 can not be empty")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "Only characters from A to Z (both lower and uppercase) and space are allowed")
    private String W2;

    @Field("R")
    @NotBlank(message = "R can not be empty")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "Only characters from A to Z (both lower and uppercase) and space are allowed")
    private String R;
}
