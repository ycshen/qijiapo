<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>部门树</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@include file="../share/common_css.jsp"%>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/js/zTree/css/metroStyle/metroStyle.css" type="text/css">

<%@include file="../share/common_js.jsp"%>
<script src="${ctx}/js/adminlte/dist/js/app.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=nKLKhHpjMauRWU84zsIprGYj"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    .qjp_oper{
        position:fixed;
        top: 0;
        left: 0;
        background-color:#F8F8F8;
        width: 100%;
        z-index: 999;
        text-align: center;
        padding-right:30px;
        padding-left: 30px;
    }
</style>
</head>
<body>
<div class="qjp_oper">
    <input placeholder="请输入关键字" class="layui-input" id="suggestId"/>
    当前定位：<span id="spanAddr">定位失败,请输入关键字重新定位</span>
    <button class="layui-btn layui-btn-small" style="margin-top: 5px;" onclick="setLocaltion();">设为考勤点</button>
</div>
<div id="allmap"></div>
<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
<input type="hidden" id="longitude" value=""/>
<input type="hidden" id="latitude" value=""/>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(116.331398,39.897445);
    map.centerAndZoom(point,12);
    map.enableScrollWheelZoom(true);
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.setZoom(14);
            map.addOverlay(mk);
            map.panTo(r.point);
            var geoc = new BMap.Geocoder();
            geoc.getLocation(r.point, function(rs){
                var addComp = rs.addressComponents;
                var address = addComp.province + addComp.district + addComp.street + addComp.streetNumber;
                $("#spanAddr").html(address);
            });

        }
        else {
            //alert('failed'+this.getStatus());
        }
    },{enableHighAccuracy: true})

    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {"input" : "suggestId"
                ,"location" : map
            });
    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        $("#spanAddr").html(myValue);
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

        setPlace();
    });

    function setPlace(){
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            alert(pp)
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }
    function G(id) {
        return document.getElementById(id);
    }

    function setLocaltion(){
        var addr = $("#spanAddr").html();
        if("定位失败,请输入关键字重新定位" == addr){
            layer.alert("定位失败,请输入关键字重新定位",{closeBtn: false,
                skin: 'layui-layer-molv'
            });
        }else{
            parent.getLocation(addr, 0, 0);
            parent.layer.closeAll();
        }
    }
</script>