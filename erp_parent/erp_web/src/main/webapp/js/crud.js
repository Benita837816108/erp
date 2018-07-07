//保存要提交的action的方法名
var method = "";
$(function(){
	//$.messager.alert("提示",'成功引入easyui','info');
	// 部门列表配置信息
	var cfg = {
		    url:name+'!listByPage.action'    
		    ,columns:columns
		    //,fitColumns:true
		    //,singleSelect:true
		    ,pagination:true //如果为true，则在DataGrid控件底部显示分页工具栏。
		    ,pageSize:5 // pageSize必须在pageList里有才有效
		    ,pageList:[5,10,20,30,40,50]
			,toolbar:[
				{
					text:'新增', // 按钮上的文字
					iconCls:'icon-add',  // 文本左边的图标
					handler:function(){ // 按钮的单击事件
						// 弹出编辑窗口
						$('#editWindow').dialog('open');
						// 清空表单内容
						$('#editForm').form('clear');
						method = "add";
					}
				}
			]
		};
	cfg.singleSelect = true;
	$('#dg').datagrid(cfg);
	
	//绑定查询事件
	$('#btnSearch').bind('click',function(){
		// 查询datagrid里分页的数据格式
		//alert(JSON.stringify($('#dg').datagrid('getData')));
		
		var submitdata = $('#searchForm').serializeJSON(); // 把form里的条件值转成json对象
		//alert(JSON.stringify(submitdata));
		$('#dg').datagrid('load',submitdata);
		/* $('#dg').datagrid('loadData',{
			"total":15,
			"rows":[
				{"name":"abcd","tele":"123","uuid":-34},
				{"name":"管理员组","tele":"000000","uuid":1},
				{"name":"总裁办","tele":"111111","uuid":2},
				{"name":"采购部","tele":"222222","uuid":3},
				{"name":"销售部","tele":"333333","uuid":4},
				{"name":"公关部","tele":"444444","uuid":5},
				{"name":"行政部","tele":"555555","uuid":6},
				{"name":"人事部","tele":"555555","uuid":7},
				{"name":"运输部","tele":"444444","uuid":8},
				{"name":"党办","tele":"555555","uuid":9},
			]
		}); */
		// ajax
		/* $.ajax({
			url:name+'!list.action',
			data:submitdata,
			dataType:'json', // 把服务端响应回来的数据转成json对象, 默认 string
			type:'post',
			success:function(rtn){
				// dataType:json rtn={}, datatype:json, string
				$('#dg').datagrid('loadData',rtn);
			}
		}); */
	});
	
	$('#editWindow').dialog({    
	    title: '编辑', // 窗口的标题    
	    width: 260,
	    height: 160,    
	    closed: true,    // 初始化时，是否为关闭状态, true:不弹出窗口, false:弹出窗口
	    modal: true,  // 模式窗口
	    buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				var submitdata = $('#editForm').serializeJSON(); 
				$.ajax({
					url:name+'!' + method + '.action',
					data:submitdata,
					dataType:'json', // 把服务端响应回来的数据转成json对象, 默认 string
					type:'post',
					success:function(rtn){
						//{success:true,message:''};
						$.messager.alert("提示", rtn.message, 'info', function(){
							if(rtn.success){
								// 关闭编辑窗口
								$('#editWindow').dialog('close');
								// 刷新部门列表
								$('#dg').datagrid('reload');
							}
						});
					}
				});
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				// 关闭编辑窗口
				$('#editWindow').dialog('close');
			}
		}]
	}); 
	
});

/**
 * 删除
 */
function del(uuid){
	$.messager.confirm('确认', '确定要删除吗?', function(yes){
		if (yes){
			var submitdata = {};
			submitdata.id=uuid;
			$.ajax({
				url:name+'!delete.action',
				data:submitdata,
				dataType:'json', // 把服务端响应回来的数据转成json对象, 默认 string
				type:'post',
				success:function(rtn){
					//{success:true,message:''};
					$.messager.alert("提示", rtn.message, 'info', function(){
						if(rtn.success){
							// 刷新部门列表
							$('#dg').datagrid('reload');
						}
					});
				}
			});
		}
	});
}

/**
 * 修改
 */
function edit(uuid){
	// 弹出窗口
	$('#editWindow').dialog('open');
	// 清空表单内容
	$('#editForm').form('clear');
	
	/* $.ajax({
		url:name+'!get.action',
		data:{id:uuid},
		dataType:'json', // 把服务端响应回来的数据转成json对象, 默认 string
		type:'post',
		success:function(rtn){
			$('#depname').val(rtn.name);
			$('#deptele').val(rtn.tele);
		}
	}); */
	// 加载回显远程url返回的数据
	$('#editForm').form('load',name+'!get.action?id=' + uuid);
	//{"name":"管理员组","tele":"000000","uuid":1}
	//$('#editForm').form('load',{"dep.name":"管理员组","dep.tele":"000000","dep.uuid":1});
	method = "update";
}