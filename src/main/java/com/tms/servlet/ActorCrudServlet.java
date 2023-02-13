package com.tms.servlet;


import com.tms.domain.Actor;
import com.tms.service.ActorCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/actor")
public class ActorCrudServlet extends HttpServlet {

    ActorCrudService actorCrudService = new ActorCrudService();
    private static final Logger log = LoggerFactory.getLogger(ActorCrudServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doing /actor Get method!");
        int requestActorId = Integer.parseInt(req.getParameter("id"));
        Actor actor = actorCrudService.getActorById(requestActorId);
        if (actor.getId() == 0) {
            log.warn("User is not found! Trying find id=" + requestActorId);
        }
        req.setAttribute("actor", actor);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleActor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doing /actor Post method!");
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
        log.info("doing /actor Put method!");
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String biography = req.getParameter("biography");
        boolean result = actorCrudService.updateActor(id, firstName, lastName, age, biography);
        if (!result) {
            log.warn("/actor Put result in false");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        log.info("doing /actor Delete method!");
        int id = Integer.parseInt(req.getParameter("id"));
        actorCrudService.deleteActor(id);
    }

}
