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
    <title>itv9电影频道-最新最全更新最快的电影网站-在线观看</title>
    <link rel="shortcut icon" href="<%=basePath%>bootstrap/img/itv9.ico" />
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/public.css" rel="stylesheet">
    <link href="bootstrap/css/index.css" rel="stylesheet">
    <link href="bootstrap/css/type.css" rel="stylesheet">
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
                <form id="searchForm" action="searchAction_search.htm" method="post" target="_blank">
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
<div class="info">
    <div class="dytype">
        <h2>电影分类</h2>
        <ul class="">
            <s:if test="mp.typeName != ''&&mp.typeName !=null">
                <li>
                    <a title="取消此条件" target="_self"
                       href="<%=basePath%>searchAction_type.htm?mp.typeName=mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                       ${mp.typeName}
                    </a>
                </li>
            </s:if>
            <s:if test="mp.area != ''&&mp.area !=null">
                <li>
                    <a title="取消此条件" target="_self"
                       href="<%=basePath%>searchAction_type.htm?mp.area=&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                       ${mp.area}
                    </a>
                </li>
            </s:if>
            <s:if test="mp.year != ''&&mp.year !=null">
                <li>
                    <a title="取消此条件" target="_self"
                       href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                       ${mp.year}
                    </a>
                </li>
            </s:if>
            <s:if test="mp.actor != ''&&mp.actor !=null">
                <li><a title="取消此条件" target="_self"
                       href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=">${mp.actor}</a>
                </li>
            </s:if>
        </ul>
        <a class="re" target="_self" href="<%=basePath%>searchAction_type.htm?mp.area=">重新筛选</a>
    </div>
    <div class="type-info">
        <ul>
            <li class="li-one">按类型：</li>
            <li class="li-a">
                <a target="_self" class="${mp.typeName==''?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    全部
                </a>
                <a target="_self" class="${mp.typeName=='喜剧'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=喜剧&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    喜剧
                </a>
                <a target="_self" class="${mp.typeName=='爱情'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=爱情&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    爱情
                </a>
                <a target="_self" class="${mp.typeName=='恐怖'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=恐怖&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    恐怖
                </a>
                <a target="_self" class="${mp.typeName=='动作'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=动作&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    动作
                </a>
                <a target="_self" class="${mp.typeName=='战争'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=战争&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    战争
                </a>
                <a target="_self" class="${mp.typeName=='科幻'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=科幻&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    科幻
                </a>
                <a target="_self" class="${mp.typeName=='悬疑'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=悬疑&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    悬疑
                </a>
                <a target="_self" class="${mp.typeName=='动画'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=动画&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    动画
                </a>
                <a target="_self" class="${mp.typeName=='犯罪'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=犯罪&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    犯罪
                </a>
                <a target="_self" class="${mp.typeName=='文艺'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=文艺&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    文艺
                </a>
                <a target="_self" class="${mp.typeName=='记录'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=记录&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    记录
                </a>
                <a target="_self" class="${mp.typeName=='伦理'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=伦理&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    伦理
                </a>
                <a target="_self" class="${mp.typeName=='古装'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=古装&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    古装
                </a>
                <a target="_self" class="${mp.typeName=='历史'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.typeName=历史&mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}">
                    历史
                </a>
                <a target="_self" href="#">其他</a>
            </li>
        </ul>
        <ul>
            <li class="li-one">按地区：</li>
            <li class="li-a">
                <a target="_self" class="${mp.area==''?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    全部
                </a>
                <a target="_self" class="${mp.area=='大陆'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=大陆&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    大陆
                </a>
                <a target="_self" class="${mp.area=='美国'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=美国&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    美国
                </a>
                <a target="_self" class="${mp.area=='香港'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=香港&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    香港
                </a>
                <a target="_self" class="${mp.area=='韩国'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=韩国&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    韩国
                </a>
                <a target="_self" class="${mp.area=='日本'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=日本&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    日本
                </a>
                <a target="_self" class="${mp.area=='台湾'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=台湾&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    台湾
                </a>
                <a target="_self" class="${mp.area=='法国'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=法国&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    法国
                </a>
                <a target="_self" class="${mp.area=='英国'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=英国&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    英国
                </a>
                <a target="_self" class="${mp.area=='泰国'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=泰国&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    泰国
                </a>
                <a target="_self" class="${mp.area=='印度'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=印度&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    印度
                </a>
                <a target="_self" class="${mp.area=='其他'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    其他
                </a>
            </li>
        </ul>
        <ul>
            <li class="li-one">按年代：</li>
            <li class="li-a">
                <a target="_self" class="${mp.year==''?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    全部
                </a>
                <a target="_self" class="${mp.year=='2013'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2013&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2013
                </a>
                <a target="_self" class="${mp.year=='2012'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2012&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2012
                </a>
                <a target="_self" class="${mp.year=='2011'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2011&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2011
                </a>
                <a target="_self" class="${mp.year=='2010'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2010&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2010
                </a>
                <a target="_self" class="${mp.year=='2009'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2009&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2009
                </a>
                <a target="_self" class="${mp.year=='2008'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2008&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2008
                </a>
                <a target="_self" class="${mp.year=='2007'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2007&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2007
                </a>
                <a target="_self" class="${mp.year=='2006'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2006&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2006
                </a>
                <a target="_self" class="${mp.year=='2005'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2005&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    2005
                </a>
                <a target="_self" class="${mp.year=='2004'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=2004 2003&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}">
                    更早
                </a>
            </li>
        </ul>
        <ul>
            <li class="li-one">按明星：</li>
            <li class="li-a">
                <a target="_self" class="${mp.actor==''?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=&mp.typeName=${mp.typeName}">
                    全部
                </a>
                <a target="_self" class="${mp.actor=='周星驰'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=周星驰&mp.typeName=${mp.typeName}">
                    周星驰
                </a>
                <a target="_self" class="${mp.actor=='李连杰'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=李连杰&mp.typeName=${mp.typeName}">
                    李连杰
                </a>
                <a target="_self" class="${mp.actor=='成龙'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=成龙&mp.typeName=${mp.typeName}">
                    成龙
                </a>
                <a target="_self" class="${mp.actor=='刘德华'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=刘德华&mp.typeName=${mp.typeName}">
                    刘德华
                </a>
                <a target="_self" class="${mp.actor=='范冰冰'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=范冰冰&mp.typeName=${mp.typeName}">
                    范冰冰
                </a>
                <a target="_self" class="${mp.actor=='舒淇'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=舒淇&mp.typeName=${mp.typeName}">
                    舒淇
                </a>
                <a target="_self" class="${mp.actor=='张柏芝'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=张柏芝&mp.typeName=${mp.typeName}">
                    张柏芝
                </a>
                <a target="_self" class="${mp.actor=='张曼玉'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=张曼玉&mp.typeName=${mp.typeName}">
                    张曼玉
                </a>
                <a target="_self" class="${mp.actor=='林心如'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=林心如&mp.typeName=${mp.typeName}">
                    林心如
                </a>
                <a target="_self" class="${mp.actor=='张国荣'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=张国荣&mp.typeName=${mp.typeName}">
                    张国荣
                </a>
                <a target="_self" class="${mp.actor=='甄子丹'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=甄子丹&mp.typeName=${mp.typeName}">
                    甄子丹
                </a>
                <a target="_self" class="${mp.actor=='黄晓明'?'li-a-sel':''}"
                   href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=黄晓明&mp.typeName=${mp.typeName}">
                    黄晓明
                </a>
            </li>
        </ul>
    </div>
    <div class="results">
        <ul>
            <s:iterator value="list" var="li">
                <li>
                    <div class="left-img">
                        <a href="<%=basePath%>itv_fm/playAction/play/${li.id}.html" class="left-a" target="_blank">
                            <img src="http://p8.qhimg.com/d/_hao360/default.png" data-original="${li.imgUrl}" class="lazy" alt="${li.name}">
                        </a>
                    </div>
                    <div class="right-content">
                        <p class="right-title">
                            <a href="#">${li.name}</a>
                        </p>

                        <p class="right-info">
                            <span style="color: #999999">导演：</span>
                                ${li.director}
                        </p>

                        <p class="right-info">
                            <span style="color: #999999">主演：</span>
                                ${li.actor}
                        </p>

                        <p class="right-info">
                            <span style="color: #999999">年代：</span>
                                ${li.year}
                        </p>

                        <p class="right-info">
                            <span style="color: #999999">评分：</span>
                            <span style="font-size: 12px;color: #ff4500">${li.value} 分</span>
                        </p>
                    </div>
                </li>
            </s:iterator>
        </ul>
    </div>
</div>
<div class="page">
    <s:iterator value="pageList" var="pli">
        <s:if test="#pli == mp.page">
            <a target="_self" href="javascript:void(0)" class="filter-sel-active">${mp.page}</a>
        </s:if>
        <s:else>
            <a target="_self"
               href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}&mp.page=${pli}">${pli}</a>
        </s:else>
    </s:iterator>
    <s:if test="mp.page < mp.page_num">
        <a target="_self"
           href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}&mp.page=${mp.page+1}">下一页</a>
        <a target="_self"
           href="<%=basePath%>searchAction_type.htm?mp.area=${mp.area}&mp.year=${mp.year}&mp.actor=${mp.actor}&mp.typeName=${mp.typeName}&mp.page=${mp.page_num}">${mp.page_num}</a>
    </s:if>
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
<script type="text/javascript">
    $(function () {
        $("img.lazy").lazyload({
            threshold: 100
        });
    });
</script>
</body>
</html>