package com.example.ToDoApp.mapper;

import com.example.ToDoApp.dto.ToDoDto;
import com.example.ToDoApp.entity.ToDo;

public class ToDoMapper {

    public static ToDo mapToToDo(ToDoDto toDoDto){
        
        ToDo toDo = new ToDo(
            toDoDto.getId(),
            toDoDto.getStatus(),
            toDoDto.getDate(),
            toDoDto.getTitle()
            
            
        );

        return toDo;

    }

    public static ToDoDto mapToToDoDto(ToDo toDo){
        
        ToDoDto toDoDto = new ToDoDto(
            toDo.getId(),
            toDo.getStatus(),
            toDo.getDate(),
            toDo.getTitle()
        );

        return toDoDto;

    }
}
