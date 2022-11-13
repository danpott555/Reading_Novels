<%-- 
    Document   : Novel
    Created on : Jul 6, 2022, 1:59:21 PM
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

        <title>${myConnect.n.name} [Tới Chap ${myConnect.lc.chapter_number}]</title>
    </head>
    <body>
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
        <div class="novel-cover">
            <div class="novel-title">
                <h2>${myConnect.n.name}</h2>
                <h4>[Cập nhật vào: ${myConnect.lc.date}]</h4>
            </div>
            <div class="detail-info">
                <div class="row">
                    <div class="col-xs-4">
                        <img class="img-responsive" src="<c:url value="${myConnect.n.image}"/>" />
                    </div>
                    <div class="col-xs-8">
                        <ul class="list-info">
                            <li class="row">
                                <p class="col-xs-4"><i class="fa fa-user"></i>Tác giả</p>
                                <p class="col-xs-8">${myConnect.n.author}</p>
                            </li>
                            <li class="row">
                                <p class="col-xs-4"><i class="fa fa-tags"></i>Thể loại</p>
                                <p class="col-xs-8">
                                    <c:forEach items="${myConnect.novelGenres}" var="ng">
                                        <c:if test="${myConnect.n.id==ng.novel_id}">
                                            <a href="genre?type=${ng.genre_id}" class="tag thumbnail-tag">${ng.genre_name}</a>
                                        </c:if>
                                    </c:forEach>
                                </p>
                            </li>
                            <li class="row">
                                <p class="col-xs-4"><i class="fa fa-eye"></i>Lượt theo dõi</p>
                                <p class="col-xs-8">${myConnect.n.follow}</p>
                            </li>
                        </ul>
                        <div class="follow">
                            <form action="novel?truyen=${myConnect.n.id}" method="post">
                                <c:if test="${myConnect.isFollow == true}">
                                    <button type="submit" name="fl" value="0" class="btn btn-danger"><span style="font-weight: bold"> Bỏ Theo dõi</span></button>
                                </c:if>
                                <c:if test="${myConnect.isFollow != true}">
                                    <button type="submit" name="fl" value="1" class="btn btn-success"><i class="fa fa-heart"></i><span style="font-weight: bold"> Theo dõi</span></button>
                                </c:if>
                            </form>
                        </div>
                        <div class="read-action">
                            <a class="btn btn-warning" href="chapter?truyen=${myConnect.n.id}&chapter=1">Đọc từ đầu</a>
                            <a class="btn btn-warning" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.lc.chapter_number}">Đọc mói nhất</a>
                        </div>

                    </div>
                </div>
                <div class="novel-intro">
                    <h3><i class="fa fa-file-text-o"> NỘI DUNG</i></h3>
                    <p>${myConnect.n.infor}</p>
                </div>
                <div class="chapter-list">
                    <h3><i class="fa fa-list"> DANH SÁCH CHƯƠNG</i></h3>
                    <div class="list">
                        <div class="row heading">
                            <div class="col-xs-8">Số chương</div>
                            <div class="col-xs-4">Cập nhật</div>
                        </div>
                        <nav>
                            <ul>
                                <c:forEach items="${myConnect.chapterList}" var="c">
                                    <li class="row">
                                        <div class="col-xs-8">
                                            <a href="chapter?truyen=${myConnect.n.id}&chapter=${c.chapter_number}">Chapter ${c.chapter_number}</a>
                                        </div>
                                        <div class="col-xs-4">${c.date}</div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
