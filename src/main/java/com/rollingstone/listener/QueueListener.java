package com.rollingstone.listener;

import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import com.rollingstone.command.GenericCommandType;
import com.rollingstone.command.TodoCommand;
import com.rollingstone.domain.Todo;
import com.rollingstone.service.TodoService;

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

        log.info("Todo :"+ todo.toString());
                 
        if (type.equalsIgnoreCase(GenericCommandType.CREATE_TODO.toString())){
            todoService.saveTodo(todo);
        }else if (type.equalsIgnoreCase(GenericCommandType.UPDATE_TODO.toString())){
            //update todo
            todoService.saveTodo(todo);
        }


    }
}
