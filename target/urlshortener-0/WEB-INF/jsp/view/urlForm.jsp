<!DOCTYPE html>
<html>
    <head>
        <title>URL Shortener</title>
    </head>
    <body>
        <a href="<c:url value="/login?logout" />">Logout</a>
        <h2>Create a Short URL</h2>
        <form method="POST" action="shortner" >
            <input type="hidden" name="action" value="create"/>
            <h4>Enter your Long Url:</h4><br/>
        	<input type="text" name="input_url"><br/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
