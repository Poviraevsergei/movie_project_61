package com.tms.servlet;

import com.tms.domain.Subscription;
import com.tms.service.SubscriptionCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/subscription")
public class SubscriptionCrudServlet extends HttpServlet {

    SubscriptionCrudService subscriptionCrudService = new SubscriptionCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestSId = Integer.parseInt(req.getParameter("id"));
        Subscription subscription = subscriptionCrudService.getSubscriptionById(requestSId);
        req.setAttribute("subscription", subscription);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleSubscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean result = subscriptionCrudService.createSubscription(Integer.parseInt(req.getParameter("userId")));
        if (result) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/successfully.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/unsuccessfully.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Date expireDate = new Date(Long.parseLong(req.getParameter("expire_date")));

        subscriptionCrudService.updateSubscription(id, expireDate);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        subscriptionCrudService.deleteSubscription(id);
    }
}
