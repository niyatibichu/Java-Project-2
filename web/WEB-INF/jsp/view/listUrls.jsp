<%@ page import="java.util.Map,com.project1.UserUrl" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   
    Map<String, UserUrl> shortnerDatabase =(Map<String, UserUrl>)request.getAttribute("shortnerDatabase");
String username=(String)request.getSession().getAttribute("username");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>URL Shortener</title>
    </head>
    <body>
        <a href="<c:url value="/login?logout" />">Logout</a>
        <h2>URLS</h2>
        <a href="<c:url value="/shortner">
            <c:param name="action" value="create" />
        </c:url>">Create Short Url</a><br /><br />
        
        
        <c:forEach var="db" items="${shortnerDatabase}">
        	<c:set var="key" value="${db.key}" />
        	<c:set var="userurl" value ="${db.value }" />
        	<c:set var="long" value ="longUrl" />
        	<c:set var="clicks" value ="clicks" />
        	<c:set var="uname" value="${username}" />
   			<c:if test="${userurl.userName==uname}">
       			<b>Short url :</b>${key}
	        	<br></br>
	        	<b>Long url :</b>${userurl.longUrl} 
	        	<br></br>
	        	<b>Clicks :</b>${userurl.clicks}
	        	<br></br>
	        	
			</c:if>	
       	</c:forEach>
</body>
</html>
