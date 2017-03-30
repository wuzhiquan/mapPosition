var map;
var editor = {};
var marker = new Array();
var district = new Array();

$(function() {
    map = new AMap.Map('map', {
        resizeEnable: true,
        center: [114.049919,22.696843],
        zoom: 13
    });
    map.plugin(["AMap.ToolBar"], function() {
        map.addControl(new AMap.ToolBar());
    });
    initData({position:{lng:114.049919,lat:22.696843}});
    /**map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true, //是否使用高精度定位，默认:true
            timeout: 10000, //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            //zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition: 'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete); //返回定位信息
    });**/
    //setInterval(updateMarker,1000);
    updateMarker();
    $('.amap-toolbar').attr('style','right:10px;top:10px;visibility: visible;');
    window.onresize = function () {
    	console.log('a');
    }
});

function initData(data) {
    var pos = data.position;
    //marker.push({ name: '张三',phone:"13976xxxxdd", lng: pos.lng, lat: pos.lat });
    //marker.push({ name: '李四',phone:"13876xxxxdd", lng: pos.lng, lat: pos.lat - 0.01 });
    district.push({
        name: "大和",
        desc: "999案件     当班：10，轮休：11",
        color:"#00FF00",
        path: [ //构建多边形经纬度坐标数组      
        	[114.046247, 22.69488], [114.047003, 22.697036], [114.047078, 22.699168], [114.047036, 22.700661], [114.046811, 22.702351], [114.042864, 22.703623], [114.043114, 22.70494], [114.045112, 22.704494], [114.047229, 22.704465], [114.046774, 22.70658], [114.047585, 22.708696], [114.04909, 22.712907], [114.052204, 22.712016], [114.053203, 22.711472], [114.054089, 22.710428], [114.054639, 22.709074], [114.055236, 22.707685], [114.054269, 22.704892], [114.054107, 22.703317], [114.053988, 22.701347], [114.055447, 22.696689], [114.055447, 22.692031], [114.054197, 22.686053], [114.049428, 22.683637], [114.045732, 22.684726], [114.045308, 22.693363]
        ]
    });
    district.push({
        name: "观城",
        desc: "999案件     当班：10，轮休：11",
        color:"#FF0000",
        path: [ //构建多边形经纬度坐标数组      
        	[114.046247, 22.69488], [114.047003, 22.697036], [114.047078, 22.699168], [114.047036, 22.700661], [114.046811, 22.702351], [114.042864, 22.703623], [114.043114, 22.70494], [114.045112, 22.704494], [114.047229, 22.704465], [114.046774, 22.70658], [114.047585, 22.708696], [114.04909, 22.712907], [114.05126, 22.713758], [114.052817, 22.714282], [114.055248, 22.715297], [114.057171, 22.717467], [114.059186, 22.719147], [114.062532, 22.720907], [114.059762, 22.715066], [114.058785, 22.712344], [114.059309, 22.708235], [114.061391, 22.706105], [114.063472, 22.702311], [114.065489, 22.700101], [114.070124, 22.689656], [114.065928, 22.688537], [114.055122, 22.689675], [114.054197, 22.686053], [114.049428, 22.683637], [114.045732, 22.684726], [114.045308, 22.693363]
        ]
    });
    district.push({
        name: "松园厦",
        desc: "999案件     当班：10，轮休：11",
        color:"#00FFFF",
        path: [ //构建多边形经纬度坐标数组      
        	[114.063386,22.707198],[114.063493,22.709615],[114.06006,22.713936],[114.062048,22.720972],[114.063735,22.721596],[114.06904,22.725772],[114.074158,22.721537],[114.083728,22.713851],[114.073944,22.70767],[114.065424,22.705294]
        	]
    });
    district.push({
        name: "新田",
        desc: "999案件     当班：10，轮休：11",
        color:"#FFFF00",
        path: [ //构建多边形经纬度坐标数组      
        	[114.076668,22.703287],[114.073471,22.707246],[114.082237,22.714751],[114.092719,22.710816],[114.094371,22.702153],[114.09332,22.699428],[114.085842,22.693433],[114.082269,22.695991],[114.079243,22.69896]
        	]
    });
    district.push({
        name: "樟坑径",
        desc: "999案件     当班：10，轮休：11",
        color:"#0000FF",
        path: [ //构建多边形经纬度坐标数组      
        	[114.060618,22.680502],[114.071132,22.689958],[114.065446,22.704531],[114.074179,22.705247],[114.079114,22.698285],[114.077312,22.690538],[114.086678,22.693468],[114.094843,22.698259],[114.101216,22.67899],[114.093513,22.664788],[114.069115,22.675084]
        	]
    });
    district.push({
        name: "大布头",
        desc: "999案件     当班：10，轮休：11",
        color:"#FF00FF",
        path: [ //构建多边形经纬度坐标数组      
        	[114.063386,22.707198],[114.063364,22.709694],[114.06006,22.713936],[114.062048,22.720972],[114.063735,22.721596],[114.066808,22.719518],[114.067463,22.717697],[114.068064,22.716385],[114.06785,22.714798],[114.06682,22.712975],[114.065295,22.705254]
        	]
    });
    
    drawMarker(marker);
    putMarker(marker);
    drawDistrict(district);
}

function updateMarker()
{
    $.ajax({
//            url : "http://swan.dev.krrj.cn/wxtest/location.htm",
    		url: '/wxtest/list.htm',
            dataType : "json",
            type : "get",
            success : function(data) {
//                $.each(data,function(key,value){
//                    var hasName = false;
//                    $.each(marker,function(){
//                        if(this.name == key)
//                        {
//                            hasName = true;
//                            var tmp = value.split(",");
//                            this.lng = tmp[0];
//                            this.lat = tmp[1];
//                            refreshMarker(this);
//                        }
//                    });
//                    if(!hasName)
//                    {
//                        var tmp = value.split(",");
//                            this.lng = tmp[0];
//                            this.lat = tmp[1];
//                        var item = {name:key,lng:tmp[0],lat:tmp[1]};
//                        marker.push(item);
//                        refreshMarker(item);
//
//                    }
//                });
            	data.list.forEach(function(item){
            		marker = marker.filter(data => data.name != item.name)
            		marker.push({name: item.name,phone:item.phone, lng: item.lng, lat: item.lat});
            		refreshMarker(item);
            	});
                putMarker(marker);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
            }
        });
}

function putMarker(marker)
{
    var userItem = $(".areaList ul");
     $.each(marker, function(index, item) {
    	 console.log(item)
        var tmp = $("<li><span>"+item.name+"</span><button>定位</button></li>");
        tmp.find('button').on("click",function(){
            map.setCenter([item.lng,item.lat]);
        });
        userItem.append(tmp);
     });

}

var cur ;
function drawDistrict(district)
{
    $.each(district, function(index, item) {
        item.polygon = new AMap.Polygon({
            map: map,
            path: item.path,
            strokeColor: item.color,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: "#f5deb3",
            fillOpacity: 0.35
        });

        item.polygon.on("click",function(){
            cur = item;
            item.editor.open();
            //$("#info").text(item.name+" "+item.desc);
        });
        item.editor = new AMap.PolyEditor(map, item.polygon);
    });
}

function drawMarker(marker) {
    $.each(marker, function(index, item) {
        refreshMarker(item);
    });
}

function refreshMarker(item)
{
    if(item.marker != null)
    {
        item.marker.setPosition([item.lng, item.lat]);
        return;
    }
    var mar = new AMap.Marker({
            position: [item.lng, item.lat],
            draggable: true,
            cursor: 'move',
            raiseOnDrag: true
        });
        mar.setLabel({offset: new AMap.Pixel(-30, 40),content:"姓名是："+item.name});
        mar.setMap(map);
        mar.on("click",function(e){
        		prompt("点座标",e.lnglat);
//            var contextMenu = new AMap.ContextMenu();  //创建右键菜单
//            contextMenu.addItem("打电话"+item.phone, function() {
//                alert("正在拨号："+item.phone);
//            }, 0);
//            contextMenu.addItem("发信息"+item.phone, function() {
//                alert("正在给号码"+item.phone+"发短信");
//            }, 1);
//            contextMenu.open(map, e.lnglat);
        });
    item.marker = mar;
}

function districtInfo()
{
    if(cur)
    {
        cur.editor.close();
        prompt("路径数据",cur.polygon.getPath());
    }
}