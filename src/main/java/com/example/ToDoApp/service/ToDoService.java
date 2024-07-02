package com.example.ToDoApp.service;

import java.util.List;
import com.example.ToDoApp.dto.ToDoDto;


public interface ToDoService {

    List<ToDoDto> getAllToDoItems();

    ToDoDto getToDoItemById(Long id);

    boolean updateStatus(Long id);

    boolean saveToDoItems( ToDoDto toDoDto);

    boolean editToDoItem(ToDoDto toDoDto);

    boolean deleteToDoItem(Long id);

}
