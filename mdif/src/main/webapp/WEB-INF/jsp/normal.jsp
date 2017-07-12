<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String rootPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申报员</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/styles.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/newWindow.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery-form.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/normal.js"></script>
<script type="text/javascript">
 var rootPath = "<%=rootPath%>";
</script>
</head>
<body>
	<div class="main">
		<div class="header">
			<div class="header-center">
				<div class="logo fl"></div>
				<a href="/mdif/user/logout.do" class="fr btn-exit">退出</a>

			</div>

		</div>
		<div class="con">
			<div class="title">
				<span id ="projectName" name="projectName">${projectName}</span>
			</div>
			<div class="part-two">
				<div class="left-tab">
					<ul class="left-tab-ul">
					    <li id="navigationUpload" style="background:#dbeef3">上传</li>
						<li id="navigationDownload" style="background:">下载</li>						
					</ul>
				</div>				
				
				<!-- 上传列表 -->
				<div id="myUploadList" class="right-tab-con tab-operation" style="display: block;">					
                    <form id= "uploadForm" enctype='multipart/form-data' method='post'> 
                       <table>
                           <tr>
                           <td>
                               <div>
                               <input name="sourceFile" class="easyui-filebox" data-options="prompt:'请选择文件...',buttonText:'&nbsp;选&nbsp;择&nbsp;'"  style="width:300px; height:25px" >
					           </div>
                           </td>
                           <td>
                              <div>
					         <select id="selectMonthText" class="easyui-combobox" name="dept" style="width:100px; height:25px">
                                    <option value="2017/7">2017年7月</option>
                                    <option value="2017/8">2017年8月</option>
                                    <option value="2017/9">2017年9月</option>
                                    <option value="2017/10">2017年10月</option>
                                    <option value="2017/11">2017年11月</option>
                                    <option value="2017/12">2017年12月</option>
                               </select>
					           </div>
                           </td>
                           <td>
                           <a id="btnUploadFile" href="#" class="easyui-linkbutton" type="submit" iconCls="icon-ok" style="width: 50; height: 25" onclick="doUpload()">上传</a> 
                           </td>
                           </tr>
					   
					   </table>
                        
					    
					       
					       
                    </form>	 	
					<table id="templateList" class="easyui-datagrid"
						style="width: 920px; height: 500px"
						data-options="rownumbers:true,singleSelect:true,method:'get'">
						<thead>
							<tr>
							    <th data-options="field:'fileId',hidden:true">id</th>
								<th data-options="field:'region',hidden:true">区域</th>
								<th data-options="field:'hospital',hidden:true">医院名称</th>
								<th data-options="field:'fileName',width:600,align:'center'">文档名称</th>
								<th data-options="field:'monthText',width:150,align:'center'">上传月份</th>
								<th data-options="field:'userName',hidden:true">操作员</th>							
								<th data-options="field:'operator',width:138,align:'center',formatter:formatTemplateOper">操作</th>
							</tr>
						</thead>
					</table>
				</div> 
				
				
				<!-- 模板下载 -->
				<div id="myDownloadList" class="right-tab-con tab-operation" style="display: none;">
					<table id="myDownloadListTable" class="easyui-datagrid" style="width: 920px; height: 500px"
						data-options="rownumbers:true,singleSelect:true,method:'get'">
						<thead>
							<tr>
								<th data-options="field:'id',hidden:true">id</th>
								<th data-options="field:'templateName',width:750,align:'center'">模板名称</th>
								<th data-options="field:'operator',width:138,align:'center',formatter:formatDownloadFileOper">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
								
			</div>
		</div>		
	</div>
	
	
<script type="text/javascript">
function formatDownloadFileOper(val, row, index) 
{	
	 return "<a href='" + rootPath +  "/project/downloadTemplateFile.ajax?templateId=" + row['id']  + "'>下载</a>";	 
}
</script>
<script type="text/javascript">
            $(function(){
                $('#selectMonthText').combobox({    
                 required:true,    
                 multiple:false, //多选
                 editable:false  //是否可编辑
                 });  
            })
</script>
       

</body>

</html>