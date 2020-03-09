<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/2/22
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script>
        window.onload = function () {
            //1、获取图片对象
            var img =  document.getElementById("checkCode");
            //2、绑定单击事件
            img.onclick = function (){
                var date = new Date().getTime();
                img.src = "/cc/checkCodeServlet?"+date;
            }
        }
    </script>
    <style>
        div{
            color: red;
        }
    </style>
</head>
<body>
<form action="/cc/loginServlet" method="post">
    用户名<input type="text" placeholder="输入用户名" name="username"><br>
    密码<input type="password" placeholder="输入密码" name="password"><br>
    验证码<input type="text" placeholder="输入验证码" name="checkCode">
    <image id="checkCode" src="/cc/checkCodeServlet"></image><br>
    <input type="submit" value="登录">
</form>
<div>
    <%=request.getAttribute("cc_error") == null ?"": request.getAttribute("cc_error")%>
</div>
<div>
    <%=request.getAttribute("user_error") == null ? "":request.getAttribute("user_error")%>
</div>
</body>
</html>
