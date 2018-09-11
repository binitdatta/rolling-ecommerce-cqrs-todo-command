package com.rollingstone.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.rollingstone.domain.Todo;
import com.rollingstone.repositories.TodoRepository;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void saveTodo(Todo todo){
        todoRepository.save(todo);
    }
}
