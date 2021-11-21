<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Notes - Set New Password</title>
    </head>
    <body>
        <h1>Notes App</h1>
        <h2>Enter a new password</h2>
        <form method="post" action="reset">
            <input type="password" name="reset_password" value="${reset_password}"><br>
            <input type="submit" value="Submit">
            <input type="hidden" name="uuid_set" value="${uuid_set}">
        </form>
        <p>${message}</p>
    </body>
</html>
