<%-- 
    Document   : AdminNewChapter
    Created on : Jul 19, 2022, 2:23:24 AM
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

        <title>Cập nhật Chapter</title>
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
        <div class="container" style="background-color: white;">
            <div class="upload-novel">
                <div class="col-sm-1">
                    
                </div>
                <div class="new-novel col-sm-10">
                    <form action="adminnewchapter" method="post">
                        <div class="form-group">
                            <label for="name-novel" class="control-label">Tên truyện</label>
                            <input name="name" type="text" id="name-novel" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="nextchapter-title" class="control-label">Tên chapter tiếp theo</label>
                            <input name="chapter_title" type="text" id="nextchapter-title" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="nextchapter-content" class="control-label">Nội dung</label>
                            <textarea name="content" id="nextchapter-content" class="form-control" style="height: 400px" required></textarea>
                        </div>
                        <input style="width: 100px" type="submit" value="Xác nhận">    
                    </form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
