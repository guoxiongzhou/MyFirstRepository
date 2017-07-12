<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String rootPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>总管理员</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/styles.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/css/newWindow.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery-form.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin.js"></script>
<script type="text/javascript">
 var rootPath = "<%=rootPath%>";
</script>
</head>
<body >
    <div class="main">
        <div class="header">
            <div class="header-center">
                 <div class="logo fl"></div>
                 <a href="/mdif/user/logout.do" class="fr btn-exit">退出</a>
            </div>
            
        </div>    
        <div class="con">
            <div class="title"><span>上传情况统计</span></div>
            <div class="part-two pd20">
                <div class="operation-list">
                    <div class="btn-group fl">
                        <span id="allProjects" style="background:#dbeef3">全部</span>
                        <span id="uploadProjects"  style="background:">已上传</span>
                        <span id="unuploadProjects"  style="background:">未上传</span>
                    </div> 
                    <div class="fl operation-input"><span>搜索:</span><input class="input-style" type="" name="" > </div>
                </div>
                <div class="clear"></div>
                <table id="templateList" class="easyui-datagrid"
						style="width: 1100px; height: 500px"
						data-options="rownumbers:true,singleSelect:true,method:'get'">
						<thead>
							<tr>							
							    <th data-options="field:'projectName',width:200,align:'center'">项目名称</th>
								<th data-options="field:'region',width:100,align:'center'">区域</th>
								<th data-options="field:'hospital',width:365,align:'center'">医院名称</th>
								<th data-options="field:'monthText',width:120,align:'center'">上传月份</th>
								<th data-options="field:'uploadStatus',width:100,align:'center'">是否上传</th>							
								<th data-options="field:'projectManager',width:170,align:'center'">管理员</th>
							</tr>
						</thead>
					</table>                
            </div>
        </div>
    </div>

</body>
</html>