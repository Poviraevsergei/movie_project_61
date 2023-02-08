<%@ page import="com.tms.domain.Actor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Actor actorJsp = (Actor) request.getAttribute("actor");%>
<html>
<head>
    <title>Actor info</title>
</head>
<body>
<h1> Hello, this is your User!</h1>
<h3>User id:  <%=actorJsp.getId()%></h3>
<h3>User first name:  <%=actorJsp.getFirstName()%></h3>
<h3>User last name:  <%=actorJsp.getLastName()%></h3>
<h3>User age:  <%=actorJsp.getAge()%></h3>
<h3>User biography:  <%=actorJsp.getBiography()%></h3>

</body>
</html>
