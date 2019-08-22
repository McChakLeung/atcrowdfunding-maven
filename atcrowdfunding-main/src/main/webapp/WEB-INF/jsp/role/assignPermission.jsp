<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/21
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
    <link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限分配列表<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <button class="btn btn-success" id="assignPermissionBtn">分配许可</button>
                    <br><br>
                    <ul id="treeDemo" class="ztree"></ul>
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
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
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
<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script type="text/javascript">
    var setting = {
        check : {
            enable : true
        },
        view: {
            selectedMulti: false,
            addDiyDom: function(treeId, treeNode){
                var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                aObj.attr("href", "javascript:;");
                var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                if ( treeNode.icon ) {
                    icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
                }
            },
        },
        async: {
            enable: true,
            url:"${APP_PATH}/role/asyncLoadData.do",
            autoParam:["id", "name", "level"],
            otherParam:["roleId",${param.roleId}],
            dataFilter:filter
        }
    };

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


        //$.fn.zTree.init($("#treeDemo"), setting); //异步访问数据
        $.fn.zTree.init($("#treeDemo"), setting);
    });

    //过滤异步加载ztree时返回的数据
    function filter(treeId, parentNode, childNodes) {
        //if (!childNodes)
        //    return null;
        //childNodes = eval(childNodes); //必须转换为[{id:103,pId:1,name:'子节点3'}];这样的格式
        return childNodes.datas;
    }

    var loadingIndex = -1;

    $("#assignPermissionBtn").click(function () {

        //定义一个json，接收roleid以及选中的节点ID
        var jsonObj = {
            roleId:${param.roleId},
        }

        //获取树对象
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");

        //获取许可树中节点属性为check的节点id
        var checkedNode = treeObj.getCheckedNodes(true);

        //遍历获取许可树中节点属性为check的节点id，并组装到jsonObj中
        $.each(checkedNode,function (i,n) {
                jsonObj["ids["+i+"]"] = n.id;
        })

        layer.confirm("确定要分配这些权限给该用户吗？",  {icon: 3, title:'提示'}, function(cindex){
            layer.close(cindex);

            //发起异步请求
            $.ajax({
                type:"post",
                url:"${APP_PATH}/role/doAssignPermission.do",
                data:jsonObj,
                beforeSend:function () {
                    loadingIndex = layer.load(3, {time: 10*1000});
                    return true
                },
                success:function (result) {
                    if(result.success){
                        layer.close(loadingIndex);
                        $.fn.zTree.init($("#treeDemo"), setting);
                    }else{
                        layer.msg(result.message,{time:2000, icon:5, shift:5})
                    }
                }
            })
        }, function(cindex){
            layer.close(cindex);
        });


    })

</script>
</body>
</html>

