<%@ page import="java.util.Map,com.project1.UserUrl" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	
    Map<String, UserUrl> shortnerDatabase =(Map<String, UserUrl>)request.getAttribute("shortnerDatabase");
String short_url=(String)request.getAttribute("public_short");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>URL Shortener</title>
    </head>
    <body>
        <h3>SHORT URL :=</h3>
       <%=short_url %>
       <h3>LONG URL:= </h3>
       <%=shortnerDatabase.get(short_url).getlongUrl()%>
      
    </body>
</html>
