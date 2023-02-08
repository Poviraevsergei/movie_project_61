package com.tms.servlet;

import com.tms.domain.User;
import com.tms.service.UserCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserCrudServlet extends HttpServlet {

    UserCrudService userCrudService = new UserCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestUserId = Integer.parseInt(req.getParameter("id"));
        User user = userCrudService.getUserById(requestUserId);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String telephoneNumber = req.getParameter("telephoneNumber");
        boolean result = userCrudService.createUser(firstName, lastName, login, password, email, telephoneNumber);
        if (result) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/successfully.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/unsuccessfully.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String telephoneNumber = req.getParameter("telephoneNumber");
        userCrudService.updateUser(id, firstName, lastName, login, password, email, telephoneNumber);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean result = userCrudService.deleteUser(id);
        System.out.println(result);
    }
}
