<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <#include "css/bootstrap.min.css">
    <#include "css/style.css">
</head>

<body class="text-center">
<form class="form-signin" method="post">

    <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h3> ${info} </h3>
    <a class="btn btn-lg btn-primary btn-block" href="/login">Sign in</a>
    <a class="btn btn-lg btn-success btn-block" href="/registration">Sign up</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>
</form>
</body>
</html>