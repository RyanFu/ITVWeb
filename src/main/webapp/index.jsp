<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
    <title>itv9电影频道-最新最全更新最快的电影网站-在线观看</title>
    <link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>bootstrap/css/index.css" rel="stylesheet">
    <link href="<%=basePath%>bootstrap/css/copyright.css" rel="stylesheet">
    <link href="<%=basePath%>bootstrap/css/public.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/gotop.js"></script>
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("img.lazy").lazyload({
                threshold :100
            });
            var lazy=$("#myCarousel img.lazy2");
            for(var i=0;i<lazy.length;i++){
                var imgSrc = lazy[i].getAttribute('data-original');
                lazy[i].setAttribute('src', imgSrc);
            }
        });
    </script>
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
                <form id="searchForm" action="searchAction_search.htm" method="get" target="_blank"
                      onsubmit="return checkSeach();">
                    <input class="span2" name="name" id="search-text" type="text" autocomplete="off">
                    <button class="btn" type="submit">搜索</button>
                </form>>
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
<div id="bd">
<div id="focus">
    <div id="myCarousel" class="carousel slide">
        <div class="carousel-inner">
            <s:iterator value="list" var="li" status="con">
                <s:if test="#con.count==1">
                    <div class="active item">
                        <a href="<%=basePath%>itv_fm/playAction/play/${li.id}.html" target="_blank">
                            <img src="${li.bigImgUrl}" alt="${li.name}">
                        </a>
                    </div>
                </s:if>
                <s:else>
                    <div class="item">
                        <a href="<%=basePath%>itv_fm/playAction/play/${li.id}.html" target="_blank">
                            <img src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${li.bigImgUrl}" class="lazy2" alt="${li.name}">
                        </a>
                    </div>
                </s:else>
            </s:iterator>
        </div>
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>

        <div class="ft">
            <div class="focus-title" id="focus-title">
                <s:iterator value="list" var="li" status="con">
                    <s:if test="#con.count==1">
                        <a style="display: inline;" href="<%=basePath%>itv_fm/playAction/play/${li.id}.html" target="_blank">
                            <em class="title">${li.name}</em>
                            ${li.text}
                        </a>
                    </s:if>
                    <s:else>
                        <a  href="<%=basePath%>itv_fm/playAction/play/${li.id}.html" target="_blank">
                            <em class="title">${li.name}</em>
                            ${li.text}
                        </a>
                    </s:else>
                </s:iterator>
                <span class="icon"></span>
            </div>
            <ol id="fol" class="carousel-indicators">
                <s:iterator value="list" var="li" status="con">
                    <li data-target="#myCarousel" data-slide-to="${con.count-1}" class="active">
                        <a href="<%=basePath%>itv_fm/playAction/play/${li.id}.html">
                            <img src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${li.miniImgUrl}" class="lazy2" alt="${li.name}">
                        </a>
                    </li>
                </s:iterator>
            </ol>
            <div class="ftbg"></div>
        </div>
    </div>
</div>
<div class="film_type">
    <div class="film_type_info film_type_info_w">
        <dl class="dl_type">
            <dt>类型</dt>
            <dd>
                <a href="">喜剧</a>
            </dd>
            <dd><a href="">动作</a></dd>
            <dd><a href="">爱情</a></dd>
            <dd><a href="">恐怖</a></dd>
            <dd><a href="">科幻</a></dd>
            <dd><a href="">犯罪</a></dd>
            <dd><a href="">战争</a></dd>
            <dd><a href="">悬疑</a></dd>
            <dd><a href="">动画</a></dd>
            <dd><a href="">伦理</a></dd>
        </dl>
        <dl class="dl_arer">
            <dt>
                地区
            </dt>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=香港" target="_blank">香港</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=台湾" target="_blank">台湾</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=大陆" target="_blank">大陆</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=美国" target="_blank">美国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=韩国" target="_blank">韩国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=泰国" target="_blank">泰国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=法国" target="_blank">法国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=英国" target="_blank">英国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=日本" target="_blank">日本</a></dd>
        </dl>
        <dl class="years">
            <dt>年代</dt>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2013" target="_blank">2013</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2012" target="_blank">2012</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2011" target="_blank">2011</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2010" target="_blank">2010</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2009" target="_blank">2009</a></dd>
            <dd><a href="">全&nbsp;部</a></dd>
        </dl>
        <dl class="star">
            <dt>明星</dt>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=成龙" target="_blank">成龙</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=李连杰" target="_blank">李连杰</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=甄子丹" target="_blank">甄子丹</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=孙红雷" target="_blank">孙红雷</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=徐铮" target="_blank">徐铮</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=王宝强" target="_blank">王宝强</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=章子怡" target="_blank">章子怡</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=谢霆锋" target="_blank">谢霆锋</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=舒淇" target="_blank">舒淇</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=刘德华" target="_blank">刘德华</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=张柏芝" target="_blank">张柏芝</a></dd>
        </dl>
        <div class="ad_top" style="display: none">
            头部
        </div>
    </div>
</div>
<div class="column hot_column">
    <div class="hot_column_left">
        <div class="hot_column_right_title">
            <h2 class="cont_box_h2">热播推荐</h2>
        </div>
        <div>
            <ul class="figure-list">
                <s:iterator value="hotList" var="hot">
                    <li>
                        <div class="le-figure">
                            <div class="pic">
                                <a class="mod-hint le-playico" target="_blank" title="${hot.name}"
                                   href="<%=basePath%>itv_fm/playAction/play/${hot.mid}.html">
                                    <img alt="${hot.name}" src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${hot.miniImgUrl}" class="lazy">
                                    <span class="hintbg"></span>
                                    <span class="center"> ${hot.asactor} </span>
                                </a>
                            </div>
                            <div class="cont">
                                <h4>
                                    <a class="title v-tomato-tit-yellow" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${hot.mid}.html">${hot.name}</a>
                                </h4>
                                <p>${hot.text}</p>
                            </div>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="hot_column_right">
        <div class="hot_column_right_title">
            <h3 class="cont_box_h2">热搜榜</h3>
        </div>
        <div>
            <ol class="hot_column_right_ol">
                <s:iterator value="hotTopList" var="htop" status="con">
                    <s:if test="#con.count < 4">
                        <li class="hot_ol_li_top3">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${htop.name}" href="<%=basePath%>itv_fm/playAction/play/${htop.mid}.html" target="_blank">${htop.name}</a>
                            </div>
                            <div class="star">${htop.asactor}</div>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="hot_ol_li_tail">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${htop.name}" href="<%=basePath%>itv_fm/playAction/play/${htop.mid}.html" target="_blank">${htop.name}</a>
                            </div>
                            <div class="star">${htop.asactor}</div>
                        </li>
                    </s:else>
                </s:iterator>
            </ol>
        </div>
    </div>
</div>
<hr class="hr_line">
<div class="column net_column">
    <div class="hot_column_left">
        <div class="hot_column_right_title">
            <h2 class="cont_box_h2">首播剧场</h2>
        </div>
        <div>
            <ul class="figure-list">
                <s:iterator value="netList" var="net">
                    <li>
                        <div class="le-figure">
                            <div class="pic">
                                <a class="mod-hint le-playico" target="_blank" title="${net.name}"
                                   href="<%=basePath%>itv_fm/playAction/play/${net.mid}.html">
                                    <img alt="${net.name}" src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${net.miniImgUrl}" class="lazy">
                                    <span class="hintbg"></span>
                                    <span class="center"> ${net.asactor} </span>
                                </a>
                            </div>
                            <div class="cont">
                                <h4>
                                    <a class="title v-tomato-tit-yellow" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${net.mid}.html">${net.name}</a>
                                </h4>

                                <p>${net.text}</p>
                            </div>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="hot_column_right">
        <div class="hot_column_right_title">
            <h3 class="cont_box_h2">首播榜</h3>
        </div>
        <div>
            <ol class="hot_column_right_ol">
                <s:iterator value="netTopList" var="ntop" status="con">
                    <s:if test="#con.count < 4">
                        <li class="hot_ol_li_top3">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${ntop.name}" href="<%=basePath%>itv_fm/playAction/play/${ntop.mid}.html" target="_blank">${ntop.name}</a>
                            </div>
                            <div class="star">${ntop.asactor}</div>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="hot_ol_li_tail">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${ntop.name}" href="<%=basePath%>itv_fm/playAction/play/${ntop.mid}.html" target="_blank">${ntop.name}</a>
                            </div>
                            <div class="star">${ntop.asactor}</div>
                        </li>
                    </s:else>
                </s:iterator>
            </ol>
        </div>
    </div>
</div>
<hr class="hr_line">
<div class="column ad_column" style="display: none">
    <div class="ad1_column">

    </div>
    <div class="ad2_column">

    </div>
</div>
<hr class="hr_line" style="display: none">
<div class="column pre_column">
    <div>
        <h2 class="cont_box_h2">预告剧场</h2>
    </div>
    <div>
        <ul class="pre_ul">
            <s:iterator value="preList" var="pre" status="con">
                <s:if test="#con.count == 1">
                    <li>
                        <div class="le-figure">
                            <div class="pic">
                                <a class="mod-hint le-playico" target="_blank" title="${pre.name}" href="<%=basePath%>itv_fm/playAction/play/${pre.mid}.html">
                                    <img alt="${pre.name}" src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${pre.miniImgUrl}" class="lazy">
                                    <span class="hintbg"></span>
                                    <span class="center"> 点击下载 </span>
                                </a>
                            </div>
                            <div class="cont">
                                <h4>
                                    <a class="title v-tomato-tit-yellow" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${pre.mid}.html">${pre.name}</a>
                                </h4>
                            </div>
                        </div>
                    </li>
                </s:if>
                <s:else>
                    <li class="pre_li_3">
                        <div class="le-figure">
                            <div class="pic">
                                <a class="mod-hint le-playico" target="_blank" title="${pre.name}" href="<%=basePath%>itv_fm/playAction/play/${pre.mid}.html">
                                    <img alt="${pre.name}" src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${pre.miniImgUrl}" class="lazy">
                                    <span class="hintbg"></span>
                                    <span class="center"> 点击下载 </span>
                                </a>
                            </div>
                            <div class="cont">
                                <h4>
                                    <a class="title v-tomato-tit-yellow" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${pre.mid}.html">${pre.name}</a>
                                </h4>
                            </div>
                        </div>
                    </li>
                </s:else>
            </s:iterator>
        </ul>
    </div>
</div>
<hr class="hr_line">
<div class="column easy_column">
    <div class="easy_column_left">
        <div class="hot_column_right_title">
            <h2 class="cont_box_h2">欢乐剧场</h2>
        </div>
        <div>
            <ul class="figure-list">
                <s:iterator value="easyList" var="easy">
                    <li>
                        <div class="le-figure">
                            <div class="pic">
                                <a class="mod-hint le-playico" target="_blank" title="${easy.name}"
                                   href="<%=basePath%>itv_fm/playAction/play/${easy.mid}.html">
                                    <img alt="${easy.name}" src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${easy.miniImgUrl}" class="lazy">
                                    <span class="hintbg"></span>
                                    <span class="center"> ${easy.asactor} </span>
                                </a>
                            </div>
                            <div class="cont">
                                <h4>
                                    <a class="title v-tomato-tit-yellow" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${easy.mid}.html">${easy.name}</a>
                                </h4>
                                <p>${easy.text}</p>
                            </div>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="hot_column_right">
        <div class="hot_column_right_title">
            <h3 class="cont_box_h2">欢乐榜</h3>
        </div>
        <div>
            <ol class="hot_column_right_ol">
                <s:iterator value="easyTopList" var="etop" status="con">
                    <s:if test="#con.count < 6">
                        <li class="hot_ol_li_top3">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${etop.name}" href="<%=basePath%>itv_fm/playAction/play/${etop.mid}.html" target="_blank">${etop.name}</a>
                            </div>
                            <div class="star">${etop.asactor}</div>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="hot_ol_li_tail">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${etop.name}" href="<%=basePath%>itv_fm/playAction/play/${etop.mid}.html" target="_blank">${etop.name}</a>
                            </div>
                            <div class="star">${etop.asactor}</div>
                        </li>
                    </s:else>
                </s:iterator>
            </ol>
        </div>
    </div>
</div>
<hr class="hr_line">
<div class="column ent_column">
    <div class="easy_column_left">
        <div class="hot_column_right_title">
            <h2 class="cont_box_h2">激情剧场</h2>
        </div>
        <div>
            <ul class="figure-list">
                <s:iterator value="pasList" var="pas">
                    <li>
                        <div class="le-figure">
                            <div class="pic">
                                <a class="mod-hint le-playico" target="_blank" title="${pas.name}"
                                   href="<%=basePath%>itv_fm/playAction/play/${pas.mid}.html">
                                    <img alt="${pas.name}" src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${pas.miniImgUrl}" class="lazy">
                                    <span class="hintbg"></span>
                                    <span class="center"> ${pas.asactor} </span>
                                </a>
                            </div>
                            <div class="cont">
                                <h4>
                                    <a class="title v-tomato-tit-yellow" target="_blank" href="<%=basePath%>itv_fm/playAction/play/${pas.mid}.html">${pas.name}</a>
                                </h4>
                                <p>${pas.text}</p>
                            </div>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="hot_column_right">
        <div class="hot_column_right_title">
            <h3 class="cont_box_h2">激情榜</h3>
        </div>
        <div>
            <ol class="hot_column_right_ol">
                <s:iterator value="pasTopList" var="ptop" status="con">
                    <s:if test="#con.count < 6">
                        <li class="hot_ol_li_top3">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${ptop.name}" href="<%=basePath%>itv_fm/playAction/play/${ptop.mid}.html" target="_blank">${ptop.name}</a>
                            </div>
                            <div class="star">${ptop.asactor}</div>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="hot_ol_li_tail">
                            <div class="index">${con.count}</div>
                            <div>
                                <a title="${ptop.name}" href="<%=basePath%>itv_fm/playAction/play/${ptop.mid}.html" target="_blank">${ptop.name}</a>
                            </div>
                            <div class="star">${ptop.asactor}</div>
                        </li>
                    </s:else>
                </s:iterator>
            </ol>
        </div>
    </div>
</div>
</div>
<hr class="hr_line">
<div class="film_type">
    <div class="film_type_title">
        <h2 class="film_type_title_font">
            电影分类导航
        </h2>
    </div>
    <div class="film_type_info">
        <dl class="dl_type">
            <dt>类型</dt>
            <dd>
                <a href="">喜剧</a>
            </dd>
            <dd><a href="">动作</a></dd>
            <dd><a href="">爱情</a></dd>
            <dd><a href="">恐怖</a></dd>
            <dd><a href="">科幻</a></dd>
            <dd><a href="">犯罪</a></dd>
            <dd><a href="">战争</a></dd>
            <dd><a href="">悬疑</a></dd>
            <dd><a href="">动画</a></dd>
            <dd><a href="">伦理</a></dd>
        </dl>
        <dl class="dl_arer">
            <dt>
                地区
            </dt>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=香港" target="_blank">香港</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=台湾" target="_blank">台湾</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=大陆" target="_blank">大陆</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=美国" target="_blank">美国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=韩国" target="_blank">韩国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=泰国" target="_blank">泰国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=法国" target="_blank">法国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=英国" target="_blank">英国</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.area=日本" target="_blank">日本</a></dd>
        </dl>
        <dl class="years">
            <dt>年代</dt>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2013" target="_blank">2013</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2012" target="_blank">2012</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2011" target="_blank">2011</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2010" target="_blank">2010</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=2009" target="_blank">2009</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.year=" target="_blank">全&nbsp;部</a></dd>
        </dl>
        <dl class="star">
            <dt>明星</dt>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=成龙" target="_blank">成龙</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=李连杰" target="_blank">李连杰</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=甄子丹" target="_blank">甄子丹</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=孙红雷" target="_blank">孙红雷</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=徐铮" target="_blank">徐铮</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=王宝强" target="_blank">王宝强</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=章子怡" target="_blank">章子怡</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=谢霆锋" target="_blank">谢霆锋</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=舒淇" target="_blank">舒淇</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=刘德华" target="_blank">刘德华</a></dd>
            <dd><a href="<%=basePath%>searchAction_type.htm?mp.actor=张柏芝" target="_blank">张柏芝</a></dd>
        </dl>
        <dl class="resys">
            <dt>推荐</dt>
            <dd><a href="">中国合伙人</a></dd>
            <dd><a href="">人再囧途之泰囧</a></dd>
            <dd><a href="">钢铁侠三</a></dd>
            <dd><a href="">全城高考</a></dd>
        </dl>
    </div>
</div>
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
</body>
<script type="text/javascript">
    $('#myCarousel').carousel({
        interval: 4000,
        pause: 'hover'
    });
    $('#myCarousel').on('slid', function (e) {
        var xx = $(this);
        var id = 0
        setTimeout(
                function () {
                    try{
                        id = xx.find('li.active').attr("data-slide-to");
                        var title = $('#focus-title a');
                        var len = title.length;
                        for (var i = 0; i < len; i++) {
                            if(title[i].style!=undefined){
                                title[i].style.display = 'none';
                            }
                        }
                        title[id].style.display = 'inline';
                    }catch (err){}
                }, 0);

    })

    var over=true;
    $("#fol li").bind("mouseover", function (event) {
        var id_ = $(this).attr("data-slide-to");
        id_ = Number(id_);
        $(this).addClass("actives");
        $('#myCarousel').carousel(id_);
        var title = $('#focus-title a');
        var len = title.length;
        for (var i = 0; i < len; i++) {
            title[i].style.display = 'none';
        }
        title[id_].style.display = 'inline';
    });
    $("#fol li").bind("mouseout", function (event) {
        $(this).removeClass("actives");
    });
    function checkSeach(){
        var svalue=$("#search-text").val();
        var reg="/^[\\s]*$/";
        if(reg.test(svalue)){
            return false;
        }
        return true;
    }
</script>
</html>