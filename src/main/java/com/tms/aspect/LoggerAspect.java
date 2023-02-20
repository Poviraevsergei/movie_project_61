package com.tms.aspect;

import com.tms.domain.Actor;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class LoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.tms.*.*(String, Long))")
    public void f(){}

    @Pointcut("execution(public * com.tms.*.*(String))")
    public void ff(){}

    @Around("f() || ff()")
    public void getLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        joinPoint.proceed();
        LocalTime end = LocalTime.now();
        log.info("Method worked time: " + (end.getNano() - start.getNano()));
    }

    @Around("@annotation(com.tms.annotation.GetTimeAnnotation)")
    public void getTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        joinPoint.proceed();
        LocalTime end = LocalTime.now();
        log.info("Method worked time: " + (end.getNano() - start.getNano()));
    }

    @Before("within(com.tms.*)")
    public void getLogBefore(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature() + " started!");
    }

    @After("within(com.tms.*)")
    public void getLogAfter(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature() + " finished!");
    }

    @AfterReturning(value = "within(com.tms.*)", returning = "whatWeWait")
    public void getLogAfterReturning(Object whatWeWait) {
        log.info("Log after returning! " + whatWeWait);
    }

    @AfterThrowing(value = "within(com.tms.*)", throwing = "err")
    public void getLogAfterThrowing(Throwable err) {
        log.warn("WE HAVE THROW!!!!!! " + err);
    }
}