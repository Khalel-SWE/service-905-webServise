<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Sign Up</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css">
    <style>
        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
        <div class="login-form">
            <form class="sign-in-htm" action="AuthController" method="POST">
                <input type="hidden" name="action" value="login">
                
                <div class="group">
                    <label for="username" class="label">Username</label>
                    <input id="username" name="username" type="text" class="input" required>
                </div>
                <div class="group">
                    <label for="password" class="label">Password</label>
                    <input id="password" name="password" type="password" class="input" data-type="password" required>
                </div>
                <div class="group">
                    <input id="check" type="checkbox" class="check" checked>
                    <label for="check"><span class="icon"></span> Keep me Signed in</label>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign In">
                </div>
                <div class="hr"></div>
                
                <% if (request.getParameter("error") != null) { %>
                    <div class="error-message">Invalid username or password</div>
                <% } %>
            </form>
            <form class="sign-up-htm" action="AuthController" method="POST">
                <input type="hidden" name="action" value="signup">
                
                <div class="group">
                    <label for="signup-username" class="label">Username</label>
                    <input id="signup-username" name="username" type="text" class="input" required>
                </div>
                <div class="group">
                    <label for="signup-password" class="label">Password</label>
                    <input id="signup-password" name="password" type="password" class="input" data-type="password" required>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign Up">
                </div>
                <div class="hr"></div>
                
                <% if (request.getParameter("signupError") != null) { %>
                    <div class="error-message"><%= request.getParameter("signupError") %></div>
                <% } %>
            </form>
        </div>
    </div>
</div>
</body>
</html>