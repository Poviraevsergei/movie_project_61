package com.tms;

import com.tms.domain.Actor;
import org.springframework.stereotype.Component;

@Component
public class SomeLogic {

    public void firstTestMethod(String name, Long l) {
        System.out.println("HELLO 1");
        System.out.println("HELLO 2");
        secondTestMethod();
        SomeLogicSecond someLogicSecond = new SomeLogicSecond();
        someLogicSecond.hello();
        System.out.println("HELLO 3");
    }

    public Actor secondTestMethod() {
        System.out.println("Bye 1");
        System.out.println("Bye 2");
        System.out.println("Bye 3");
        return new Actor();
    }
}