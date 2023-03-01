package com.tms.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@CacheEvict
public class ExceptionResolver {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ArithmeticException.class)
    public String muFirstExHand(Exception e){
        log.warn("ArithmeticException: " + e);
        return "unsuccessfully";
    }
}
