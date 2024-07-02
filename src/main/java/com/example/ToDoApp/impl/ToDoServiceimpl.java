package com.example.ToDoApp.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.dto.ToDoDto;
import com.example.ToDoApp.entity.ToDo;
import com.example.ToDoApp.mapper.ToDoMapper;
import com.example.ToDoApp.repository.ToDoRepository;
import com.example.ToDoApp.service.ToDoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ToDoServiceimpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public List<ToDoDto> getAllToDoItems() {
       
        List<ToDo> toDoItems = toDoRepository.findAll();
        return toDoItems.stream().map((toDo) -> ToDoMapper.mapToToDoDto(toDo))
                .collect(Collectors.toList());
        
        
    }

    @Override
    public ToDoDto getToDoItemById(Long id) {
        
        ToDo toDo = toDoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("ToDo item does not exist"));

        return ToDoMapper.mapToToDoDto(toDo);        


    }

    @Override
    public boolean updateStatus(Long id) {
       
        ToDo toDo = ToDoMapper.mapToToDo(getToDoItemById(id));
        toDo.setStatus("Completed");

        return saveToDoItems(ToDoMapper.mapToToDoDto(toDo));

    }

    @Override
    public boolean saveToDoItems(ToDoDto toDoDto) {
       
        ToDo toDo = ToDoMapper.mapToToDo(toDoDto);
        ToDo updatedToDo = toDoRepository.save(toDo);

        if(getToDoItemById(updatedToDo.getId()) != null){
            return true;
        }
        return false;
    }
    

    @Override
    public boolean deleteToDoItem(Long id) {
        ToDo toDo = toDoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("To Do does not exist"));
    
        toDoRepository.deleteById(id);

        if(toDoRepository.findById(id).isEmpty()){
            return true;
        }

        return false;

    }

    @Override
    public boolean editToDoItem(ToDoDto toDoDto) {
        ToDo existingToDo = toDoRepository.findById(toDoDto.getId()).orElse(null);
        if (existingToDo != null) {

            existingToDo.setDate(toDoDto.getDate());
            existingToDo.setTitle(toDoDto.getTitle());

            ToDo updatedToDo = toDoRepository.save(existingToDo);
    

            return updatedToDo != null;
        }
        return false;
    }
    


}
