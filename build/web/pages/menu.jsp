<%@page import="edu.pitt.resumecore.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp" %>

<div id="mainContainer" class="container theme-showcase">
    <div class="panel panel-default">


        <div class="panel-heading">
            <h3 class="panel-title"><strong>Menu</strong></h3>
        </div>

        <div class="panel-body">
            <ul>
                <%
                    if (user.getRoles().contains("student")) {
                        out.print("<li><a href='listResumes.jsp?userID=" + user.getUserID() + "'>Your Resumes</a></li>");
                        out.print("<li><a href='personalInfo.jsp'>Enter a new Resume</a></li>");
                    } else if (user.getRoles().contains("staff")) {
                        out.print("<li><a href='listResumes.jsp'>List all Resumes</a></li>");
                        out.print("<li><a href='searchResumes.jsp'>Search through Resumes</a></li>");
                        out.print("<li><a href='listUsers.jsp'>List Users</a></li>");
                        out.print("<li><a href='addUser.jsp'>Add User</a></li>");
                    } else if (user.getRoles().contains("employer")) {
                        out.print("<li><a href='listResumes.jsp'>View all Resumes</a></li>");
                        out.print("<li><a href='searchResumes.jsp'>Search through Resume</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>

    <%@ include file="../includes/footer.jsp" %>