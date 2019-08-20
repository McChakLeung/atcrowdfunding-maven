<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/20
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ZH-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <jsp:include page="../common/manager_header.jsp"/>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <jsp:include page="../common/manager_menu.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--<tr>--%>
                                <%--<td>1</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>PM - 项目经理</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>2</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>SE - 软件工程师</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>3</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>PG - 程序员</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>4</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>TL - 组长</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>5</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>GL - 组长</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>6</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>QA - 品质保证</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>7</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>QC - 品质控制</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>8</td>--%>
                                <%--<td><input type="checkbox"></td>--%>
                                <%--<td>SA - 软件架构师</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                    <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                    <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                                <%--<tr>--%>
                                    <%--<td>8</td>--%>
                                    <%--<td><input type="checkbox"></td>--%>
                                    <%--<td>CMO / CMS - 配置管理员</td>--%>
                                    <%--<td>--%>
                                        <%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
                                        <%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
                                        <%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li class="disabled"><a href="#">上一页</a></li>
                                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">下一页</a></li>
                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
        queryPage(1);
    });

    var loadingIndex = -1;

    var jsonObj = {
        "pageno":1,
        "pagesize":10
    };

    function queryPage(pageno) {
        jsonObj.pageno = pageno;
        $.ajax({
            type:"post",
            url:"${APP_PATH}/role/queryPage.do",
            data:jsonObj,
            beforeSend:function () {
                loadingIndex = layer.load(3, {time: 10*1000});
                return true;
            },
            success:function (result) {
                if(result.success){
                    layer.close(loadingIndex);
                    //从result对象中获取page对象
                    var page = result.page;
                    //从page对象中获取page数据行
                    var data = page.datalist;

                    //定义一个content进行拼接表格
                    var content = '';

                    //遍历data对象，并拼接表格
                    $.each(data,function (i,n) {
                        content += '<tr>';
                        content += '<td>'+(i+1)+'</td>';
                        content += '<td><input type="checkbox" id="'+n.id+'"></td>';
                        content += '<td>'+n.name+'</td>';
                        content += '<td>';
                        content += '<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        content += '<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content += '<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        content += '</td>';
                        content += '</tr>';
                    })
                    $("tbody").html(content);

                    //定义一个contentbar进行拼接分页
                    //var contentbar = '';
                }else{
                    layer.msg(result.message,{time:2000, icon:5, shift:5})
                }
            }
        })
    }

    $("tbody .btn-success").click(function(){
        window.location.href = "assignPermission.html";
    });
</script>
</body>
</html>

