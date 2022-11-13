<%-- 
    Document   : Sort
    Created on : Jul 12, 2022, 6:13:17 AM
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



        <title>Tìm truyện tranh online</title>
    </head>
    <body>
        <header>
            <div class="heading-bar">
                <a href="welcome"
                   ><h1>Kho<span style="color: #f18121">truyện</span></h1></a
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
        <input type="text" name="sort" value="${sort}" hidden>
        <section class="category" id="moi-cap-nhat">
            <a href="#"
               ><div class="fake-symbol" style="background-color: #ff2853"></div>
                <c:if test="${sort==2}">
                    <span class="cat-header">Sắp xếp theo lượt theo dõi</span>
                </c:if>
                <c:if test="${sort==1}">
                    <span class="cat-header">Sắp xếp theo ngày cập nhật</span>
                </c:if>
                </a
            >
            <div class="thumbnail-container">
                <c:if test="${sort==2}">
                    <c:forEach items="${myConnect.novelSortByFollow}" var="n">
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
                                        <p>Tác giả: ${n.author}</p>
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
                </c:if>
                <c:if test="${sort==1}">
                    <c:forEach items="${myConnect.novels}" var="n">
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
                </c:if>
            </div>
        </section>
    </body>
</html>
