<%@ page import="com.tms.domain.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Movie movieJsp = (Movie) request.getAttribute("movie");%>
<html>
<head>
    <title>Movie info</title>
</head>
<body>
<h1> Hello, this is your Movie!</h1>
<h3>Movie id:  <%=movieJsp.getId()%></h3>
<h3>Movie name:  <%=movieJsp.getMovieName()%></h3>
<h3>Movie genre:  <%=movieJsp.getGenre()%></h3>
<h3>Movie rating:  <%=movieJsp.getRating()%></h3>
<h3>Movie description:  <%=movieJsp.getDescription()%></h3>

</body>
</html>