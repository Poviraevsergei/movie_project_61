package com.tms.servlet;

import com.tms.domain.Movie;
import com.tms.service.AddMovieToUserService;
import com.tms.service.GiveAllMoviesFromUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/getMovies")
public class GiveAllMoviesFromUser extends HttpServlet {
    GiveAllMoviesFromUserService giveAllMoviesFromUserService = new GiveAllMoviesFromUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        ArrayList<Movie> movieList = giveAllMoviesFromUserService.getMovies(userId);
        req.setAttribute("movieList", movieList.toString());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/allMovies.jsp").forward(req, resp);

    }
}
