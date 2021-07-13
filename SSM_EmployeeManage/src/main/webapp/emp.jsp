<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理系统</title>
<style type="text/css">
#add_update{
margin:auto;
background-color:#F8F8FF;
width: 660px;
height: auto;
font-family: "楷体";
font-size: 1em;
}
.title{
 font-size: 1.4em;
 color: #6666ff;
 margin:0px;
 padding:0px;
 
}
#list{
margin:auto;
background-color:#F8F8FF;
height: auto;

}

</style>
</head>
<body>
<!-- 员工新增、修改 -->
<div id="add_update" align="center" >
<form id="f1" name="f1" method="post" enctype="multipart/form-data">
<table >
<tr style="text-align: center;">
<td colspan="3" class="title">员工管理</td>
</tr>
<tr>
       <td width="120" align="center">姓名</td>
       <td width="180">
       <input type="text" id="ename" name="ename" iconCls="" class="easyui-textbox"  data-options="required:true">
       </td>
       <td rowspan="7" width="360" align="center">
       <img id="myphoto" alt="图片不存在" src="uppic/default.jpg" width="320" height="340">
       </td>
   </tr>
   <tr>
       <td align="center">性别</td>
       <td>
       <input type="radio" id="sex" name="sex" checked="checked" value="男">男
       <input type="radio"  id="sex" name="sex"  value="女">女
       </td>
      
   </tr>
   <tr>
       <td align="center">地址</td>
       <td>
       <input type="text" iconCls="" class="easyui-textbox"  id="address" name="address">
       </td>
      
   </tr>
   <tr >
       <td align="center">生日</td>
       <td>
       <input type="text" id="edate" name="edate" value="1990-01-01"  class="easyui-datebox" required="required">
       </td>
       
   </tr>
   <tr>
       <td align="center">照片</td>
       <td>
       <input class="easyui-filebox"id="pic" name="pic" buttonText="请选择文件">
       
       </td>
       
   </tr>
   <tr>
       <td align="center">部门</td>
       <td>
       <input  id="deptId" name="deptId">
       </td>
      
   </tr>
   <tr>
       <td align="center">薪资</td>
       <td>
       <input type="text" id="salary" name="salary" class="easyui-numberbox" value="5000" prefix="￥" data-options="min:0,precision:2">
       </td>
      
   </tr>
   <tr>
       <td align="center">福利</td>
       <td colspan="2" width="40">
       <span id="welf"  name="welf"></span>
       </td>
       
   </tr>
   
   <tr align="center" >
       <td colspan="3">
       <input type="hidden" id="eid" name="eid">
       <a id="btsave" name="btsave" href="#" class="easyui-linkbutton" >保存</a>
       <a id="btupdate" name="btupdate" href="#" class="easyui-linkbutton" onclick="btupdate()">修改</a>
       <input type="reset" id="btreset" name="btreset" value="重置">
       </td>
   </tr>
</table>

</form>
</div>
<!-- 员工新增、修改结束 -->

<!--员工列表展示 -->
<div id="list" align="center" ><table id="dg"></table><br>
<input type="button" id="add" name="add" onclick="add()" value="新增" style="height: 30px;line-height:30px; ">
</div>
<!--员工列表展示结束 -->

<!-- 员工详情弹窗 -->
<div id="win" class="easyui-window" title="员工详情" style="width:500px;height:420px"   
        data-options="iconCls:'icon-save',modal:true">   
    <table border="1px" align="center" width="700" id="t2" name="t2">
   <tr align="center" bgcolor="#FFFFCC">
       <td colspan="3">员工管理</td>
   </tr>
   <tr>
   <td width="140" align="center">编号</td>
       <td width="120">
       <span id="eidtext"></span>
       </td>
       <td rowspan="8" width="290">
        <img id="dtmyphoto" alt="图片不存在" src="uppic/default.jpg" width="290" height="300">
       </td>
     </tr>
   <tr>
       <td width="140" align="center">姓名</td>
       <td width="120">
       <span id="enametext"></span>
       </td>
       
   </tr>
   <tr>
       <td align="center">性别</td>
       <td>
        <span id="sextext"></span>
       </td>
      
   </tr>
   <tr>
       <td align="center">地址</td>
       <td>
        <span id="addresstext"></span>
       </td>
      
   </tr>
   <tr>
       <td align="center">图片名称</td>
       <td>
        <span id="phototext"></span>
       </td>
      
   </tr>
   
   <tr >
       <td align="center">生日</td>
       <td>
        <span id="edatetext"></span>
       </td>
       
   </tr>
   <tr>
       <td align="center">部门</td>
       <td>
        <span id="deptext"></span>
       </td>
      
   </tr>
   <tr>
       <td align="center">薪资</td>
       <td>
       <span id="salarytext"></span>
       </td>
      
   </tr>
   <tr>
       <td align="center">福利</td>
       <td colspan="2">
       <span id="wftxt"></span>
       </td>
       
   </tr>
</table>   
</div>  
<!-- 员工个人详情弹窗结束 -->

<link rel="stylesheet" href="eazyUI/themes/default/easyui.css">
<link rel="stylesheet" href="eazyUI/themes/icon.css">
<script type="text/javascript" src="eazyUI/jquery-3.6.js" ></script>
<script type="text/javascript" src="eazyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="eazyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="statics/JS/emp.js"></script>
</body>
</html>