<%-- 
    Document   : ForgetPassword
    Created on : Jul 12, 2022, 6:49:10 AM
    Author     : Smily
--%>

<%@page import="java.util.Map"%>
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
          
    <title>Quên Mật Khẩu</title>
  </head>
  <body>
      <%
          if(request.getAttribute("myConnect") != null){
          MyConnect myConnect = (MyConnect)request.getAttribute("myConnect");
      %>
    <header>
      <div class="heading-bar">
        <a href="welcome"
          ><h1>Kho<span style="color: #f18121">truyện</span></h1></a
        >
      </div>
    </header>
    <div class="app">
        <div class="register-form">
            <form action="forget" method="post">
                Tên đăng nhập <br><input type="text" name="user" required><br/>
                <p style="font-size: 12px; color: gray">(*)<span> Tên đăng nhập chỉ gồm chữ cái và chữ số</span></p>
                Email <br><input type="text" name="email" required><br/>
                Mật khẩu cấp 2 <br><input type="password" name="password_2" required><br/>
                Câu hỏi bảo mật <br><select name="ques_id">
                    <%
                    for (Map.Entry<Integer, QuestionList> en : myConnect.getQuesMap().entrySet()) {
                            int key = en.getKey();
                            QuestionList value = en.getValue();%>
                            <option value='<%=key%>' <%=((key == 1)?"selected":"")%>><%=value.getName()%></option>
                <%}%>
                </select><br/>
                Câu trả lời <br><input type="text" name="ans" required><br/>
                <input class="ok" type="submit" value="Xác nhận"><br/>
            </form>
        </div>
    </div>
    <footer>
        <div style="margin-top: 17.8%" class="footer-container">
        <p>Copyright © 2022 KhoTruyen - Kho Đọc Truyện Tranh Hay Nhất.</p>
        <div class="more-link">
          <a href="http://www.nettruyenco.com/">Xuyên không</a>
          <a href="http://www.nettruyenco.com/">Chuyển sinh</a>
          <a href="http://www.nettruyenco.com/">Ngôn tình</a>
          <a href="http://www.nettruyenco.com/">Đam Mỹ</a>
          <a href="http://www.nettruyenco.com/">Manhwa</a>
          <a href="http://www.nettruyenco.com/">Manhua</a>
        </div>
      </div>
    </footer>
        <%}else{%>
            <h1>Error</h1>
        <%}%>
  </body>
</html>
