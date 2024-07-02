package com.example.ToDoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ToDoApp.dto.ToDoDto;
import com.example.ToDoApp.service.ToDoService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/toDos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDoItems(){
        List<ToDoDto> toDos= toDoService.getAllToDoItems();
        return ResponseEntity.ok(toDos);
    }

    @PostMapping("/updateToDoStatus/{id}")
    public ResponseEntity<String> updateToDoStatus(@PathVariable Long id) {
        if (toDoService.updateStatus(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Update Success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update Failure");
        }
    }

    @PostMapping("/addToDoItem")
    public ResponseEntity<String> addToDoItem(@RequestBody ToDoDto toDoDto) {
        boolean result = toDoService.saveToDoItems(toDoDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Add Success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Add Failure");
        }
    }

    @PostMapping("/saveToDoItem")
    public ResponseEntity<String> saveToDoItem(@RequestBody ToDoDto toDoDto){

        if(toDoService.saveToDoItems(toDoDto)){
            return ResponseEntity.status(HttpStatus.OK).body("Save Success");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Save Failure");
    }

    @PutMapping("/{id}/editToDoItem")
    public ResponseEntity<String> editToDoItem(@PathVariable Long id , @RequestBody ToDoDto toDoDto ){
        
        if (toDoDto != null) {
            if(toDoService.editToDoItem(toDoDto)){
                return ResponseEntity.status(HttpStatus.OK).body("Edited Item");
            }
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ToDoItem not found");
    }
    

    @DeleteMapping("/deleteToDoItem/{id}")
    public ResponseEntity<String> deleteToDoItem(@PathVariable Long id) {
        if (toDoService.deleteToDoItem(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete Failure");
        }
    }









    

}
