<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/22
  Time: 12:39
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
                <li><a href="#">许可树维护</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form id="form">
                        <div class="form-group">
                            <label for="fname">许可名称</label>
                            <input type="text" class="form-control" id="fname" placeholder="请输入许可名称" value="${requestScope.permission.name}">
                        </div>
                        <div class="form-group">
                            <label for="furl">许可地址</label>
                            <input type="text" class="form-control" id="furl" placeholder="请输入许可地址" value="${requestScope.permission.url}">
                        </div>
                        <div class="form-group">
                            <label for="ficon">许可图标</label>
                            <select class="form-control" id="ficon">
                                <option value="">${requestScope.permission.icon}</option>
                            </select>
                        </div>
                        <button type="button" id="updatebtn" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button type="button" class="btn btn-danger" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
<script src="${APP_PATH}/script/common.js"></script>
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

        loadIcon();
    });

    //在页面加载的时候加载icon数据
    function loadIcon() {

        //将icon下拉列表原来的option移除
        $("#ficon>option :gt(0)").remove();

        $.ajax({
            url:"${APP_PATH}/loadIcon.do",
            type:"post",
            dataType: "json",
            success:function (result) {
                if(result.success){
                    $.each(result.datas,function (index,value) {
                        var $dataOption = $("<option></option>");
                        //$dataOption.val(value.id);
                        $dataOption.text(value.iconName);
                        $dataOption.appendTo(ficon);
                    })
                }else{
                    layer.msg(result.message,{time:2000, icon:5, shift:5})
                }

            }


        })
    }

    $("#updatebtn").click(function () {

        //获取表单提交的数据
        var fname = $("#fname");
        var furl = $("#furl");
        var ficon = $("#ficon>option:selected");
        var loadingIndex = -1;

        //异步请求
        $.ajax({
            type:"post",
            url:"${APP_PATH}/permission/doUpdate.do",
            data:{
                id:"${param.id}",
                name:fname.val(),
                url:furl.val(),
                icon:ficon.text()
            },
            //该函数用于在发生ajax请求前的处理，一般用于表单校验、加载进度条等
            beforeSend:function (XMLHttpRequest) {

                //验证许可名称
                if($.trim(fname.val()) == ""){
                    // $("#fmessage").text("用户名不能为空，请重新输入");
                    layer.msg("许可名称不能为空，请重新输入", {time:2000, icon:5, shift:5},function () {
                        fname.val("");
                        fname.focus();
                    })
                    return false
                }

                //验证url地址
                if($.trim(furl.val()) == ""){
                    // $("#fmessage").text("密码不能为空，请重新输入");
                    layer.msg("url地址不能为空，请重新输入", {time:2000, icon:5, shift:5},function () {
                        furl.val("");
                        furl.focus();
                    })
                    return false
                }

                // //验证icon是否未选择
                // if($.trim(ficon.val()) == "请选择"){
                //     // $("#fmessage").text("密码不能为空，请重新输入");
                //     layer.msg("请选择图标", {time:2000, icon:5, shift:5},function () {
                //         ficon.val("");
                //         ficon.focus();
                //     })
                //     return false
                // }

            },
            success:function (result) {
                layer.close(loadingIndex);
                if(result.success==true) {
                    window.location.href='${APP_PATH}/permission/index.do';
                }else{
                    layer.msg(result.message,{time:2000, icon:5, shift:5})
                }
            }

        });

    });

    $("#resetBtn").click(function () {
        $("#form")[0].reset();
    })
</script>
</body>
</html>
