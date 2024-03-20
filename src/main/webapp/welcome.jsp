<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<html>
<head>
    <title>Using JSP/POST Method to Read Form Data</title></head>
<body>
<h1>Using JSP/POST Method to Read Form Data</h1>
<ul>
    <li>
        <b>First Name:</b><%= request.getParameter("first_name")%>
    </li>
    <li>
        <b>Last Name:</b><%= request.getParameter("last_name")%>
    </li>
    <%
        String genderParam = request.getParameter("gender");
        if (genderParam != null) {

            String genderHtml = "male".equalsIgnoreCase(genderParam)
                    ? ("<li><b>Gender:</b> Male</li>")
                    : ("<li><b>Gender:</b> Female</li>");
            response.getWriter().println(genderHtml);
        }
    %>
    <li><b>Student: </b>
            <%= Optional.ofNullable(request.getParameter("student"))
                        .map(p -> "yes")
                        .orElse("no") %>
    <li><b>Employee: </b>
            <%= Optional.ofNullable(request.getParameter("employee"))
                        .map(p -> "yes")
                        .orElse("no") %>
    <li><b>Other: </b>
        <%= Optional.ofNullable(request.getParameter("other"))
                        .map(p -> "yes")
                        .orElse("no") %>
</ul>
</body>
</html>
</body>
</html>