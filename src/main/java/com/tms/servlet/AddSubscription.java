package com.tms.servlet;

import com.tms.service.AddSubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addSubscription")
public class AddSubscription extends HttpServlet {
    AddSubscriptionService addSubscriptionService = new AddSubscriptionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addSubscriptionService.add(Integer.parseInt(req.getParameter("userId")));
    }
}
