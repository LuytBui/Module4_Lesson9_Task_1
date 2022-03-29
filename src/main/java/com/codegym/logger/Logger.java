package com.codegym.logger;

import com.codegym.model.Vote;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class Logger {
    @AfterThrowing(pointcut = "execution(public * com.codegym.controller.VoteController.*(..))", throwing = "e")
    public void log(JoinPoint joinPoint, Exception e) {
        Vote vote = (Vote) joinPoint.getArgs()[0];

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);

        System.out.println(String.format("%s : VoteID: %s | Author: %s | Comment: %s | Time: %s",
                e.getMessage(), vote.getId(), vote.getAuthor(), vote.getComment(), currentDateTime));
    }
}
