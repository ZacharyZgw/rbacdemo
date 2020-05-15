<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/3.4.0/css/bootstrap.min.css">
    <title>登录页</title>
</head>
<body style="background-color: #f7f7f7">
<div class="modal-dialog" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">登录</h4>
        </div>
        <div class="modal-body" id = "model-body">
            <div class="form-group">

                <input type="text" class="form-control"placeholder="用户名" autocomplete="off" id = "un">
            </div>
            <div class="form-group">

                <input type="password" class="form-control" placeholder="密码" autocomplete="off" id ="pw">
            </div>
        </div>
        <div class="modal-footer">
            <div class="form-group">
                <button type="button" class="btn btn-primary form-control" onclick="login()">登录</button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script type="text/javascript" src="<%=basePath%>static/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function login(){
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "user/login",
            data: {
                userName: $("#un").val(),
                password: $("#pw").val()
            },
            success: function(data) {
                window.location.href = "user/toSelect";
            }
        });
    }
</script>
</body>
</html>

