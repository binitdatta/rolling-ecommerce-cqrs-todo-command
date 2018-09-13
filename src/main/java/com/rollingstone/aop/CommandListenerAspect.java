package com.rollingstone.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

@Aspect
@Component
public class CommandListenerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	Counter genericAPICallCounter = Metrics.counter("com.rollingstone.generic.listener.counter");

	Counter createTodoCounter = Metrics.counter("com.rollingstone.TodoService.saveTodo");


	@Before("execution(public * com.rollingstone.*Listener.*(..))")
	public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
		logger.info(":::::AOP Before for Any Listener call:::::" + pjp);
		genericAPICallCounter.increment();
	}

	@AfterReturning("execution(public * com.rollingstone.service.*Service.saveTodo*(..))")
	public void afterCallingCreateTodo(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning Create Todo Service call:::::" + pjp);
		createTodoCounter.increment();
	}

	
}
