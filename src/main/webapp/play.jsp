<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta content="${mb.name},电影,在线影视,大片,itv9影视" name="keywords">
    <meta content="电影《${mb.name}》在线观看" name="description">
    <title>${mb.name}-在线观看-ITV9影视</title>
    <link rel="shortcut icon" href="<%=basePath%>bootstrap/img/itv9.ico"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/play.css" rel="stylesheet">
    <link href="bootstrap/css/public.css" rel="stylesheet">
    <script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="top">
    <div class="top_center">
        <div class="top_title">
            <a href="indexAction_index.htm" title="itv9影视"><p class="t_p1"><strong>iTV&nbsp;</strong><span class="t_s">影视</span></p>
            </a>
        </div>
        <div class="top_bar">
            <ul>
                <li class="nav-item">
                    <a target="_self" href="#">首页</a>
                </li>
                <li class="nav-item">
                    <a target="_self" href="<%=basePath%>indexAction_index.htm">电影</a>
                </li>
                <li class="nav-item">
                    <a target="_self" href="#">电视剧</a>
                </li>
                <li class="nav-item">
                    <a target="_self" href="#">综艺</a>
                </li>
                <li class="nav-item">
                    <a target="_self" href="#">动漫</a>
                </li>
            </ul>
        </div>
        <div class="top_search">
            <form id="searchForm" action="searchAction_search.htm" method="post" onsubmit="return checkSeach();">
                <input id="search" class="search_text" type="text" name="name" autocomplete="off" qsuginited="1">
                <input class="search_submit" type="submit" value="搜索">
            </form>
        </div>
    </div>
</div>
<div class="info">
    <div itemscope itemtype="http://schema.org/Movie" class="main">
        <div class="overview">
            <div class="left_img">
                <img itemprop="image" id="film_poster" width="230" height="307" alt="${mb.name}" src="${mb.imgUrl}">
            </div>
            <div class="right_info">
                <div class="meta clearfix">
                    <h1 itemprop="name" id="film_name" class="title fn" data-movieid="65052">${mb.name}</h1>
                    <dl class="director" monitor-desc="导演">
                        <dt>导演：</dt>
                        <dd>
                            <a href="#">
                                <span itemprop="director" itemscope itemtype="http://schema.org/Person">
                            <span itemprop="name">${mb.director}</span></span>
                            </a>
                        </dd>
                    </dl>
                    <dl class="actor" monitor-desc="演员">
                        <dt>主演：</dt>
                        <dd>
                            <a href="#">
                                <span itemprop="actor" itemscope itemtype="http://schema.org/Person">
                                    <span itemprop="name">
                                        ${mb.actor}
                                    </span> </span>
                            </a>
                        </dd>
                    </dl>
                    <div class="text">
                        <dl class="area">
                            <dt>地区：</dt>
                            <dd>${mb.area}</dd>
                        </dl>
                        <dl class="year">
                            <dt>年代：</dt>
                            <dd itemprop="datePublished"
                                content="Please insert valid ISO 8601 date/time here. Examples: 2015-07-27 or 2015-07-27T15:30">${mb.year}</dd>
                        </dl>
                        <dl class="duration">
                            <dt>片长：</dt>
                            <dd>${mb.duration}</dd>
                        </dl>
                    </div>
                    <div itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating"
                         class="aggregate-rating">
                        <div class="rating-site yellow">
                            <p class="value">
                                <span itemprop="ratingValue">
                                    ${mb.value}分
                                </span>
                            </p>
                        </div>
                        <div class="rating-count">
                            <span itemprop="ratingCount">
                                (${mb.ratingCount}人评)
                        </span>
                        </div>
                    </div>
                </div>
                <div class="evaluate">
                    <dl class="rating">
                        <dt>快来评价：</dt>
                    </dl>
                    <div class="plays clearfix" monitor-desc="播放链接">
                        <s:if test="mb.supList == null">
                            <a class="play_btn2" id="downUrl" data-content="${mb.downUrl == null ? '暂无' : mb.downUrl}"
                               data-original-title="下载地址"
                               href="javascript:void(0);">
                                获取下载地址
                            </a>
                        </s:if>
                        <s:else>
                            <a class="play_btn" href="${mb.supList[0].link}" target="_blank">
                                <meta itemprop="url" content="http://www.letv.com/ptv/pplay/89511/1.html">
                            </a>
                        </s:else>
                        <ul id="supplies" class="providers clearfix">
                            <s:iterator value="mb.supList" var="li" status="con">
                                <s:if test="#con.count < 4">
                                    <li>
                                        <a class="${li.className}" href="${li.link}" target="_blank">
                                            <em>${li.name}</em>
                                            <i>></i>
                                        </a>
                                    </li>
                                </s:if>
                            </s:iterator>
                            <s:if test="mb.suppliesCount > 3">
                                <div class="more">
                                    <a class="toggle" href="###">更多</a>

                                    <div class="menu">
                                        <ul>
                                            <s:iterator value="mb.supList" var="li" status="con">
                                                <s:if test="#con.count > 3">
                                                    <li>
                                                        <a class="${li.className}" href="${li.link}" target="_blank">
                                                            <em>${li.name}</em>
                                                        </a>
                                                    </li>
                                                </s:if>
                                            </s:iterator>
                                        </ul>
                                    </div>
                                </div>
                            </s:if>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
        <div class="doc">
            <div class="bi">
                <h3 class="title_line">
                    <span class="inner">简介</span>
                </h3>

                <div class="doc_info">
                    <p class="less" itemprop="review" itemscope itemtype="http://schema.org/Review">
                        <span itemprop="reviewBody">${mb.less}</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="key_">
    ${mb.name}
    itv9,itv9影视,大片,最新电影,最新大片,免费电影,高清电影,更新最快的电影,在线视频,在线电影,伦理片,在线高清电影
    ${mb.name}-在线观看-ITV9影视
</div>
<jsp:include page="share.html"></jsp:include>
</body>
<script type="text/javascript">
    $(".more").bind("mouseover", function (event) {
        $(this).addClass("open");
        $(".menu")[0].style.display = 'block';
    });

    $(".menu").bind("mouseout", function (event) {
        $(this).removeClass("open");
        $(".menu")[0].style.display = 'none';
    });
    $("#downUrl").popover().click(
        function(e) {
            e.preventDefault();
        }
    );
</script>
</html>