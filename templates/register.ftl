<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">
    <title>Register</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">

</head>

<body class="text-center">
<form class="form-signin" method="post">
    <img class="mb-4" src="images/logo.png">
    <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
    <input class="form-control" name="name" placeholder="Name" required>
    <input class="form-control" name="surname" placeholder="Surname" required>
    <input class="form-control" name="email" type="email" placeholder="email" required>
    <input class="form-control"  name="photoUrl" placeholder="photo url" required>
    <input class="form-control"  type="password" name="password" id="password"
           placeholder="Password" required>
    <input class="form-control" type="password" name="repeatPassword" id="repeatPassword"
           placeholder="Password (repeat)" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit" formmethod="post" onclick="passcheck()">Register
    </button>
    <a class="btn btn-lg btn-primary btn-block" href="/login">Sign in</a>
    <p class="mt-5 mb-3 text-muted">&copy; Dan.IT 2018</p>
</form>
</body>
<script>
    function passcheck() {
        var password = document.getElementById("password").value;
        var password2 = document.getElementById("repeatPassword").value;
        if (password === password2) {
            alert("the password is correct");
        } else {
            alert("the password is not the same");
        }
    }
</script>
</html>