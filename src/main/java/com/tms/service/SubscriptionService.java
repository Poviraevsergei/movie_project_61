package com.tms.service;

import com.tms.domain.Subscription;
import com.tms.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public boolean add(int userId) {
        return subscriptionRepository.add(userId);
    }

    public Subscription getSubscriptionById(int id) {
        return subscriptionRepository.getSubscriptionById(id);
    }

    public boolean createSubscription(int userId) {
        return subscriptionRepository.createSubscription(userId);
    }

    public boolean updateSubscription(int id, Date expireDate) {
        return subscriptionRepository.updateSubscription(id, expireDate);
    }

    public boolean deleteSubscription(int id) {
        return subscriptionRepository.deleteSubscription(id);
    }
}