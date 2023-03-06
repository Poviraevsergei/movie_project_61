<#import "macros/comm.ftl" as c>

<@c.comm "User info FTL">
    <h1> Hello, this is your User!</h1>
    <h3>User id:  <%=userJsp.getId()%></h3>
    <h3>User first name:  <%=userJsp.getFirstName()%></h3>
    <h3>User last name:  <%=userJsp.getLastName()%></h3>
    <h3>User birthdate:  <%=userJsp.getBirthdate()%></h3>
    <h3>User login:  <%=userJsp.getLogin()%></h3>
    <h3>User password:  <%=userJsp.getPassword()%></h3>
    <h3>User email:  <%=userJsp.getEmail()%></h3>
    <h3>User telephone:  <%=userJsp.getTelephoneNumber()%></h3>
</@c.comm>