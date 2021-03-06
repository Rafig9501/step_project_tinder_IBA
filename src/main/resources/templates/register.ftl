<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">
    <title>Register</title>
    <#include "css/bootstrap.min.css">
    <#include "css/style.css">
</head>

<body class="text-center">
<form class="form-signin" method="post">
    <#--    <img class="mb-4" src="images/logo.png">-->
    <img src="https://britishacademy.az/uploads/1516218667.png" alt="Flowers in Chania" width="200" height="200">
    <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
    <input class="form-control" name="name" placeholder="Name" required>
    <input class="form-control" name="surname" placeholder="Surname" required>
    <input class="form-control" name="email" type="email" placeholder="email" required>
    <input class="form-control" name="photoUrl" placeholder="photo url" required>
    <input class="form-control" type="password" name="password" id="password"
           placeholder="Password" required>
    <input class="form-control" type="password" name="repeatPassword" id="repeatPassword"
           placeholder="Password (repeat)" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit" formmethod="post" onclick="passcheck()">Register
    </button>
    <a class="btn btn-lg btn-primary btn-block" href="/login">Sign in</a>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020 </p>
</form>
</body>
<script src="js">
</script>
</html>