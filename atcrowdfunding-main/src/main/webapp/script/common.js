function showMenu(){
    //获取完整url路径
    var currentRequestPath = window.location.href;
    //获取主机和ip地址
    var host = window.location.host;
    //截取contextPath
    var contextPath = currentRequestPath.substring(7 + host.length)

}