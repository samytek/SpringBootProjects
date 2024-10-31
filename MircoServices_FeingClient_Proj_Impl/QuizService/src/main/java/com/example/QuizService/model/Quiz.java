package com.example.QuizService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_seq_gen")
    @SequenceGenerator(name = "quiz_seq_gen", sequenceName = "quiz_seq", allocationSize = 1)    private Integer id;
    private String title;

    @ElementCollection
    @CollectionTable(name = "Quiz")
    private List<Integer> questionIds;

}
