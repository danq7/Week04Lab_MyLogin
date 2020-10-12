<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CPRG 352 Lab 4 </title>
    </head>
    <body>
        <h1>Login</h1>
        <form action ="login" method="post">
            <label>Username: </label><input type="text" name="user_input" value="${userName}"><br>
            <label>Password: </label><input type="password" name="password_input" value="${password}"><br>
            <input type="submit" value="Log in">
        </form>
        <p>${loginMsg}</p>
    </body>
</html>
