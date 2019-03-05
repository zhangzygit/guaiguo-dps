
$.ajaxSetup ({
   cache: false //关闭AJAX相应的缓存
});

$(function(){
	
	/*$.extend($.fn.validatebox.defaults.rules, {
	    maxLength: {   
	        validator: function(value, param){   
	            return param[0] >= value.length;   
	        },   
	        message: '请输入最大{0}位字符.'  
	    }   
	});*/
	
	
});
//获取当前时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 1 && hour <= 9) {
    	hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
    	minute = "0" + minute;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + minute;
    return currentdate;
}
//处理日期格式 (val java对象格式处理方式)
function wmsInvList_Dateformattime(val) {
	if(val == null || $.trim(val) == ''){
		return "";
	}
	var year=parseInt(val.year)+1900;
	var month=(parseInt(val.month)+1);
	month=month>9?month:('0'+month);
	var date=parseInt(val.date);
	date=date>9?date:('0'+date);
	var hours=parseInt(val.hours);
	hours=hours>9?hours:('0'+hours);
	var minutes=parseInt(val.minutes);
	minutes=minutes>9?minutes:('0'+minutes);
	var seconds=parseInt(val.seconds);
	seconds=seconds>9?seconds:('0'+seconds);
	var time=year+'-'+month+'-'+date +' '+hours+':'+minutes+':'+seconds;  //时分秒
	return time;
};
//处理日期格式 (val java对象格式处理方式)
function tqa_Dateformattime(val) {
	if(val == undefined || val == null || $.trim(val) == ''){
		return "";
	}
	var strDate = val.getFullYear() + "-";
	var month = val.getMonth() + 1;
	strDate += month > 9?month:('0' + month);
	strDate += "-";
	strDate += val.getDate() > 9?val.getDate():('0' + val.getDate());
	return strDate;
};

function getDateformatter(str){
	if(str == undefined || str == null || $.trim(str) == ''){
		return null;
	}
    var tempStrs = str.split(" "); 

    var dateStrs = tempStrs[0].split("-"); 

    var year = parseInt(dateStrs[0], 10); 

    var month = parseInt(dateStrs[1], 10) - 1; 

    var day = parseInt(dateStrs[2], 10); 

    var date = new Date(year, month, day);
	return date;
}

var commonu = {
		warmBox :null,//保温箱配置
		posCodeKey : "posId", //缓存中POS key
		orderHandOverStatus:{
			//1:新增, 2(称重), 3:打印批次截单, 4.批次截单, 5:批次交接 
			1:'新增',
			2:'称重',
			3:'打印批次截单',
			4:'批次截单',
			5:'批次交接',
			6:'完成'
		},
		//损益单据状态
		wmspl_billState:{
			1:'存盘',
			2:'确认',
			3:'审核通过',
			4:'审核不通过',
			save:1,
			confirm:2,
			verifyOk:3,
			verfiryNo:4
		},
		salesSrRecvHeaderStatus:{
			//销退质检状态
			0:'销退申请',
			1:'到货登记',
			2:'质检确认',
			3:'质检完成'
		},
		plType:{ //损益类型
			bs:1, //报损
			by:2  //报益
		},
		owner : { //货主列表
			1:'春播科技'
		},
		rf_taskType : {
			1:"上架",
			2:"下架",
			3:"盘点",
			4:"移库"
		},
		rf_taskStatus : {
			0:"新增",
			1:"已认领",
			2:"正在执行",
			3:"已完成"
		},
		rf_tsskSrcType : {
			1:'销售下架',
			2:'采购收货',
			3:'销退上架',
			4:'采退下架', 
			5:'计划盘点',
			6:'移库',
			8:'领料下架',
			9:'领料下架取消回库', 
			10:'实物报缺',
			11:"取消订单"
		},
		srcTransType:{
			1:'销退业务',
			2:'领用业务',
			3:'借用',
			4:'归还',
			5:'报废',
			6:'库房盘点'
		},
		skuType:[  {'type':1001,'typeName':'一般商品'},                   
				   {'type':1002,'typeName':'礼品卡'},                   
				   {'type':1003,'typeName':'包装材料'},                  
				   {'type':1004,'typeName':'赠品'},                 
				   {'type':1005,'typeName':'加工成品'},                
				   {'type':1008,'typeName':'虚拟组套'}                
				],
		skuTypeValue:{
			1001:'一般商品',
			1002:'礼品卡',
			1003:'包装材料',
			1004:'赠品',
			1005:'加工成品',
			1008:'虚拟组套'
		},
		testCyc:[
		         {'type':-1,'typeName':'请选择'},
		         {'type':1,'typeName':'每批次'},
		         {'type':4,'typeName':'每个月(30天)'},
		         {'type':7,'typeName':'每3个月(90天)'},
		         {'type':11,'typeName':'每半年(180天)'},
		         {'type':14,'typeName':'每年(365天)'}
		         ],
		 testCycValue:{
			 1:'每批次',
			 4:'每个月(30天)',
			 7:'每3个月(90天)',
			 11:'每半年(180天)',
			 14:'每年(365天)',
		 },
		needTest:[
		          {'type':-1,'typeName':'请选择'},
		          {'type':0,'typeName':'未设置'},
			      {'type':1,'typeName':'是'},
			      {'type':2,'typeName':'否'}
		          ],
		          freeQcReason:[
		                    {'type':-1,'typeName':'请选择'},
		                    {'type':0,'typeName':'商品检测周期设置错误'},
		                    {'type':1,'typeName':'IT误报'},
		                    {'type':2,'typeName':'仪器故障'},
		                    {'type':3,'typeName':'预售商品'},
		                    {'type':4,'typeName':'原料免检'},
		                    {'type':30,'typeName':'其他'}
		                    ],
		          needTestValue:{
		        	  0:'未设置',
		        	  1:'是',
		        	  2:'否'
		          },
		freeQcReasonValue:{
		        0:'商品检测周期设置错误',
		       1:'IT误报',
		       2:'仪器故障',
		       3:'预售商品',
		       4:'原料免检',
		       30:'其他'
		      }
		
};
commonu.getWarmBoxConf = function(){
	
	//
	$.ajax({
		type:"post",
		url:"qulitycheck_warmBoxConf",
		async:false,
		dataType:"json",
		success:function(da){
			if(da.result){
				commonu.warmBox = da.data;
			}
		}
	});
};
commonu.showMessage = function(message){
	 $.messager.show({
         title:'<span style="color:red;face:verdana">提示</span>',
         msg:"<span style='color:red;face:verdana'>"+message+"</span>",
         showType:'slide',
         style:{
             right:'',
             top:document.body.scrollTop+document.documentElement.scrollTop,
             bottom:''
         }
     });
};

commonu.showInfo = function(message){
	 $.messager.show({
        title:'提示',
        width:430,
        height:200,
        msg:"<span>"+message+"</span>",
        showType:'slide',
        style:{
            right:'',
            top:document.body.scrollTop+document.documentElement.scrollTop,
            bottom:''
        }
    });
};

commonu.Dateformattime = function(val) {
	if(val == null || $.trim(val) == ''){
		return "";
	}
	var year=parseInt(val.year)+1900;
	var month=(parseInt(val.month)+1);
	month=month>9?month:('0'+month);
	var date=parseInt(val.date);
	date=date>9?date:('0'+date);
	var hours=parseInt(val.hours);
	hours=hours>9?hours:('0'+hours);
	var minutes=parseInt(val.minutes);
	minutes=minutes>9?minutes:('0'+minutes);
	var seconds=parseInt(val.seconds);
	seconds=seconds>9?seconds:('0'+seconds);
	var time=year+'-'+month+'-'+date +' '+hours+':'+minutes+':'+seconds;  //时分秒
	return time;
};

//仓库列表
commonu.getWarehouse = function(){
	
	if(commonu.warehouse != null){
		return commonu.warehouse;
	}
	var id = $("#warehouseIds").val();
	if(id == null || $.trim(id) == ''){
		return null;
	}
	var ids = new Array();
	ids = id.split(",");
	if(ids.length != 1){
		return null;
	}
	
	$.ajax({
		type : 'POST',
		async : false,
		data : {
			'condition':id
		},
		url: 'getWarehouseObj', 
        datatype : "json", //参数的类型
        timeout: 20000,
		success: function(data){
			if(data.success){
				commonu.warehouse = data.obj;
			}
		},
	    error: function(data) {
	    }
  	});
};
commonu.getPOSNO = function(){
	var posId = getCookie("posId");
	if(posId == null || $.trim(posId) == ''){
		var mydiv = document.createElement("div"); 
		$(mydiv).window({
		    href: 'receive_toPosJsp',
		    closed:false,
		    width:350,
		    height:180,
		    modal:true,
		    title:"POS台号登记"
	   	}); 
	}
	return posId;
};

commonu.systemErrorMessage = function(){
	$.messager.show({
        title:'<span style="color:red;face:verdana">提示</span>',
        msg:"<span style='color:red;face:verdana'>系统异常！</span>",
        showType:'slide',
        style:{
            right:'',
            top:document.body.scrollTop+document.documentElement.scrollTop,
            bottom:''
        }
    });
};

commonu.tooltipFn = function(value,className){
	var abValue = value;  
	if (value.length>=18) {  
	    abValue = value.substring(0,15) + '...';  
	}  
	var content = '<div title="' + value + '" class="'+className+'" >' + abValue + '</div>';  
	return content;
};
commonu.tooltipShowFn = function(className){
	$.each($('.'+className),function(i,v){
		var title = $(v).attr("title");
		$(v).tooltip({    
			position: 'right',    
			content: '<span style="color:#fff">'+title+'</span>',    
			onShow: function(){$(this).tooltip('tip').css({            
				backgroundColor: '#666',            
				borderColor: '#666'        
				});    
				}
			});
	});
};


//POS
/**
 * 获取POS台号，如果获取不到则弹出pos台设置框
 * callBackFn 回调函数，设置完成
 */
commonu.getPosCode = function(callBackFn){
	
	var posCode = commonu.posCode;
	if($.trim(posCode) == ''){
		//1:获取cookies中posCode
		posCode = getCookie(commonu.posCodeKey);
	}
	//2：依然获取不到时设置pos
	if($.trim(posCode) == ''){
		//setCookie
		commonu.openPosWindow(callBackFn);
	}else{
		//设置PosCode
		callBackFn(posCode);
	}
};
/**
 * 删除pos,清除cookie
 */
commonu.clearPosCode = function(callBackFn){
	//
	delCookie(commonu.posCodeKey);
	commonu.openPosWindow(callBackFn);
};
/**
 * 弹出pos设置
 */
commonu.openPosWindow = function(callBackFn){
	//onClose
	layer.open({
        type: 2,
        title: '<strong>POS台号设置</strong>',
        maxmin: false,
        shadeClose: false, //点击遮罩关闭层
        area : ['400px' , '200px'],
        content: 'sys_setPosCodeView',
        end:function(){
        	var code = posCode = getCookie(commonu.posCodeKey);
        	callBackFn(code);
        }
    });
	
};

//获取打印机列表
commonu.getPrinterDataList = function(){
	//打印机设置
	LODOP = getLodop();
	var countPrint = LODOP.GET_PRINTER_COUNT();
	var arrayPrint = new Array();
	for (var n = 0; n < countPrint; n++) {
		arrayPrint.push(LODOP.GET_PRINTER_NAME(n));
	}
	var printObjs = new Array();
	for (var m = 0; m < arrayPrint.length; m++) {
		var obj = {
			id : m + 1,
			text : arrayPrint[m]
		};
		printObjs[m] = obj;
	}
	
	return printObjs;
};

function easyUIDatagridTip(){
	 $(".note").tooltip({
         onShow: function(){
             $(this).tooltip('tip').css({ 
                 width:'300',
                 boxShadow: '1px 1px 3px #292929'                        
             });
         }
	 });
}


//获取地址栏参数
commonu.getURLParamValue = function(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};

//返回主菜单
commonu.backToMenu = function(){
    document.location.href="/main.do";
};

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "H+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
    }  ;
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

function addDate(date,days){
    var d=new Date(date);
    d.setDate(d.getDate()+days);
    var m=d.getMonth()+1;
    return d.getFullYear()+'-'+m+'-'+d.getDate();
}
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd HH:mm:ss"); //扩展的Date的format方法(上述插件实现)
}

$(document).ready(function() {
    $("#tabs-tool-tt").click(function () {
        createWindowModel("我的任务", "myTask", 800, 420);
        $('#tasktable').datagrid('reload');
    })

    $('#tasktable').datagrid(
        {
            fitColumns : true,
            method : "POST",
            loadMsg : "正在加载请稍后...",
            pageSize : 10,//默认选择的分页是每页15行数据
            pageList : [10],//可以选择的分页集合
            pagination : true,
            fitColumns:false,
            // nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
            rownumbers : true,
            remoteSort:false,
			url:'/warehouse/stock/getSerialsFiles.do',
            toolbar: '#tb',
            columns : [[
                {
                    field : 'exportFileName',
                    title : '文件名称',
                    width : 200
                }, {
                    field : 'createDate',
                    title : '创建时间',
                    formatter:function(value,row,index){
                        return formatDatebox(value);
                    },
                    width : 200
                }, {
                    field : 'exportFileTime',
                    title : '完成时间',
                    formatter:function(value,row,index){
                        return formatDatebox(value);
                    },
                    width : 200
                }, {
                    field : 'upcNo',
                    title : '操作',
                    formatter:function(value,row,index){
                        return '<button id="'+index+'" class="icon-download">下载</button>';
                    },
                    width : 100
                }]],
            onLoadSuccess : function(data) {

			}
        });
    function downLoad(options) {
        var config = $.extend(true, { method: 'post' }, options);
        var $iframe = $('<iframe id="down-file-iframe" />');
        var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
        $form.attr('action', config.url);
        for (var key in config.data) {
            $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
        }
        $iframe.append($form);
        $(document.body).append($iframe);
        $form[0].submit();
        $iframe.remove();
    }

    $(document).on('click','.icon-download',function (e) {
        var index = $(e.target).attr('id');
        var rows = $('#tasktable').datagrid('getRows');
        var row = rows[index];
        $.ajax({
			url:"downloadFiles.do",
			data:{"exportFileName":row.exportFileName},
            type: "POST",
            success: function(data){
                window.location.href=data;
            }
        });
    })
})

function createWindowModel(title,id,width,height) {
    $("#"+id).window({
        width:width,
        height:height,
        modal:true,
        title:title,
        resizable:false,
        cls:'theme-panel-black',
        border:false,
        minimizable:false,
        collapsible:false,
        closed:false
    });
}

//弹出加载层
function load() {
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在加载请稍后...").appendTo("body").css({display:"block",width:"130px",height:"12px","line-height":"12px",left:($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
}
//取消加载层
function disLoad() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}
