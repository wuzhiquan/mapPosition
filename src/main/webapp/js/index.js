function indexMenu(opt) {
	this.opt = opt;
	this.update = function() {
		var root = $(".nav-menu");
		root.children().remove();
		this.each(opt.data, root, this);
		root.children("li").each(function(){
			$(this).children("a").on("click",function(event){
				$(this).parent().find("em").toggleClass("icon-angle-down").toggleClass("icon-angle-right");
				$(this).parent().find(".sub-menu").toggle();
				var env = event || window.event;
			});
		});
	};
	this.each = function(data, root, self) {
		$.each(data,function() {
			var li = $("<li>");
			var link = $("<a>");
			link.attr("href", this.link || "#");
			link.append("<i class=\"" + (this.icon || "icon-menu")	+ "\"></i>");
			link.append(this.text);
			li.append(link);
			if (this.sub) {
				link.append("<em class=\"icon-angle-right\"></em>");
				var subMenu = $("<ul class=\"sub-menu\"></ul>");
				li.append(subMenu);
				self.each(this.sub, subMenu);
			}
			root.append(li);
		});
	};
}

$(function() {
	var opt = new Object();
	opt.data = [ {
		text : "历史"
	}, {
		text : "外语",
		icon : "icon-clipboard",
		link : "http://www.baidu.com",
		sub : [ {
			text : "英语",
			icon : "icon-circle-thin"
		},{
			text : "俄语",
		},{
			text : "日语",
			icon : "icon-circle-thin"
		},{
			text : "韩语",
			icon : "icon-circle-thin"
		} ]
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "物理",
		link : "http://www.baidu.com"
	}, {
		text : "外语",
		icon : "icon-clipboard",
		sub : [ {
			text : "英语",
			icon : "icon-circle-thin"
		},{
			text : "俄语",
		},{
			text : "日语",
			icon : "icon-circle-thin"
		},{
			text : "韩语",
			icon : "icon-circle-thin"
		} ]	} ];

	new indexMenu(opt).update();
});