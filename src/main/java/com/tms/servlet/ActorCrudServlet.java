package com.tms.servlet;

import com.tms.domain.Actor;
import com.tms.service.ActorCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/actor")
public class ActorCrudServlet extends HttpServlet {

    ActorCrudService actorCrudService = new ActorCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestActorId = Integer.parseInt(req.getParameter("id"));
        Actor actor = actorCrudService.getActorById(requestActorId);
        req.setAttribute("actor", actor);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleActor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String biography = req.getParameter("biography");

        boolean result = actorCrudService.createActor(firstName, lastName, age, biography);
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
        int age = Integer.parseInt(req.getParameter("age"));
        String biography = req.getParameter("biography");
        boolean result = actorCrudService.updateActor(id, firstName, lastName, age, biography);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        actorCrudService.deleteActor(id);
    }
}
