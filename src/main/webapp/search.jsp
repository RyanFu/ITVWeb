<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="keywords" content="itv9,itv9视频,itv9影视,大片,最新电影,最新大片,免费电影,高清电影,更新最快的电影,在线视频,在线电影,伦理片,在线高清电影">
    <meta name="description" content="itv9--最新、最全的视频网站-在线观看">
    <title>搜索结果--itv9电影频道</title>
    <link rel="shortcut icon" href="<%=basePath%>bootstrap/img/itv9.ico" />
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/public.css" rel="stylesheet">
    <link href="bootstrap/css/index.css" rel="stylesheet">
    <link href="bootstrap/css/search.css" rel="stylesheet">
    <link href="<%=basePath%>bootstrap/css/copyright.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/jquery.lazyload.min.js"></script>
</head>
<body>
<div id="full">
    <div id="top">
        <div id="ctop">
            <div id="top_left1">
                <div class="trademark">
                    <p class="t_p1"><strong>iTV&nbsp;</strong><span class="t_s">影视</span></p>

                    <p class="t_p2">中国更新最快的电影网站</p>
                </div>
            </div>
            <div id="search">
                <div class="input-append">
                    <form action="searchAction_search.htm" method="post">
                        <input class="span2" name="name" id="search-text" type="text" autocomplete="off">
                        <button class="btn" type="submit">搜索</button>
                    </form>
                </div>
            </div>
            <jsp:include page="ajaxSearch.html"></jsp:include>
        </div>
        <div id="navbar">
            <div id="navbar-inner">
                <ul id="nav">
                    <li class="nav-item">
                        <a target="_self" href="#">
                            <span class="bg-right">首页</span>
                        </a>
                    </li>
                    <li class="nav-item dianying-on">
                        <a class="dianying-nav on" target="_self" href="<%=basePath%>indexAction_index.htm">
                            <span class="bg-right">电影</span>
                        </a>
                    </li>
                    <li class="nav-item dianying-on">
                        <a class="dianying-nav" target="_self" href="#">
                            <span class="bg-right">电视剧</span>
                        </a>
                    </li>
                    <li class="nav-item dianying-on">
                        <a class="dianying-nav" target="_self" href="#">
                            <span class="bg-right">综艺</span>
                        </a>
                    </li>
                    <li class="nav-item dianying-on">
                        <a class="dianying-nav" target="_self" href="#">
                            <span class="bg-right">电视直播</span>
                        </a>
                    </li>
                </ul>
                <div id="subnav">

                </div>
                <div class="navbar-bg-left"></div>
                <div class="navbar-bg-right"></div>
            </div>
        </div>
    </div>
    <hr>
    <div class="search-info">
        <div class="search-one">
            <ul class="search-movie-info">
                <s:iterator value="list" var="li">
                    <li>
                        <div class="avatar">
                            <a class="gimginfo" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${li.id}.html">
                                <img src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${li.imgUrl}" class="lazy" alt="${li.name}" title="${li.name}">
                                <span class="mask"></span>
                            <span class="info">
                                <em>标清</em>
                                <ins>${li.duration}</ins>
                            </span>
                            </a>
                        </div>
                        <div class="movie-data">
                            <div class="data-text">
                                <h3>
                                    <a target="_blank" href="http://v.360.cn/m/hKTlY0T6SHnATh.html">
                                        <b>${li.name}</b>
                                    </a>
                                    <span class="category">[电影]</span>
                                </h3>

                                <div class="item">
                                    <p>
                                        <strong>演员：</strong>
                                        <span>${li.actor}</span>
                                    </p>
                                </div>
                                <div class="item">
                                    <p>
                                        <strong>导演：</strong>
                                        <span>${li.director}</span>
                                    </p>
                                </div>
                                <div class="item">
                                    <p class="area">
                                        <strong>地区：</strong>
                                        <span>${li.area}</span>
                                    </p>

                                    <p class="area">
                                        <strong>年代：</strong>
                                        <span>${li.year}</span>
                                    </p>

                                    <p>
                                        <strong>类型：</strong>
                                        <span>动作/科幻</span>
                                    </p>
                                </div>
                                <div class="context">
                                    <strong>简介：</strong>
                                    ${li.less}
                                </div>
                            </div>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
        <hr>
    </div>
    <!--
    <div class="movie-list">
        <div class="list-db">
            <ul class="db-ul">
                <li>
                    <a class="gimginfo" target="_blank" title="钢铁侠3BD"
                       href="http://www.kubohd.com/vod/45462-0-1.html" sitename="qvod">
                        <img alt="" src="http://p6.qhimg.com/t01dffd3cabfde000bb.jpg">
                        <span class="mask"></span>
                        <span class="info">
                            <em>其他</em>
                            <ins>2:05:03</ins>
                        </span>
                    </a>
                    <a class="title" target="_blank" title="" href="http://www.kubohd.com/vod/45462-0-1.html">
                        <b>钢铁侠3</b>
                    </a>
                </li>
            </ul>
        </div>
    </div>-->
</div>
<div class="copyright">
    <div class="cp_info">
        <ul class="cp_ul">
            <li class="cp_ul_li"><a href="#">意见反馈</a></li>
            <li><a href="#">加入搜藏</a></li>
            <li><a href="#">版权声明</a></li>
            <li><a href="#">关于我们</a></li>
            <li><a href="#">联系方式</a></li>
        </ul>
        <p class="cp_info_p">
            Copyright © ITV9. All Rights Reserved.&nbsp;&nbsp;&nbsp;
            <a href="http://www.miibeian.gov.cn">蜀ICP备09012292号-2</a>
        </p>
        <jsp:include page="share.html"></jsp:include>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("img.lazy").lazyload({
            threshold: 100
        });
    });
</script>
</body>
</html>