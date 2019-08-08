function showMenu(){
    //获取a标签的路径
    var currentRequestPath = $(this).attr("href");
    var hrefPath = window.location.href;
    if(hrefPath.indexOf(currentRequestPath)){
        $(this).css("color","red");
        $(this).parents().parents().parents().removeClass("tree-closed");
        $(this).parents().parents().show();
    }

}