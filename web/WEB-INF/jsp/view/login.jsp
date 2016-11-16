<%@ page import="java.util.Map,com.project1.UserUrl" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	Object o = request.getAttribute("shortnerDatabase");
    if(o instanceof Map<?, ?>){
    	Map<String, UserUrl> shortnerDatabase = (Map<String, UserUrl>)o;
    }
	String short_url=(String)request.getParameter("public_short");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>URL Shortener</title>
    </head>
    <body>
        <h2>Login</h2>
        You must log in to access the URL Shortener site.<br /><br />
        <%
            if(((Boolean)request.getAttribute("loginFailed")))
            {
                %>
        <b>The username or password you entered are not correct. Please try
            again.</b><br /><br />
                <%
            }
        %>
        <form method="POST" action="<c:url value="/login" />">
            Username<br />
            <input type="text" name="username" /><br /><br />
            Password<br />
            <input type="password" name="password" /><br /><br />
            <input type="submit" name="input_case" value="login">     
        </form> 
        <form method="POST" action="<c:url value="/login" />">
       <input type="submit" name="input_case" value="signup">
       </form>
       <form method="POST" action="<c:url value="/login" />">
       ShortUrl:<br />
            <input type="text" name="ip_shorturl" /><br /><br />
       <input type="submit" name="input_case" value="public">
       </form>
          
            </body>
</html>
