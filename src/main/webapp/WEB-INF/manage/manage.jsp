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
        .table td{
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
                <li><a href="manageAction_fill.htm">信息补全</a></li>
                <li><a href="#">添加视频</a></li>
            </ul>
        </div>
    </div>
    <form action="" method="post">
        <div class="hero-unit narrow" style="padding: 10px;">
            <div style="position: relative;top: 5px">
                <label class="checkbox inline" style="padding-left: 10px">
                    查询空列
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox1" value="1" name="mb.name"> 名称
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox2" value="1" name="mb.director"> 导演
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox3" value="1" name="mb.actor"> 主演
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox4" value="1" name="mb.area"> 地区
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox5" value="1" name="mb.year"> 年代
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox6" value="1" name="mb.less"> 简介
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox7" value="1" name="mb.imgUrl"> 图片
                </label>
                <label class="checkbox inline">
                    <input type="checkbox" id="inlineCheckbox8" value="1" name="mb.supplies"> 资源
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
                            <td>${li.name==null?"无":li.name}</td>
                            <td>${li.director==null?"无":li.director}</td>
                            <td>${li.actor==""?"无":li.actor}</td>
                            <td>${li.year}</td>
                            <td>${li.area==null?"无":li.area}</td>
                            <td>${li.suppliesCount}</td>
                            <td>
                                <button class="btn" type="button">Edit</button>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div style="position: relative;top:20px;padding-left: 10px;">
                <div class="pagination">
                    <ul>
                        <li class="disabled"><a href="#">&laquo;</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li class="active"><a href="#">2</a></li>
                        <li class="active"><a href="#">3</a></li>
                        <li class="active"><a href="#">4</a></li>
                        <li class="active"><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </form>
</div>
<form action="" method="post">
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
                                    <input type="text" style="width: 175px">
                                </td>
                                <td>导演</td>
                                <td><input type="text" style="width: 175px"></td>
                            </tr>
                            <tr>
                                <td>主演</td>
                                <td>
                                    <input type="text" style="width: 175px">
                                </td>
                                <td>年代</td>
                                <td><input type="text" style="width: 175px"></td>
                            </tr>
                            <tr>
                                <td>地区</td>
                                <td>
                                    <input type="text" style="width: 175px">
                                </td>
                                <td>资源</td>
                                <td><input type="text" style="width: 175px"></td>
                            </tr>
                            <tr>
                                <td>图片</td>
                                <td colspan="3">
                                    <input id="inimg" type="text" style="width: 380px">
                                </td>
                            </tr>
                            <tr>
                                <td>下载</td>
                                <td colspan="3">
                                    <input type="text" style="width: 380px">
                                </td>
                            </tr>
                            <tr>
                                <td>简介</td>
                                <td colspan="3">
                                    <textarea rows="3" style="width: 380px"></textarea>
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
    });
</script>
</html>