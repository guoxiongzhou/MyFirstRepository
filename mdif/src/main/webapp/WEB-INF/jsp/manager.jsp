<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String rootPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目管理员</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/styles.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/themes/css/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/themes/css/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/themes/css/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/themes/css/newWindow.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/manager.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/themes/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/themes/js/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/themes/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery-form.js"></script> 
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
						<li id="navigationDownload" style="background: #dbeef3">下载</li>
						<li id="navigationUpload" style="background:">上传模板</li>
						<li id="navigationSetting" style="background:">操作配置</li>
					</ul>
				</div>				
				
				<!-- 下载列表 -->
				<div id="myDownloadList" class="right-tab-con tab-operation" style="display: block;">
					<table id="myDownloadListTable" class="easyui-datagrid"
						style="width: 920px; height: 500px"
						data-options="rownumbers:true,singleSelect:true,method:'get'">
						<thead>
							<tr>
								<th data-options="field:'fileId',hidden:true">id</th>
								<th data-options="field:'region',width:80,align:'center'">区域</th>
								<th data-options="field:'hospital',width:200,align:'center'">医院名称</th>
								<th data-options="field:'fileName',width:320,align:'center'">上报文件</th>
								<th data-options="field:'monthText',width:100,align:'center'">上传月份</th>
								<th data-options="field:'userName',width:100,align:'center'">操作员</th>
								<th data-options="field:'operator',width:100,align:'center',formatter:formatDownloadFileOper">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
				
				<!-- 模板 -->
				<div id="myTemplateTable" class="right-tab-con tab-operation" style="display: none;">					
                    <form id= "uploadForm" enctype='multipart/form-data' method='post'> 
                       <table>
                           <tr>
                           <td>
                               <div>
                               <input name="sourceFile" class="easyui-filebox" data-options="prompt:'请选择文件...',buttonText:'&nbsp;选&nbsp;择&nbsp;'" style="width:300px; height:25px" >
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
								<th data-options="field:'id',hidden:true">id</th>
								<th data-options="field:'templateName',width:750,align:'center'">模板名称</th>
								<th data-options="field:'operator',width:138,align:'center',formatter:formatTemplateOper">操作</th>
							</tr>
						</thead>
					</table>
				</div>                
                
				<!-- 操作配置 -->
				<div id="myUsersTable" class="right-tab-con tab-operation" style="display: none;">
					<table id="myUsers" class="easyui-datagrid"
						style="width: 920px; height: 500px"
						data-options="rownumbers:true,singleSelect:true,method:'get',toolbar:toolbar">
						<thead>
							<tr>
								<th data-options="field:'id',hidden:true">id</th>
								<th data-options="field:'region',width:150,align:'center'">区域</th>
								<th data-options="field:'hospital',width:300,align:'center'">医院名称</th>
								<th data-options="field:'userName',width:150,align:'center'">账号</th>
								<th data-options="field:'password',width:150,align:'center'">密码</th>
								<th
									data-options="field:'operator',width:138,align:'center',formatter:formatOper">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
								
			</div>
		</div>
		<div id="fullbg"></div>
		<div id="dialog" style="width: 320px; height: 300px">
			<p class="close">
				<a href="#" onclick="closeBg();">关闭</a>
			</p>
			<form action=" " onSubmit="return addUser()" method="post">
				<div>
					<table>
						<tr style="height: 32px">
							<td style="width: 100px;"><div>
									<span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:</span>
								</div></td>
							<td style="width: 250px;"><select required="true"
								id="selectRegion" name="selectRegion" class="easyui-combobox"
								style="width: 200px; height: 25px"></select></td>
						</tr>
						<tr>
							<td><div>
									<span>医&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院:</span>
								</div></td>
							<td><select required="true" id="selectHospital"
								name="selectHospital" class="easyui-combobox"
								style="width: 200px; height: 25px" editable:false></select></td>
						</tr>
						<tr>
							<td><div>
									<span style="width: 180px; height: 25px">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</span>
								</div></td>
							<td><div>
									<input id="newUserName" required="true" class="easyui-textbox"
										style="width: 200px; height: 25px"
										data-options="validType:'length[6,30]'">
									<!-- <span style="height: 25px"><input type="text"
										name="userName"></span> -->
								</div></td>
						</tr>
						<tr>
							<td><div>
									<span style="width: 180px; height: 25px">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span>
								</div></td>
							<td><div>
									<!-- <span style="height: 25px"><input type="password" name="password"></span> -->
									<input id="newPassword" class="easyui-textbox" required="true"
										type="password" style="width: 200px; height: 25px"
										data-options="validType:'length[6,30]'">
								</div></td>
						</tr>
						<tr>
							<td><div>
									<span style="width: 180px; height: 25px">密码确认:</span>
								</div></td>
							<td><div>
									<input id="newPasswordConfirm" class="easyui-textbox"
										required="true" type="password"
										style="width: 200px; height: 25px"
										data-options="validType:'length[6,30]'">
									<!-- <span style="height: 25px"><input type="password" name="passwordConfirm"></span> -->
								</div></td>
						</tr>
					</table>
				</div>
				<div style="height: 50px;"></div>
				<div>
					<a id="btnAddUser" href="#" class="easyui-linkbutton" type="submit"
						iconCls="icon-ok" style="width: 50; height: 25px">保存</a>
					<!-- <input type="submit" style="width: 100px; height: 28px" value="提交"> -->
				</div>
			</form>
		</div>

	</div>
	
	<script type="text/javascript">
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				showAddUserBg()
			}
		} ];
	</script>
<script type="text/javascript">
function formatDownloadFileOper(val, row, index) 
{
	 return "<a href='" + rootPath +  "/project/downloadFile.ajax?uploadId=" + row['fileId']  + "'>下载</a>";	 
}
</script>

	<script type="text/javascript">
		var toolbarTemplate = [ {
			text : '添加模板',
			iconCls : 'icon-add',
			handler : function() {
				//上传模板
				importClick()
			}
		} ];
	</script>

	<script type="text/javascript">
		//显示添加人员窗口
		function showAddUserBg() {
			//var bh = $("body").height()+50;
			var bh = $(document).height();
			var bw = $("body").width();
			$("#fullbg").css({
				height : bh,
				width : bw,
				display : "block"
			});
			$("#dialog").show();
		}
		//关闭灰色 jQuery 遮罩 
		function closeBg() {

			$("#fullbg,#dialog").hide();
		}
	</script>

	<script type="text/javascript">
		//显示添加人员窗口
		function showAddUserBg() {
			//var bh = $("body").height()+50;
			var bh = $(document).height();
			var bw = $("body").width();
			$("#fullbg").css({
				height : bh,
				width : bw,
				display : "block"
			});
			$("#dialog").show();
		}
		//关闭灰色 jQuery 遮罩 
		function closeBg() {

			$("#fullbg,#dialog").hide();
		}
	</script>

</body>

</html>