var map;
var editor = {};
var persons = {};
var cases = {};
var marker = new Array();
var track;
var district = new Array();
$(function() {
	// 初始化地图相关参数
	map = new AMap.Map('map', {
		resizeEnable : true,
		center : [ 114.049919, 22.696843 ],
		zoom : 14,
	});
	map.on("complete", function() {
		initData({
			position : {
				lng : 114.049919,
				lat : 22.696843
			}
		});
		setInterval(updateMarker, 10000);
		// 加载事件
		loadCase();
	});
	// 输入提示
	var searchMap = AMap.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
      var autoOptions = {
        city: "北京", //城市，默认全国
        input: "keyword"//使用联想输入的input的id
      };
      autocomplete= new AMap.Autocomplete(autoOptions);
      var placeSearch = new AMap.PlaceSearch({
            city:'北京',
            map:map
      })
      // 点击下拉地点后生成maker
      AMap.event.addListener(autocomplete, "select", function(e){
         //TODO 针对选中的poi实现自己的功能
         placeSearch.search(e.poi.name);
         console.log(e);
         console.log('纬度：'+e.poi.location.lat+',经度：'+e.poi.location.lng);
         var newMaker = new AMap.Marker({
             icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_bs.png",
             position: [e.poi.location.lng, e.poi.location.lat],
             draggable : true,
     		 cursor : 'move',
     		 raiseOnDrag : true
         });
         var district = e.poi.district;
         var address = e.poi.address;
         var name = e.poi.name;
         newMaker.setMap(map);
         newMaker.on("click", function(e) {
        	 console.log(e);
     		var contextMenu = new AMap.ContextMenu(); // 创建右键菜单
     		contextMenu.addItem("查看详情", function() {
     			var detail = dialog({
     				title: name,
				    content: '<p>详细地址：'+district+'&nbsp;&nbsp;'+address+'</p>',
				    width:400
				});
     			detail.showModal();
     		}, 0);
     		contextMenu.addItem("创建案件", function() {
     			$("#caseMask").append("<input id='caselng' type='hidden' value='"+e.lnglat.lng+"'/>");
     			$("#caseMask").append("<input id='caselat' type='hidden' value='"+e.lnglat.lat+"'/>");
     			$("#caseMask").append("<input id='caseAddress' type='hidden' value='"+district+"&nbsp;&nbsp;"+address+"'/>");
     			$("#showCaseImg").append("<span id='showWord'>图片预览区域</span>");
     			$("#caseMask").show();
     		}, 0);
     		contextMenu.open(map, e.lnglat);
     	});
      });
    });
	/*map.plugin([ "AMap.ToolBar" ], function() {
		map.addControl(new AMap.ToolBar());
	});*/
	// 关闭谈话框
	$("#closeMask").click(function() {
		$("#mask").hide();
		$("#inputid").remove();
		$("#showImg").empty();
		$("#file").val('');
		$("#msgTextarea").val('');
	});
	$("#closeCaseMask").click(function() {
		$("#caseMask").hide();
		$("#showCaseImg").empty();
		$("#casefile").val('');
		$("#caseName").val('');
		$("#caseTextarea").val('');
		$("#caselng").remove();
		$("#caselat").remove();
		$("#caseAddress").remove();
		$("#showWord").remove();
	});
});

function initData(data) {
	var pos = data.position;
	commitData(contentPath+"/range.htm", {}, {
		success : function(area) {
			$.each(area.data, function() {
				var p = new Array();
				p.push(eval("[" + this.position + "]"));
				var area = {
					name : this.name,
					id : this.id,
					plantpeople : this.plantpeople,
					color : this.bordercolor,
					path : p,
					peoples : 0
				};
				district.push(area);
				addDistrict(area);
			});
			initEvent();
			drawDistrict(district);
		}
	});
	updateMarker();
}

function initEvent() {
	var showPart1 = false;
	var showPart2 = false;
	// 显示隐藏第一块内容
	$('.part1_icon').click(function() {
		showPart1 = !showPart1;
		if (showPart1) {
			$('.block_part1 .content').slideDown(200);
		} else {
			$('.block_part1 .content').slideUp(200);
		}
	});
	// 显示隐藏第二块内容
	$('.part2_icon').click(function() {
		showPart2 = !showPart2;
		if (showPart2) {
			$('.block_part2 .content').slideDown(200);
		} else {
			$('.block_part2 .content').slideUp(200);
		}
	});
	// 切换列表
	$('.block-bar span').click(function() {
		if ($(this).hasClass('staffbar')) {
			$('.areaWrap').hide();
			$('.staffWrap').show();
			$('.block_part1 i').attr('style', 'border-bottom-color:#fff');
		} else {
			$('.staffWrap').hide();
			$('.areaWrap').show();
			$('.block_part1 i').attr('style', 'border-bottom-color:#ececec');
		}
		$('.block-bar span').removeClass('active');
		$(this).addClass('active');
	});
	// 点击区域获取列表
	$('.staffInfo').click(function() {
		$('.staffWrap ul').slideUp(200);
		if ($(this).hasClass('active')) {
			$('.staffInfo').removeClass('active');
		} else {
			$('.staffInfo').removeClass('active');
			$(this).addClass('active');
			$(this).next().slideDown(200);
		}
	});
	// 切换列表
	$('.block-bar span').click(function() {
		if ($(this).hasClass('staffbar')) {
			$('.areaWrap').hide();
			$('.staffWrap').show();
		} else {
			$('.staffWrap').hide();
			$('.areaWrap').show();
		}
		$('.block-bar span').removeClass('active');
		$(this).addClass('active');
	});
	// 点击区域获取列表
	$('.staffInfo').click(function() {
		$('.staffWrap ul').hide();
		$('.staffInfo').removeClass('active');
		$(this).addClass('active');
		$(this).next().show();
	});
}
/**
 * 更新工作人员
 * 
 * @returns
 */
function updateMarker() {
	$.ajax({
		url : contentPath+'/list.htm',
		dataType : "json",
		type : "get",
		success : function(data) {
			$.each(data.list, function(index, item) {
				if (persons["id" + item.id]) {
					persons["id" + item.id].lng = item.lng;
					persons["id" + item.id].lat = item.lat;
				} else
					persons["id" + item.id] = {
						id : item.id,
						rangeid : item.rangeid,
						name : item.name,
						phone : item.phone,
						lng : item.lng,
						lat : item.lat
					};
				refreshMarker(persons["id" + item.id]);
			});
			putMarker(persons);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}
var once = true;
/**
 * 在菜单上显示人
 * 
 * @param persons
 * @returns
 */
function putMarker(persons) {
	// console.log(persons)
	if (once)
		once = false;
	else
		return;
	$.each(persons, function(key, value) {
		var ul = $("#areaperson" + value.rangeid);
		if (ul.length == 0) {
			ul = $("<ul>");
			ul.attr("id", "areaperson" + value.rangeid);
			$("#area" + value.rangeid).after(ul);
		}
		var tmp = $("<li><span>" + value.name + "</span></li>");
		tmp.on("click", function() {
			map.setCenter([ value.lng, value.lat ]);
		});
		ul.append(tmp);
		// 区域概要的人数加1
		var peoples = Number($("#peoples" + value.rangeid).text()) + 1;
		$("#peoples" + value.rangeid).text(peoples);
		$("#_peoples" + value.rangeid).text(peoples);
	});
}

var cur;
/**
 * 在地图上画各区
 * 
 * @param district
 * @returns
 */
function drawDistrict(district) {
	$.each(district, function(index, item) {
		item.polygon = new AMap.Polygon({
			map : map,
			path : item.path,
			strokeColor : item.color,
			strokeOpacity : 1,
			strokeWeight : 2,
			fillColor : "#f5deb3",
			fillOpacity : 0.2
		});
		item.polygon.on("click", function() {
			// $("#info").text(item.name+" "+item.desc);
		});
	});
}
/**
 * 有点的时候就更新位置，没的时候就往地图加点
 * 
 * @param item
 * @returns
 */
function refreshMarker(item) {
	if (item.marker != null) {
		item.marker.setPosition([ item.lng, item.lat ]);
		$.each(district, function() {
			if (this.id == item.rangeid)// 找到相对应的区域
			{
				if (this.polygon) {
					if (this.polygon.contains([ item.lng, item.lat ])) {
						item.marker.setIcon(null);
						item.marker.setAnimation('AMAP_ANIMATION_NONE');
					} else {
						item.marker.setIcon(contentPath+"/images/red.png");
						//item.marker.setAnimation('AMAP_ANIMATION_BOUNCE');
					}
				}
			}
		});
		return;
	}
	var mar = new AMap.Marker({
		position : [ item.lng, item.lat ],
		draggable : true,
		cursor : 'move',
		raiseOnDrag : true
	});
	mar.setLabel({
		offset : new AMap.Pixel(-30, 40),
		content : item.name
	});
	mar.setMap(map);
	mar.on("click", function(e) {
		var contextMenu = new AMap.ContextMenu(); // 创建右键菜单
		contextMenu.addItem("手机号码：" + item.phone, function() {
		}, 0);
		contextMenu.addItem("发送图文消息", function() {
			var idInput = "<input type='hidden' value='"+item.id+"' name='userid' id='inputid'/>";
			$("#mask").append(idInput);
			$("#mask").show(msghistory(item.id));
			$("#userName").text(item.name);
			$("#msgTextarea").val("");
			$("#upfile").val("");
		}, 0);
		contextMenu.addItem("显示路径", function() {
			commitData(contentPath+"/track.htm", {
				userid : item.id
			}, {
				success : function(data) {
					if (data.code == 0) {
						track && track.setMap(null);
						var lineArr = new Array();
						$.each(data.data, function() {
							lineArr.push([ this.lng, this.lat ]);
						});
						var polyline = new AMap.Polyline({
							path : lineArr, // 设置线覆盖物路径
							strokeColor : "#3366FF", // 线颜色
							strokeOpacity : 1, // 线透明度
							strokeWeight : 2, // 线宽
							strokeStyle : "solid", // 线样式
							strokeDasharray : [ 10, 5 ]
						// 补充线样式
						});
						polyline.setMap(map);
						track = polyline;
					}
				}
			});
		}, 1);
		contextMenu.open(map, e.lnglat);
	});
	item.marker = mar;
}
/**
 * 更新人员列表中的区域
 * 
 * @param dist
 * @returns
 */
function addDistrict(dist) {
	var district = $("#person");
	var area = $("<div>");
	area.addClass("staffInfo");
	area.attr("id", "area" + dist.id);
	area.append("<span class=\"name\"><em class=\"positionIcon\"></em>" + dist.name + "</span>");
	area.append("<span class=\"num\"><em class=\"peopleIcon\"></em><span id=\"_peoples" + dist.id + "\">0</span>/" + dist.plantpeople
			+ "人<em class=\"directionIcon\"></em></span>");
	district.append(area);

	district = $("#essentials");
	area = $("<div>");
	area.addClass("areaInfo");
	area.append("<p class=\"title\">" + dist.name + "</p>");
	area.append("<p class=\"event\"><span><em></em>事件</span><span class=\"right\">10件</span></p>");
	area.append("<p class=\"plan\"><span><em></em>计划到岗人数</span><span class=\"right\">" + dist.plantpeople + "人</span></p>");
	area.append("<p class=\"actual\"><span><em></em>实际到岗人数</span><span id=\"peoples" + dist.id + "\" class=\"right\">" + 0 + "</span></p>");
	district.append(area);
}
/**
 * 加载事件，并在地图上显示
 * 
 * @returns
 */
function loadCase() {
	commitData(contentPath+"/case.htm", {}, {
		success : function(data) {
			if (data.code == 0) {
				$.each(data.data, function(index, item) {
					if (cases["case" + item.id] == null) {
						cases["case" + item.id] = item;
						var mar = new AMap.Marker({
							map : map,
							offset : new AMap.Pixel(-21, -64),
							position : [ item.lng, item.lat ],
							draggable : true,
							raiseOnDrag : true,
							icon : contentPath+"/images/redthing.png"
						});
						mar.setLabel({
							offset : new AMap.Pixel(0, 64),
							content : item.name
						});
						mar.on("click", function(e) {
							var contextMenu = new AMap.ContextMenu();
							contextMenu.addItem("案件描述", function() {
								if(item.picid){
									var content = "<p>"+item.description+"</p><img src='"+contentPath+"/file/get.htm?id="+item.picid+"' style='max-width:350px;max-height:400px;display:block;margin:0 auto;'/>";
								}else{
									var content = item.description;
								}
								var d = dialog({
								    title: '案件描述',
								    content: content,
								    width:400
								});
								d.showModal();
							}, 0);
							contextMenu.addItem("案件地址", function() {
								var d = dialog({
								    title: '地址',
								    content: item.address,
								    width:400
								});
								d.showModal();
							}, 0);
							contextMenu.addItem("案件指派", function() {
								console.log(item);
								var d = dialog({
								    title: '指派人',
								    content: "输入指派人：<input id='zhipairen'/>",
								    width:400,
								    okValue: '指派',
								    ok: function () {
								        commitData(contentPath+"/getPersonByName.htm",{name: $("#zhipairen").val()},{success:function(temp){
											console.log(temp);
											var picurl = item.picid ? contentPath + '/file/get.htm?id='+item.picid : '';
											// 添加案件处理信息
											commitData(contentPath+"/addCaseHandel.htm",{
												userid: temp.data.id,
												caseid: item.id,
												remark: '备注',
												status: 0
											},{success:function(_msg) {
												console.log(_msg);
												var data = {
													id: temp.data.id,
													title: item.name,
													description: item.description,
													picUrl: contentPath + '/viewcaseinfo.htm?id='+_msg.data,
													url: picurl
												}
												if(_msg.code == 0) {
													// 发送消息
													commitData(contentPath+"/sendCaseMessage.htm",data,{success:function(msg) {
														console.log(msg);
													}});
												}
											}});
										}});
								    }
								});
								d.showModal();
							}, 0);
							contextMenu.open(map, e.lnglat);
						});
						cases["case" + item.id].marker = mar;
					}
				});
			}
		}
	});
}
/*显示信息*/
function getMsg(){
	$('.block_part2 ul').empty();
	commitData(contentPath+"/WXmessageList.htm",{},{success: function (data) {
		var count = 0;
		data.list.forEach(function(item){
			if(item.status == 0) {
				count++;
			}
			var style = (item.status == 0 ? "nosee" : '');
			var content = '';
			switch(item.msgType){
				case 'text':
					content = item.content;
					break;
				case 'image':
					content = '[图片文件，点击查看]';
					break;
				case 'location':
					content = '[定位信息，点击查看]';
					break;
				case 'case':
					content = item.content.split('/')[1] ?item.content.split('/')[1]:item.content.split('/')[0];
					break;
			}
			var li = $('<li data-id="'+item.id+'"><span class="msg '+ style +'">'+item.name+'：'+content+'</span><span class="time">'+item.createTime.substring(5,16)+'</span></li>');
			li.on("click", function() {
				$(this).find('span.msg').removeClass('nosee');
				var id = $(this).data('id');
				if(item.msgType == 'text'){
					var d = dialog({
					    title: '信息',
					    content: item.content,
					    width:400
					});
					d.showModal();
				}
				if(item.msgType == 'image'){
					var image = new Image();
					image.src = contentPath+'/file/get.htm?id='+item.picUrl;
					image.onload = function () {
						var d = dialog({
						    title: '信息',
						    content: '<img src="'+contentPath+'/file/get.htm?id='+item.picUrl+'" style="margin: 0 auto;display: block;max-width:100%;max-height:500px;"/>',
						    width:600
						});
						d.showModal();
					}
				}
				if(item.msgType == 'location'){
					var d = dialog({
					    title: '信息',
					    content: item.label,
					    width:400,
					    okValue: '定位',
					    ok: function () {
					        map.setCenter([ item.location_Y, item.location_X ]);
					        $(".block_part2 .content").hide();
					    }
					});
					d.showModal();
				}
				if(item.msgType == 'case'){
					console.log(item);
					var arr = item.content.split('/');
					console.log(arr);
					if(arr[1]){
						commitData(contentPath+"/getCaseById.htm",{id:arr[0]},{success:function(o){
							console.log(o);
							if(o.data.status == 2){
								var d = dialog({
								    title: '案件处理',
								    content: arr[1],
								    width:400,
								    okValue: '已完成',
								    ok: function () {
								    }
								});
								d.showModal();
							}else {
								var d = dialog({
								    title: '案件处理',
								    content: arr[1],
								    width:400,
								    okValue: '确定完成',
								    ok: function () {
								    	commitData(contentPath+"/updateCaseStatus.htm",{id:arr[0],status: 2},{success:function(item1){
											console.log(item1);
											commitData(contentPath+"/sendMessage.htm",{
												content: '你提交的“'+arr[1]+'”审批完成',
												id: item.userid
											},{
												success: function (data) {
													console.log(data);
													if(data.code == 0) {
														commitData(contentPath+"/addWXmessageSend.htm",{
															content: '你提交的“'+arr[1]+'”审批完成',
															userid: item.userid,
															msgType:"text"
														},{success:function (temp){
															console.log(temp);
															window.location.reload();
														}})
													}
												}
											})
										}})
								    }
								});
								d.showModal();
							}

							
						}})
					}
					else{
						var d = dialog({
						    title: '案件处理',
						    content: arr[0],
						    width:400
						});
						d.showModal();
					}
				}
				// 更改信息查看状态
				commitData("/updateWXmessageStatus.htm",{id: id, status: 1},{success:function(data) {
					console.log(data);
				}});
			});
			$('.block_part2 ul').append(li);
		});
		$(".part2_icon em").text(count);
	}});
}
getMsg();
// 十秒钟拉取一次信息
setInterval(getMsg, 10000);