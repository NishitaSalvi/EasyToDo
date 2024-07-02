package com.example.ToDoApp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoDto {

    private Long id;
    private String status;
    private LocalDateTime date;
    private String title;
    

}
