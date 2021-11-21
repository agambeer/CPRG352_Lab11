<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Notes - Reset Password</title>
    </head>
    <body>
        <h1>Notes App</h1>
        <h2>Reset Password</h2>
        <form method="post" action="reset">
            <p>Please enter your email address to reset your password</p>
            <label>Email Address:</label>
            <input type="email" name="reset_email" value="${reset_email}"><br>
            <input type="submit" value="Submit">
        </form>
        <p>${message}</p>
    </body>
</html>
