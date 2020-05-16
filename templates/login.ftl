
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
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center">
<!--<form class="form-signin" action="login" method="post">-->
    <!--<img class="mb-4" src="images/logo.png">-->
    <!--<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>-->
    <!--<input name="login" class="form-control" placeholder="Login (email)" required>-->
    <!--<input type="password" name="passwd" id="inputPassword" class="form-control" placeholder="Password" required>-->
    <!--<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>-->
    <!--<a class="btn btn-lg btn-primary btn-block" href="register.html">Register</a>-->
    <!--<p class="mt-5 mb-3 text-muted">&copy; DAN.IT 2018</p>-->
<!--</form>-->
<form class="form-signin" method="post">
    <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" name="login" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword"  class="sr-only">Password</label>
    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2018</p>
</form>
</body>
</html>