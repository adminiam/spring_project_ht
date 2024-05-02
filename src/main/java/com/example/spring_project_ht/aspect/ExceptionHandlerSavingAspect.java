package com.example.spring_project_ht.aspect;

import com.example.spring_project_ht.Services.UserService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "application.component", havingValue = "JDBC")
public class ExceptionHandlerSavingAspect {
    private final UserService userService;

    public ExceptionHandlerSavingAspect(UserService userService) {
        this.userService = userService;
    }


    @Pointcut("execution(* com.example.spring_project_ht.Controllers.UserController.*(..))")
    public void savingExceptionPointCut() {
    }

    @AfterThrowing(pointcut = "savingExceptionPointCut()", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        userService.addUserException(String.valueOf(exception.getClass()).substring(16));
    }
}
