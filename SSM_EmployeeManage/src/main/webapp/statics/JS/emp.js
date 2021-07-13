 /*展示函数*/
$(function(){
	$('#win').window('close');//明细框隐藏
	$("#list").show();//展示显示
	$("#add_update").hide();//添加修改隐藏
	$('#dg').datagrid({    
	    url:'searchEmpPageAll',
	    striped:true,
	    pagination:true,
	    singleSelect:true,
	    pageNumber:1,
	    pageSize:5,
	    pageList:[5,10,15,20],
	    fitColumns:true,
	    columns:[[    
	        {field:'eid',title:'编号',width:100,align:'center'},
	        {field:'ename',title:'姓名',width:100,align:'center'},
	        {field:'sex',title:'性别',width:100,align:'center'},
	        {field:'address',title:'地址',width:100,align:'center'},
	        {field:'edate',title:'生日',width:100,align:'center'},
	        {field:'photo',title:'照片',width:100,align:'center',
	        	formatter: function(value,row,index){
	        		return '<img src=uppic/'+row.photo+' width="40" height="50">';
	        	}
	        },
	        {field:'deptName',title:'部门',width:100,align:'center'},
	        {field:'opt',
	         title:'操作', width:100, align:'center',
	         formatter: function(value,row,index){
	        	    var bt1='<input type="button" value="删除" onclick="dodelById('+row.eid+')">';
					var bt2='<input type="button" value="编辑" onclick="dosearchById('+row.eid+')">';
					var bt3='<input type="button" value="详情" onclick="dosearchDetail('+row.eid+')">';
					return bt1+'&nbsp;'+bt2+'&nbsp;'+bt3;
				     }	
	        }
	    ]]    
	}); 
      $("#dg").datagrid({title:'员工信息'}); 
     

});
/*展示函数结束*/

function add(){
	$("#add_update").show();//添加显示
	$("#list").hide();//展示隐藏
	$("#btsave").show();//保存按钮隐藏
	$("#btupdate").hide();//修改按钮隐藏
	$("#btreset").trigger('click');//重置form表单
	$.post('searchDept_Welf',function(map){
		var lisdept=map.lisdept;//部门列表
		var liswelf=map.liswelf;//福利列表
		var content=$("#welf").html();
		 //福利复选框  
			if(content==null||content== "" ){
			for(var i=0;i<liswelf.length;i++){
			var welf=liswelf[i];
			$("#welf").append("<input type='checkbox' name='wids' value='"+welf.wid+"'>"+welf.welfare+"</input>")
		};
        };//end of if
		 
		//部门下拉列表
		 $('#deptId').combobox({    
			    data:lisdept,    
			    valueField:'deptId',    
			    textField:'deptName',
			    value:2,
			    panelHeight:100 
			});  
		
	},'json');//end of post
	
	/*添加函数*/
	$("#btsave").click(function(){
		$('#f1').form('submit', {
        url:"saveEmp",
        onSubmit: function(){
            var isValid = $(this).form('validate');
        if (!isValid){
            $.messager.progress('close');   // 如果表单是无效的则隐藏进度条
        }
         return isValid;    // 返回false终止表单提交
        },
        success:function(code){
           if(code==1){
	            $.messager.progress('close');   // 如果提交成功则隐藏进度条
				$.messager.alert('提示','保存成功！');
				$('#dg').datagrid('reload');    // 重新载入当前页面数据
				$("#list").show();
	            $("#add_update").hide();
			}else{
				$.messager.alert('提示','保存失败！');
			}
        }
       });
     });//end of save
	
	/*添加函数结束*/
};
  /*修改页面插入数据函数*/
  function dosearchById(eid){
	$("#add_update").show();//添加显示
	$("#list").hide();//展示隐藏
	$("#btupdate").show();//修改按钮隐藏
	$("#btsave").hide();//保存添加按钮隐藏
	$("#btreset").trigger('click');//重置form表单
	$.post('searchDept_Welf',function(map){
		var lisdept=map.lisdept;//部门列表
		var liswelf=map.liswelf;//福利列表
		var content=$("#welf").html();
		 //福利复选框  
			if(content==null||content== "" ){
			for(var i=0;i<liswelf.length;i++){
			var welf=liswelf[i];
			$("#welf").append("<input type='checkbox' name='wids' value='"+welf.wid+"'>"+welf.welfare+"</input>")
		};
        };//end of if
		 
		//部门下拉列表
		 $('#deptId').combobox({    
			    data:lisdept,    
			    valueField:'deptId',    
			    textField:'deptName',
			    value:2,
			    panelHeight:100 
			});  
		
	},'json');//end of post
	$.getJSON('searchEmpById?eid='+eid,function(emp){
		//处理修改页面数据
		  //普通文本
		$('#f1').form('load',{
			'eid':emp.eid,
			'ename':emp.ename,
			'sex':emp.sex,
			'address':emp.address,
			'edate':emp.edate,
			'deptId':emp.deptId,
			'salary':emp.salary,
		});
         //处理图片
        $("#myphoto").attr('src','uppic/'+emp.photo);
         //复选框
         var widss=emp.wids;
         $(":checkbox[name='wids']").each(function(){
			for (var i = 0; i < widss.length; i++) {
				if($(this).val()==widss[i]){
					$(this).prop("checked",true);
				}
			}
		}); 

	});
}
/*修改页面插入数据函数结束*/
/*修改提交函数*/
function btupdate(){
		 $.messager.progress();	// 显示进度条
		 $('#f1').form('submit', {
		 	url:'updateEmp',
		 	onSubmit: function(){
		 		var isValid = $(this).form('validate');
		 		if (!isValid){
		 			$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		 		}
		 		return isValid;	// 返回false终止表单提交
		 	},
		 	success: function(code){
		 		$.messager.progress('close');	// 如果提交成功则隐藏进度条
		 	if(code=='1'){
		 		$.messager.alert('提示','修改成功'); 
		 		$('#dg').datagrid('reload');    // 重新载入当前页面数据
				$("#list").show();
	            $("#add_update").hide();
		 	}else{
		 		$.messager.alert('提示','修改失败'); 
		 	}
		 	}
		 });
};
/*修改提交函数结束*/
/*删除函数*/
function dodelById(eid){
	var date="eid="+eid;
	 $.messager.confirm('确认','您确认想要删除记录吗？',function(r){  
	  if (r){
		$.messager.progress();	// 显示进度条
	 $.post('deleteEmp',date,function(code){
		if(code==1){
	            $.messager.progress('close');   // 如果提交成功则隐藏进度条
				$.messager.alert('提示','删除成功！');
				$('#dg').datagrid('reload');    // 重新载入当前页面数据
				$("#list").show();
	            $("#add_update").hide();
			}else{
				$.messager.alert('提示','删除失败！');
			}
	});//end of post
	}
});//end of confirm
}
/*删除函数结束*/
/*查看明细*/
function dosearchDetail(eid){
	$.getJSON('searchDetail?eid='+eid,function(emp){
		$("#eidtext").html(emp.eid);
		$("#enametext").html(emp.ename);
		$("#sextext").html(emp.sex);
		$("#addresstext").html(emp.address);
		$("#edatetext").html(emp.edate);
		$("#deptext").html(emp.deptName);
		$("#phototext").html(emp.photo);
		$("#salarytext").html(emp.salary);
		//员工福利
		/********获取当前员工的福利********/
		var liswelf=emp.welfares;
		var wnames=[];//获取福利名称的数组
		
		for(var i=0;i<liswelf.length;i++){
			var wf=liswelf[i];//获取emp中的每个福利对象
			wnames.push(wf.welfare);//加入到数组
		}
		var strwname=wnames.join(',');//使用,链接数组的元素值
		
		$("#wftxt").html(strwname);//显示在福利文本标签中
		
		$("#dtmyphoto").attr('src','uppic/'+emp.photo);
		$('#win').window('open');  // open a window  
	});
}
 /*查看明细结束*/