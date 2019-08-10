<%--
  Created by IntelliJ IDEA.
  User: McChakLeung
  Date: 2019/8/9
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
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
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="leftRoleList">未分配角色列表</label><br>
                            <select id="leftRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${unAssignList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li class="btn btn-default glyphicon glyphicon-chevron-right" id="leftRoleBtn"></li>
                                <br>
                                <li class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;" id="rightRoleBtn"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="rightRoleList">已分配角色列表</label><br>
                            <select id="rightRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${assignList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script type="text/javascript" src="${APP_PATH}/script/common.js"></script>
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
        $(".list_href").click(function () {
            //var currentRequestPath = $(this).attr("href");
            //alert(currentRequestPath)
            showMenu()
        })

        var loadingIndex = -1;

        //定义传入到后台的json类型参数，其中包含userid和roleid
        <%--var jsonObj = {--%>
            <%--userid: "${param.id}"--%>
        <%--}--%>

        var jsonObj = new Array();

        //为左边select添加双击事件，将单个option添加到右边的select中
        $("#leftRoleList").delegate("option","dblclick",function () {
            var leftOption = $(this);

            jsonObj.push({userid:"${param.id}", roleid: leftOption.val()})

            //发送ajax添加功能
            $.ajax({
                type:"post",
                data:JSON.stringify(jsonObj),
                url:"${APP_PATH}/user/doAssignRole.do",
                datatype:'json',
                contentType : "application/json;charset=utf-8",
                beforSend: function(){
                    loadingIndex = layer.load(3, {time: 10*1000});
                    return false;
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if(result.success){
                        $("#rightRoleList").append(leftOption);
                    }else{
                        layer.msg(result.message,{time:2000, icon:5, shift:5})
                    }
                },
                error:function () {
                    layer.msg("分配角色失败，请联系管理员处理",{time:2000, icon:5, shift:5})
                }
            })

        })

        //leftRoleBtn单击事件
        $("#leftRoleBtn").click(function () {
            var leftRoleList = $("#leftRoleList option:selected");

            //遍历leftRoleListDOM对象，并将其中的roleid组装到jsonObj中
            // $.each(leftRoleList,function (i,n) {
            //     jsonObj["ids["+i+"]"] = this.value;
            // })
            //console.log(jsonObj)

            //遍历leftRoleListDOM对象，并将其中的roleid组装到jsonObj中
            $.each(leftRoleList,function (i,n) {
                jsonObj.push({userid: "${param.id}", roleid: this.value})
            })


            //发送ajax添加功能
            $.ajax({
                type:"post",
                data:JSON.stringify(jsonObj),
                url:"${APP_PATH}/user/doAssignRole.do",
                datatype:'json',
                contentType : "application/json;charset=utf-8",
                beforSend: function(){
                    loadingIndex = layer.load(3, {time: 10*1000});
                    return false;
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if(result.success){
                        $("#rightRoleList").append(leftRoleList);
                    }else{
                        layer.msg(result.message,{time:2000, icon:5, shift:5})
                    }
                },
                error:function () {
                    layer.msg("分配角色失败，请联系管理员处理",{time:2000, icon:5, shift:5})
                }
            })
        })

        //为右边select添加双击事件，将单个option添加到左边的select中
        $("#rightRoleList").delegate("option","dblclick",function () {
            var rightOption = $(this);

            jsonObj.push({userid:"${param.id}", roleid: rightOption.val()})

            //发送ajax添加功能
            $.ajax({
                type:"post",
                data:JSON.stringify(jsonObj),
                url:"${APP_PATH}/user/doUnAssignRole.do",
                datatype:'json',
                contentType : "application/json;charset=utf-8",
                beforSend: function(){
                    loadingIndex = layer.load(3, {time: 10*1000});
                    return false;
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if(result.success){
                        $("#leftRoleList").append(rightOption);
                    }else{
                        layer.msg(result.message,{time:2000, icon:5, shift:5})
                    }
                },
                error:function () {
                    layer.msg("分配角色失败，请联系管理员处理",{time:2000, icon:5, shift:5})
                }
            })

        })

        //rightRoleBtn单击事件
        $("#rightRoleBtn").click(function () {
            var rightRoleList = $("#rightRoleList option:selected");
            //遍历leftRoleListDOM对象，并将其中的roleid组装到jsonObj中
            $.each(rightRoleList,function (i,n) {
                jsonObj.push({userid: "${param.id}", roleid: this.value})
            })
            console.log(jsonObj)

            //发送ajax添加功能
            $.ajax({
                type:"post",
                data:JSON.stringify(jsonObj),
                url:"${APP_PATH}/user/doUnAssignRole.do",
                datatype:'json',
                contentType : "application/json;charset=utf-8",
                beforSend: function(){
                    loadingIndex = layer.load(3, {time: 10*1000});
                    return false;
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if(result.success){
                        $("#leftRoleList").append(rightRoleList);
                    }else{
                        layer.msg(result.message,{time:2000, icon:5, shift:5})
                    }
                },
                error:function () {
                    layer.msg("删除角色失败，请联系管理员处理",{time:2000, icon:5, shift:5})
                }
            })
        })
    });
</script>
</body>
</html>

