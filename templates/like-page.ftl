<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body style="background-color: #f5f5f5;">
<div class="col-4 offset-4">
    <form method="post">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <#if user?exists>
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <#--                        <#if !users?exists>-->
                        <#--                            salaam-->
                        <#--                        </#if>-->

                        <img src="${user.photoUrl}"
                             class="mx-auto rounded-circle img-fluid">
                        <h3 class="mb-0 text-truncated">${user.name}  ${user.surname}
                        </h3>
                        <br>
                    </div>
                    <input type="hidden" name="userId" value=${user.id}>
                    <div class="col-12 col-lg-6">
                        <button name="dislike" type="submit" class="btn btn-outline-danger btn-block"><span
                                    class="fa fa-times"></span>
                            Dislike
                        </button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button name="like" type="submit" class="btn btn-outline-success btn-block"><span
                                    class="fa fa-heart"></span>
                            Like
                        </button>
                    </div>
                    <!--/col-->

                </div>
                <#else >nuull</#if>
                <!--/row-->
            </div>
            <!--/card-block-->
        </div>
    </form>

</div>
<form method="get">
    <div>
        <button class="btn btn-danger btn-lg" formaction="/login">
            <span class="glyphicon glyphicon-log-out"></span> Log out
        </button>
        <button class="btn btn-primary btn-lg" formaction="/liked">
            <span class="glyphicon glyphicon-log-out"></span> Liked
        </button>
    </div>
</form>

</body>
</html>