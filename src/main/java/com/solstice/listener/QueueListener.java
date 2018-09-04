package com.solstice.listener;

import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import com.solstice.service.TodoService;
import com.solstice.command.TodoCommand;
import com.solstice.command.GenericCommandType;

import com.solstice.domain.Todo;

@Service
@SuppressWarnings("unused")
public class QueueListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TodoService todoService;

    public QueueListener(TodoService todoService){
        this.todoService = todoService;

    }

    @SqsListener(value = "${sqs.todo.queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void handleCreate(TodoCommand todoCommand){

        log.info("TodoMessage received");

        String type = todoCommand.getHeader().getCommandType();

        Todo todo = todoCommand.getTodo();

        if (type.equalsIgnoreCase(GenericCommandType.CREATE_TODO.toString())){
            todoService.saveTodo(todo);
        }else if (type.equalsIgnoreCase(GenericCommandType.CREATE_TODO.toString())){
            //update todo
            todoService.saveTodo(todo);
        }


    }
}
