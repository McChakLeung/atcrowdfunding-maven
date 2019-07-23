<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/15
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form class="form-inline" role="form" style="float:left;" >
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="fqueryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="queryBtn" type="button" class="btn btn-warning" ><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">

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
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
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
        queryPageUser(1);
    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });

    function pageChange(pageno){
        //window.location.href="${APP_PATH}/user/index.do?pageno="+pageno ;
        queryPageUser(pageno);
    }

    var loadingIndex = -1;

    var jsonObj = {
        "pageno":1,
        "pagesize":10
    };

    function queryPageUser(pageno) {
        jsonObj.pageno = pageno;
        $.ajax({
            type:"post",
            url:"${APP_PATH}/user/index.do",
            data:jsonObj,
            beforeSend:function () {
                loadingIndex = layer.load(3, {time: 10*1000});
                return true;
            },
            success:function(result){
                layer.close(loadingIndex);
                if(result.success){
                    var page = result.page ;
                    var data = page.datalist ;
                    //数据行
                    var content = '';
                    $.each(data,function(i,n){
                        content+='<tr>';
                        content+='  <td>'+(i+1)+'</td>';
                        content+='  <td><input type="checkbox"></td>';
                        content+='  <td>'+n.loginacct+'</td>';
                        content+='  <td>'+n.username+'</td>';
                        content+='  <td>'+n.email+'</td>';
                        content+='  <td>';
                        content+='	  <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        content+='	  <button type="button" class="btn btn-primary btn-xs" onclick="toUpdate('+n.id+')"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteUser('+n.id+',\''+n.loginacct+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
                        content+='  </td>';
                        content+='</tr>';
                    });
                    $("tbody").html(content);

                    //分页
                    var contentbar = '';
                    if(page.pageno==1){
                        contentbar += '<li class="disabled"><a href="#">上一页</a></li>';
                    }else{
                        contentbar += '<li><a href="#" onclick="pageChange('+(page.pageno-1)+')">上一页</a></li>';
                    }

                    for(var i =1; i <= page.totalno;i++) {
                        contentbar += '<li ';
                        if(page.pageno == i) {
                            contentbar += 'class= "active"';
                        }
                            contentbar += '><a href="#" onclick="pageChange(' + i + ')">' + i + '</a></li>';
                    }
                    if(page.pageno==page.totalno) {
                        contentbar += '<li class="disabled"><a href="#">下一页</a></li>';
                    }else{
                        contentbar += '<li><a href="#" onclick="pageChange('+(page.pageno+1)+')">下一页</a></li>';
                    }
                    $(".pagination").html(contentbar);

                }else{
                    layer.msg(result.message,{time:2000, icon:5, shift:5})
                }
            }
        })
    }

    //模糊查询函数调用
    $("#queryBtn").click(function () {
        var queryText = $("#fqueryText").val();
        jsonObj.queryText = queryText;
        queryPageUser(1);
    });

    function toUpdate(id) {
        window.location.href = '${APP_PATH}/user/toUpdate.htm?id=' + id;
    }
    
    function deleteUser(id,loginacct) {
        layer.confirm("确定要删除【"+loginacct+"】用户吗？",  {icon: 3, title:'提示'}, function(cindex){
            layer.close(cindex);
            $.ajax({
                type:"post",
                url:"${APP_PATH}/user/doDelete.do",
                data:{
                    "id":id
                },
                success:function (result) {
                    if(result.success){
                        window.location.href='${APP_PATH}/user/toIndex.do';
                    }else{
                        layer.msg(result.message,{time:2000, icon:5, shift:5});
                    }
                }

            });

        }, function(cindex){
            layer.close(cindex);
        });
    }

</script>
</body>
</html>

