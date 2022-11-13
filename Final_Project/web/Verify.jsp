<%-- 
    Document   : Verify
    Created on : Jul 3, 2022, 10:22:06 PM
    Author     : Smily
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        <link href="<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />" rel="stylesheet">
        <title>Xác nhận</title>
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
            <div class="verify-form">
                <h2>Chúng tôi đã gửi mã xác nhận tới hòm thư của bạn!</h2>
                <h2>Hãy kiểm tra hòm thư của bạn!</h2>

                <form action="verifycode" method="post">
                    <br/>
                    <div>
                        Mã xác nhận:<input oninput="checkCode(this.value)" class="input-code" type="text" name="code" required>
                    </div>
                    <div id="password-danger" style="color: red; display: none;"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>Mã xác nhận không đúng</div>
                    <button id="submit-btn" class="ok btn-disabled" type="submit">Xác nhận</button>
                    <input id="authcode" type="text" name="authcode" value="${authcode}" hidden>
                    <input type="text" name="user" value="${user}" hidden>
                    <input type="text" name="name" value="${name}" hidden>
                    <input type="text" name="password" value="${password}" hidden>
                    <input type="text" name="password_2" value="${password_2}" hidden>
                    <input type="text" name="ques_id" value="${ques_id}" hidden>
                    <input type="text" name="ans" value="${ans}" hidden>
                    <input type="text" name="email" value="${email}" hidden>
                </form>
            </div>
        </div>
    </body>
</html>
