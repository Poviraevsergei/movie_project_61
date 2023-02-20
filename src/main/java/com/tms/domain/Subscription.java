package com.tms.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class Subscription {
    private int id;
    private Date expireDate;
}
