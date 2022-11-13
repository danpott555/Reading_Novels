<%-- 
    Document   : Chapter
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

        <title>${myConnect.n.name} - Chapter ${myConnect.c.chapter_number}</title>
    </head>
    <body style="background-color: black">
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
        <div style="padding-top: 10px">
            <div class="container">
                <div class="row rollback">
                    <span style="color: black; font-size: 18px;">Thể loại </span><i style="font-size: 10px; color: gainsboro" class="fa fa-chevron-right"></i>
                    <a style="color: #288ad6; font-size: 18px;" href="novel?truyen=${myConnect.n.id}">${myConnect.n.name} </a><i style="font-size: 10px; color: gainsboro" class="fa fa-chevron-right"></i>
                    <a style="color: #288ad6; font-size: 18px;" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number}">Chapter ${myConnect.c.chapter_number} </a>
                </div>
                <div class="row heading-row">
                    <h2>Chapter ${myConnect.c.chapter_number}: ${myConnect.c.chapter_title}</h2>
                </div>
                <div class="chapter-action text-center">
                    <c:if test="${myConnect.c.chapter_number-1 > 0}">
                        <a style="width: 33px" class="prev-action" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number-1}"><i class="fa fa-chevron-left"></i></a>
                        </c:if>
                    <select style="height: 34px; width: 30%;">
                        <option value="${myConnect.c.chapter_number}" selected>Chapter ${myConnect.c.chapter_number}</option>
                        <c:forEach items="${myConnect.chapterList}" var="cl">
                            <c:if test="${myConnect.c.chapter_number != cl.chapter_number}">
                                <option value="${cl.chapter_number}">Chapter ${cl.chapter_number}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <c:if test="${myConnect.c.chapter_number < myConnect.lc.chapter_number}">
                        <a style="width: 33px" class="next-action" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number+1}"><i class="fa fa-chevron-right"></i></a>
                        </c:if>
                </div>
                <div class="content">
                    <p>${myConnect.c.content}</p>
                </div>

                <div class="chapter-action text-center">
                    <c:if test="${myConnect.c.chapter_number-1 > 0}">
                        <a class="prev-action" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number-1}"><i class="fa fa-chevron-left"></i> Chap trước</a>
                    </c:if>
                    <c:if test="${myConnect.c.chapter_number < myConnect.lc.chapter_number}">
                        <a class="next-action" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number+1}">Chap sau <i class="fa fa-chevron-right"></i></a>
                        </c:if>
                </div>
                <div style="height: 30px"></div>

                <div class="row rollback">
                    <span style="color: black; font-size: 18px;">Thể loại </span><i style="font-size: 10px; color: gainsboro" class="fa fa-chevron-right"></i>
                    <a style="color: #288ad6; font-size: 18px;" href="novel?truyen=${myConnect.n.id}">${myConnect.n.name} </a><i style="font-size: 10px; color: gainsboro" class="fa fa-chevron-right"></i>
                    <a style="color: #288ad6; font-size: 18px;" href="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number}">Chapter ${myConnect.c.chapter_number} </a>
                </div>
                <div style="height: 30px"></div>
                <div class="comment">
                    <h1 class="postname">Bình luận</h1>
                    <c:if test="${user!=null}">
                        <form action="chapter?truyen=${myConnect.n.id}&chapter=${myConnect.c.chapter_number}" method="post">
                            <div class="row">
                                <div class="col-xs-1">
                                    <img style="height: 50px; width: 50px; border-radius: 50%;" src="<c:url value="${user.image}" />"/>
                                </div>
                                <div class="col-xs-8">
                                    <textarea style="padding: 5px 5px 5px 5px; width: 100%; height: 80px;" type="text" name="comment" required></textarea>
                                    <button style="margin-top: 10px; float: right" type="submit" class="btn btn-success"><span style="font-weight: bold">Gửi</span></button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <div style="height: 20px"></div>
                    <div class="comment-list">
                        <c:forEach items="${myConnect.commentList}" var="c">
                            <div class="row">
                                <div class="col-xs-1">
                                    <img style="height: 50px; width: 50px; border-radius: 50%;" src="<c:url value="${c.image}" />"/>
                                </div>
                                <div class="col-xs-8">
                                    <div class="comment-detail">
                                        <div class="comment-header">
                                            <span class="authorname">${c.name}</span>
                                            <span class="member">Cấp ${c.level}
                                                <span class="progress-bar" style="width: 40%"></span>
                                            </span>
                                        </div>
                                        <div class="comment-content">${c.comment}</div>
                                    </div>
                                </div>
                            </div>
                                    <div style="height: 20px"></div>
                        </c:forEach>
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
