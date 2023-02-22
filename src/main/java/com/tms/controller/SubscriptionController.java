package com.tms.controller;

import com.tms.domain.Subscription;
import com.tms.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    public SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    public String getSubscription(@PathVariable int id, Model model) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        model.addAttribute("subscription", subscription);
        return "singleSubscription";
    }

    @PostMapping("/{id}")
    public String createSubscription(@PathVariable int id) {
        if (subscriptionService.createSubscription(id)) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @PostMapping("/addSubscription/{userId}")
    public String addSubscriptionToUser(@PathVariable int userId) {
        if (subscriptionService.add(userId)) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @PutMapping
    public String updateSubscription(@RequestParam int id, @RequestParam long expireDate) {
        boolean result = subscriptionService.updateSubscription(id, new Date(expireDate));
        return result ? "successfully" : "unsuccessfully";
    }

    @DeleteMapping("/{id}")
    public String deleteSubscription(@PathVariable int id) {
        boolean result = subscriptionService.deleteSubscription(id);
        return result ? "successfully" : "unsuccessfully";
    }
}