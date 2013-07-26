<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setCharacterEncoding("utf-8");
%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>后台管理系统</title>
    <link rel="shortcut icon" href="<%=basePath%>bootstrap/img/itv9.ico"/>
    <link href="<%=basePath%>bootstrap/css/bootstrap.min2.css" rel="stylesheet">
    <script type="text/javascript" src="http://www.see-source.com/bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        .narrow {
            width: 980px;
            margin: 0 auto;
        }

        .navbar-inner {
            width: 960px;
        }

        .table td {
            font-size: 13px;
            font-family: '宋体';
        }

        .full {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="full">
    <div class="navbar" style="position: relative;top: 10px">
        <div class="navbar-inner narrow">
            <a class="brand" href="http://www.itv9.cn">网站首页</a>
            <ul class="nav">
                <li><a href="#">首页设置</a></li>
                <li><a href="manageAction_findFillMovie.htm">信息补全</a></li>
                <li><a href="#">添加视频</a></li>
            </ul>
        </div>
    </div>
    <form action="manageAction_findFillMovie.htm" method="post">
        <div class="hero-unit narrow" style="padding: 10px;">
            <div style="position: relative;top: 5px">
                <p style="margin-left: 10px">
                   影视名称 <input name="name" type="text" value="${mp.name== 'tru' ? '' : mp.name}" style="height: 30px;
                   margin-top: 5px">
                </p>
                <label class="checkbox inline" style="padding-left: 10px">
                    查询空列
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.name=='tru'?'checked':''} value="tru" name="mp.name"> 名称
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.director=='tru'?'checked':''} value="tru" name="mp.director"> 导演
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.actor=='tru'?'checked':''} value="tru" name="mp.actor"> 主演
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.area=='tru'?'checked':''} value="tru" name="mp.area"> 地区
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.year=='tru'?'checked':''} value="tru" name="mp.year"> 年代
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.less=='tru'?'checked':''} value="tru" name="mp.less"> 简介
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.imgUrl=='tru'?'checked':''} value="tru" name="mp.imgUrl"> 图片
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" ${mp.supplies=='tru'?'checked':''} value="tru" name="mp.supplies"> 资源
                </label>
                <button class="btn" type="submit" style="float: right">查询</button>
            </div>
            <div style="position: relative;top: 20px;padding-left: 10px">
                <table class="table-bordered table table-hover table-striped">
                    <caption>视频残缺查询</caption>
                    <thead>
                    <tr>
                        <th>影视名称</th>
                        <th>导演</th>
                        <th>主演</th>
                        <th>年代</th>
                        <th>地区</th>
                        <th>资源</th>
                        <th>编辑</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="list" var="li">
                        <tr>
                            <td>${li.name}&nbsp;</td>
                            <td>${li.director}&nbsp;</td>
                            <td>${li.actor}&nbsp;</td>
                            <td>${li.year}&nbsp;</td>
                            <td>${li.area}&nbsp;</td>
                            <td>${li.suppliesCount}&nbsp;</td>
                            <td>
                                <p id="${li.id}" style="display: none">
                                    {'id':'${li.id}','name':'${li.name}','director':'${li.director}',
                                    'actor':'${li.actor}','year':'${li.year}','area':'${li.area}',
                                    'supplies':'${li.supplies}','imgUrl':'${li.imgUrl}','less':'${li.less}',
                                    'downUrl':'${li.downUrl}','supplierUrl':'${li.supplierUrl}'}
                                </p>
                                <button class="btn" type="button" data="${li.id}">Edit</button>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div style="position: relative;top:20px;padding-left: 10px;">
                <div class="pagination">
                    <ul>
                        <s:if test="mp.page == 1">
                            <li class="disabled">
                                <a href="javascript:void(0);">&laquo;</a>
                            </li>
                        </s:if>
                        <s:else>
                            <li class="active">
                                <a href="<%=basePath%>manageAction_findFillMovie.htm?mp.page=${mp.page-1}&mp.name=${mp.name}&mp.director=${mp.director}&mp.actor=${mp.actor}&mp.area=${mp.area}&mp.year=${mp.year}&mp.less=${mp.less}&mp.imgUrl=${mp.imgUrl}&mp.supplies=${mp.supplies}">&laquo;</a>
                            </li>
                        </s:else>
                        <s:iterator begin="1" end="10" var="p">
                            <s:if test="#p == mp.page">
                                <li class="disabled">
                                    <a href="javascript:void(0);">${p}</a>
                                </li>
                            </s:if>
                            <s:if test="#p <= mp.page_num and #p != mp.page">
                                <li class="active">
                                    <a href="<%=basePath%>manageAction_findFillMovie.htm?mp.page=${p}&mp.name=${mp.name}&mp.director=${mp.director}&mp.actor=${mp.actor}&mp.area=${mp.area}&mp.year=${mp.year}&mp.less=${mp.less}&mp.imgUrl=${mp.imgUrl}&mp.supplies=${mp.supplies}">${p}</a>
                                </li>
                            </s:if>
                        </s:iterator>
                        <s:if test="mp.page < mp.page_num">
                            <li class="active">
                                <a href="<%=basePath%>manageAction_findFillMovie.htm?mp.page=${mp.page+1}&mp.name=${mp.name}&mp.director=${mp.director}&mp.actor=${mp.actor}&mp.area=${mp.area}&mp.year=${mp.year}&mp.less=${mp.less}&mp.imgUrl=${mp.imgUrl}&mp.supplies=${mp.supplies}">&raquo;</a>
                            </li>
                            <li class="active">
                                <a href="<%=basePath%>manageAction_findFillMovie.htm?mp.page=${mp.page_num}&mp.name=${mp.name}&mp.director=${mp.director}&mp.actor=${mp.actor}&mp.area=${mp.area}&mp.year=${mp.year}&mp.less=${mp.less}&mp.imgUrl=${mp.imgUrl}&mp.supplies=${mp.supplies}">${mp.page_num}</a>
                            </li>
                        </s:if>
                        <s:else>
                            <li class="disabled"><a href="#">&raquo;</a></li>
                        </s:else>
                    </ul>
                </div>
            </div>
        </div>
    </form>
</div>
<form action="manageAction_updateMovie.htm" method="post">
    <div id="myModal" class="modal hide fade" style="width: 650px" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Edit</h3>
        </div>
        <div class="modal-body">
            <div class="container-fluid" style="padding-left: 0px">
                <div class="row-fluid">
                    <div class="span2">
                        <img id="img" src="" alt="">
                        <button class="btn" id="test" type="button" style="position: relative;top: 30px">Test</button>
                    </div>
                    <div class="span10">
                        <table class="table table-bordered">
                            <tr>
                                <td>名称</td>
                                <td>
                                    <input name="mb.id" type="hidden">
                                    <input name="mb.name" type="text" style="width: 175px;height: 28px">
                                </td>
                                <td>导演</td>
                                <td>
                                    <input name="mb.director" type="text" style="width: 175px;height: 28px">
                                </td>
                            </tr>
                            <tr>
                                <td>主演</td>
                                <td>
                                    <input name="mb.actor" type="text" style="width: 175px;height: 28px">
                                </td>
                                <td>年代</td>
                                <td><input name="mb.year" type="text" style="width: 175px;height: 28px"></td>
                            </tr>
                            <tr>
                                <td>地区</td>
                                <td>
                                    <input name="mb.area" type="text" style="width: 175px;height: 28px">
                                </td>
                                <td>资源</td>
                                <td><input name="mb.supplies" type="text" style="width: 175px;height: 28px"></td>
                            </tr>
                            <tr>
                                <td>图片</td>
                                <td colspan="3">
                                    <input id="inimg" name="mb.imgUrl" type="text" style="width: 380px;height: 28px">
                                </td>
                            </tr>
                            <tr>
                                <td>下载</td>
                                <td colspan="3">
                                    <input type="text" name="mb.downUrl" style="width: 380px;height: 28px">
                                </td>
                            </tr>
                            <tr>
                                <td>简介</td>
                                <td colspan="3">
                                    <textarea name="mb.less" rows="3" style="width: 380px"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <p id="supplierUrl"></p>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            <button class="btn btn-primary" type="submit">保存</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    $("#test").bind("click", function () {
        var img = $("#inimg").val();
        $("#img").attr('src', img);
    })
    $("table .btn").bind("click", function () {
        $('#myModal').modal('show')
        var id = $(this).attr("data");
        var data=$("#"+id).text();
        var json = eval('(' + data + ')');
        $("table input[name='mb.name']").val(json.name);
        $("table input[name='mb.director']").val(json.director);
        $("table input[name='mb.actor']").val(json.actor);
        $("table input[name='mb.year']").val(json.year);
        $("table input[name='mb.area']").val(json.area);
        $("table input[name='mb.imgUrl']").val(json.imgUrl);
        $("table textarea[name='mb.less']").val(json.less);
        $("table input[name='mb.downUrl']").val(json.downUrl);
        $("table input[name='mb.supplies']").val(json.supplies);
        $("table input[name='mb.id']").val(json.id);
        $("#supplierUrl").text(json.supplierUrl);
    });
</script>
</html>