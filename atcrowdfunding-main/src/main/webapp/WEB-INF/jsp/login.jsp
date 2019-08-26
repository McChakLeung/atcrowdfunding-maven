<%--
  Created by IntelliJ IDEA.
  User: McChakLeung
  Date: 2019/7/8
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form action="" method="post" id="loginForm" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="floginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="fuserpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
        <div id="loginType" style="display: none" class="form-group">
            <label for="ftype">请选择登陆角色：</label>
            <select class="form-control" id="ftype" name="type">
                <option value="0">请选择</option>
            </select>
        </div>

    </form>


</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script>
    function dologin() {

        //获取表单提交的数据
        var floginacct = $("#floginacct");
        var fuserpswd = $("#fuserpswd");
        // var ftype = $("#ftype");
        var loadingIndex = -1;

        $("#ftype>option:gt(0)").remove();

        //异步请求
        $.ajax({
            type:"post",
            url:"${APP_PATH}/doLogin.do",
            data:{
                loginacct:floginacct.val(),
                userpswd:fuserpswd.val(),
                //type:ftype.val()
            },
            //该函数用于在发生ajax请求前的处理，一般用于表单校验、加载进度条等
            beforeSend:function (XMLHttpRequest) {
                //验证用户名
                if($.trim(floginacct.val()) == ""){
                    // $("#fmessage").text("用户名不能为空，请重新输入");
                    layer.msg("用户名不能为空，请重新输入", {time:2000, icon:5, shift:5},function () {
                        floginacct.val("");
                        floginacct.focus();
                    })

                    return false
                }

                //验证密码
                if($.trim(fuserpswd.val()) == ""){
                    // $("#fmessage").text("密码不能为空，请重新输入");
                    layer.msg("密码不能为空，请重新输入", {time:2000, icon:5, shift:5},function () {
                        fuserpswd.val("");
                        fuserpswd.focus();
                    })
                    return false
                }
                loadingIndex = layer.load(3, {time: 10*1000});
            },
            success:function (result) {
                layer.close(loadingIndex);
                if(result.success==true) {
                    $.each(result.datas,function (i,n) {
                        var $optionContent = $("<option></option>");
                        $optionContent.text(n.name);
                        $optionContent.val(n.id);
                        $optionContent.appendTo($("#ftype"));
                    })
                    layer.open({
                        type: 1,
                        title:"当前登陆账号为：" + "${sessionScope.user.username}",
                        area: ['350px', '200px'],
                        content: $("#loginType"),
                        btn: ['确定'],
                        yes:function () {
                            if($("#ftype").val()==0){
                                layer.msg("角色未选择，请重新选择", {time:2000, icon:5, shift:5})
                                return false
                            }
                            window.location.href="${APP_PATH}/main.htm"
                        },
                        cancel:function (index, layero) {
                            layer.close(index)
                        }
                    });
                }else{
                    layer.msg(result.message,{time:2000, icon:5, shift:5})
                }
            }
        });
    }

</script>
</body>
</html>