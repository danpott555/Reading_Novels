<%-- 
    Document   : History
    Created on : Jul 12, 2022, 2:49:21 AM
    Author     : Smily
--%>

<%@page import="java.util.*" %>
<%@page import="DAL.MyConnect"%>
<%@page import="Model.*"%>
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



        <title>Lịch sử</title>
    </head>
    <body>
        <header>
            <div class="heading-bar">
                <a href="welcome"
                   ><h1>Kho<span style="color: #f18121">truyện</span></h1></a
                >
                <form action="search" class="search-box">
                    <input type="text" placeholder="Nhập tên truyện cần tìm" required />
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
                            <i class="fa fa-caret-down"></i>
                            <div style="padding: 0; width: 12%; height: 120px; border: black solid 1px; border-radius: 5px" class="droplist droplist-fluid-container">
                                <div class="droplist-fluid">
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
        <section class="category" id="moi-cap-nhat">
            <div style="color: ">
                <i style="font-size: 24px" class="fa fa-book"></i>
                <span style="font-size: 24px">Lịch sử đọc truyện</span>
            </div>
            <div class="thumbnail-container">
                <c:forEach items="${myConnect.readList}" var="n">
                    <div class="thumbnail intro-pop-trigger">
                        <a href="novel?truyen=${n.id}"
                           ><div class="fake-img" style="background-color: #2c7abe">
                                <img style="width: 100%" src="<c:url value="${n.image}"/>"/>
                            </div
                            ></a>
                        <div class="thumbnail-text">
                            <a href="novel?truyen=${n.id}"><h3>${n.name}</h3></a>
                            <c:forEach items="${myConnect.lastestChapterList}" var="lcl">
                                <c:if test="${lcl.novel_id == n.id}">
                                    <a href="chapter?truyen=${n.id}&chapter=${lcl.chapter_number}">Chapter ${lcl.chapter_number}</a>
                                </c:if>
                            </c:forEach>
                        </div>
                        <div class="tag upload-time">
                            <p>${n.date}</p>
                        </div>
                        <div class="thumbnail-intro-box intro-pop">
                            <div class="arrow-container">
                                <div class="arrow"></div>
                            </div>
                            <div class="thumbnail-intro-container">
                                <div class="thumbnail-heading-text">
                                    <h2>${n.name}</h2>
                                    <p>Lượt theo dõi: ${n.follow}</p>
                                </div>
                                <div class="thumbnail-tag-container">
                                    <c:forEach items="${myConnect.novelGenres}" var="ng">
                                        <c:if test="${n.id==ng.novel_id}">
                                            <a href="genre?type=${ng.genre_id}" class="tag thumbnail-tag">${ng.genre_name}</a>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="thumbnail-intro-text">
                                    <p>
                                        ${n.infor}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="click-for-more">
                <a href="sort?sort=1">Xem thêm</a>
            </div>
        </section>
    </body>
</html>
