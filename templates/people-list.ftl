<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <title>Liked list</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <div class="row" style="padding:20px 0">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">Liked users</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img alt="no img" class="img-circle"
                                                 src="${user.photoUrl}"/>  
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        ${user.name} ${user.surname}
                                    </td>
                                    <td class="align-middle">
                                        Last login
                                        ${user.lastLogin}
                                    </td>
                                    <td class="align-middle">
                                        <form action="/chat" method="get">
                                            <input type="hidden" name="receiverUser" value="${user.id}">
                                            <button name="send" type="submit"> Send message</button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div style="display: flex; justify-content: space-between; margin-top: 20px;">
                <a href="/users" role="button" class="btn btn-primary" style="display: block;width:130px;">Find love</a>
                <a href="/login" role="button" class="btn btn-danger" style="display: block;width:130px;">Log out</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>