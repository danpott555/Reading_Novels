<%-- 
    Document   : ChangePassword
    Created on : Jul 11, 2022, 3:56:44 PM
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
        <link href="<c:url value="/css/novel.css" />" rel="stylesheet">
        <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />" rel="stylesheet">

        <title>Thông tin chung</title>
    </head>
    <body style="background-color: #999">
        <header>
            <div class="heading-bar">
                <a href="welcome"
                   ><h1><span style="color: black">Kho</span><span style="color: #f18121">truyện</span></h1></a
                >
                <form action="search" class="search-box">
                    <input name="search_input" type="text" placeholder="Nhập tên truyện cần tìm" required />
                    <button
                        type="submit"
                        class="search-icon"
                        ><i class="fa fa-search"></i>
                    </button>
                </form>
                <div class="feature-cat">
                    <c:if test="${user!=null}">
                        <div class="dropdown">
                            <img style="height: 50px; width: 50px; border-radius: 50%; border: #a600ffbf solid 1px;" src="<c:url value="${user.image}" />"/>
                            <i style="transform: translateY(120%)" class="fa fa-caret-down"></i>
                            <div style="padding: 0;width: 300%; border: black solid 1px; border-radius: 5px" class="droplist droplist-fluid-container">
                                <div class="droplist-fluidz">
                                    <a href="usersetting"><i class="fa fa-user"></i>Trang cá nhân</a>
                                    <a href="changepassword"><i class="fa fa-key"></i>Đổi mật khẩu</a>
                                    <a href="follow"><i class="fa fa-book"></i>Truyện theo dõi</a>
                                </div>

                            </div>
                        </div>
                    </c:if>

                    <c:if test="${user==null}">
                        <a href="login">Đăng Nhập</a>
                        <a href="register">Đăng Ký</a>
                    </c:if>    
                </div>

            </div>
            <menu>
                <ul class="menu-ul">
                    <li class="menu-li"><a href="welcome">Trang chủ</a></li>
                    <li class="menu-li dropdown">
                        <a href="#">Thể loại &#8595;</a>
                        <div class="droplist droplist-fluid-container">
                            <div class="droplist-fluid">
                                <c:forEach items="${myConnect.genres}" var="g">
                                    <a href="genre?type=${g.id}">${g.name}</a>
                                </c:forEach>
                            </div>
                        </div>
                    </li>
                    <!--          <li class="menu-li dropdown">
                                <a href="#">Sắp xếp &#8595;</a>
                                <div class="droplist droplist-normal">
                                  <a href="#">Lorem, ipsum dolor</a>
                                  <a href="#">Lorem, ipsum</a>
                                  <a href="#">Lorem</a>
                                  <a href="#">Lorem, ipsum dolor</a>
                                </div>
                              </li>-->
                    <c:if test="${user==null}">
                        <li class="menu-li"><a href="login">Lịch sử</a></li>
                    </c:if>
                    <c:if test="${user!=null}">
                        <li class="menu-li"><a href="history">Lịch sử</a></li>
                    </c:if>
                    <c:if test="${user.user == 'admin'}">
                        <li class="menu-li"><a href="adminnewnovel">Đăng truyện mới</a></li>
                        <li class="menu-li"><a href="adminnewchapter">Cập nhật Chapter mới</a></li>
                    </c:if>
                </ul>
            </menu>
        </header>
        <div style="background-color: white" class="container">
            <div class="row user-page">
                <h1 class="postname">Đổi mật khẩu</h1>
                <div class="col-md-3">
                    
                </div>
                <div class="row col-xs-6">
                    <div class="account-info">
                        <div class="account-form">
                            <form action="changepassword" method="post">
                                <div class="row">
                                    <div class="form-group">
                                        <label for="old_password" class="control-label">Mật khẩu cũ</label>
                                        <input name="old_password" type="password" id="old_password" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="new_password" class="control-label">Mật khẩu mới</label>
                                        <input name="new_password" type="password" id="new_password" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="re_password" class="control-label">Xác nhận mật khẩu mới</label>
                                        <input name="re_password" type="password" id="re_password" class="form-control">
                                    </div>
                                    <div class="button-wrap">
                                        <input type="submit" name="update" value="Cập nhật" class="btn btn-primary">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div style="height: 150px;">
                
            </div>
        </div>
        <footer>
            <div class="footer-container">
                <p>Copyright © 2022 KhoTruyen - Kho Đọc Truyện Tranh Hay Nhất.</p>
                <div class="more-link">
                    <a href="#">Xuyên không</a>
                    <a href="#">Chuyển sinh</a>
                    <a href="#">Ngôn tình</a>
                    <a href="#">Đam Mỹ</a>
                    <a href="#">Manhwa</a>
                    <a href="#">Manhua</a>
                </div>
            </div>
        </footer>
    </body>
</html>
