package com.tms.servlet;

import com.tms.domain.Movie;
import com.tms.service.MovieCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/movie")
public class MovieCrudServlet extends HttpServlet {
/*

    MovieCrudService movieCrudService = new MovieCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestActorId = Integer.parseInt(req.getParameter("id"));
        Movie movie = movieCrudService.getMovieById(requestActorId);
        req.setAttribute("movie", movie);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movieName");
        int year = Integer.parseInt(req.getParameter("year"));
        String genre = req.getParameter("genre");
        Double rating = Double.parseDouble(req.getParameter("rating"));
        String description = req.getParameter("description");

        boolean result = movieCrudService.createMovie(movieName, year, genre, rating,description);
        if (result) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/successfully.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/unsuccessfully.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String movieName = req.getParameter("movieName");
        int year = Integer.parseInt(req.getParameter("year"));
        String genre = req.getParameter("genre");
        Double rating = Double.parseDouble(req.getParameter("rating"));
        String description = req.getParameter("description");
       movieCrudService.updateMovie(id, movieName, year, genre, rating,description);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        movieCrudService.deleteMovie(id);
    }
*/
}
