<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <#include "css/bootstrap.min.css">
    <#include "css/style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">${receiver.name} ${receiver.surname}</h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">
                        <#list messageList as message>
                            <#if message.fromId == id >
                                <li class="send-msg float-right mb-2">
                                    <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                        ${message.content}
                                    </p>
                                    <span class="send-msg-time">${message.messageDateTime}</span>
                                </li>
                            <#--receive message-->
                            <#else>
                                <li class="receive-msg float-left mb-2">
                                    <div class="sender-img">
                                        <img src="${receiver.photoUrl}" alt="photo" class="float-left">
                                    </div>
                                    <div class="receive-msg-desc float-left ml-2">
                                        <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                            ${message.content}
                                        </p>
                                        <span class="receive-msg-time">${receiver.name}, ${message.messageDateTime}</span>
                                    </div>
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </div>
                <div style="position: relative" class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-1 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <#--text-->
                        <form method="post" class="col-md-11 pl-0">
                            <input name="text" style="width: 80%;" type="text" class="border-0"
                                   placeholder=" Send message"/>
                            <input name="receiverUserId" type="hidden" value="${receiver.id}">
                            <button id="send" style="cursor: pointer; position: absolute; right: 10px; top: 0; "
                                    type="submit">Send/Update
                            </button>
                        </form>
                    </div>
                </div>
                <p></p>
                <form method="get">
                    <div>
                        <button class="btn btn-danger btn-lg" formaction="/liked">
                            <span class="glyphicon glyphicon-log-out"></span> Liked
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>