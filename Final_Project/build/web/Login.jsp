<%-- 
    Document   : Login
    Created on : Jun 21, 2022, 7:37:50 AM
    Author     : Smily
--%>

<%@page import="java.util.*" %>
<%@page import="DAL.MyConnect"%>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />" rel="stylesheet">
    
          

    <title>Đăng Nhập</title>
  </head>
  <body>
    <header>
      <div class="heading-bar">
        <a href="welcome"
          ><h1>Kho<span style="color: #f18121">truyện</span></h1></a
        >
      </div>
    </header>
      <div class="app">
        <div class="register-form">
            <form action="login" method="post">
                Tên đăng nhập <br><input type="text" name="user" required><br/>
                <div>Mật khẩu <br><input type="password" name="password" required><br/></div>
                <div>
                    <input class="ok" type="submit" value="Đăng nhập"><br/>
                    <a href="register" style="color: blue; text-decoration: underline">Đăng ký ngay!</a> / 
                    <a href="forget" style="color: blue; text-decoration: underline">Quên mật khẩu</a>
                </div>
            </form>
        </div>
      </div>
  </body>
</html>
  