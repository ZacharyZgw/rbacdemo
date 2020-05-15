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
    <title>Success</title>
</head>
<body>
<h3 style="text-align:center">权限列表</h3>
<div class="row">
    <div class="col-md-offset-2 col-md-8 col-md-offset-2">
        <table id="showList" class="table"></table>
    </div>
</div>
<input type="button" value="退出登陆" class="btn btn-danger center-block" onclick="loginout()"/>
<script type="text/javascript" src="<%=basePath%>static/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function loginout() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "user/logout",
            success: function (data) {
                window.location.href = "user/toLogin";
            }
        });
    }

    function show() {
        $.ajax({
            type: "GET",
            url: "user/list",
            dataType: "json",
            success: function (data) {
                var list = data.data;
                console.log(list);
                var ListHtml = "<thead><tr><th>username</th><th>role</th><th>permission</th></tr></thead><tbody>";
                $.each(list, function (i,n) {
                    ListHtml = ListHtml + "<tr><td>" + n.username + "</td><td>" + n.roleName+ "</td><td>" + n.url + "</td></tr>";
                });
                ListHtml=ListHtml+"</tbody>"
                $("#showList").html(ListHtml);
            }
        });
    }
    show();
</script>
</body>
</html>