window.onload = function()
{	
	loadDownloadList();
	loadUnitPara();
	$(function() {
		$("#btnAddUser").bind("click", function() {
			addUser();
		});
	});
	intilizeNavigation();
}

/**
 * 记载必要参数
 */
function loadUnitPara()
{
	// 获取医院列表
	$.ajax({
		url : rootPath + '/project/getHospitals.ajax?version'
				+ new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		timeout : 1000,
		cache : false,
		success : succFunction
	});

	// 获取区域列表
	$.ajax({
		url : rootPath + '/project/getRegion.ajax?version'
				+ new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		timeout : 1000,
		cache : false,
		success : resionSuccFunction
	});
}



function intilizeNavigation()
{
	var itemli1 = document.getElementById("navigationDownload");
	var itemli2 = document.getElementById("navigationUpload");		
	var itemli3 = document.getElementById("navigationSetting");
	var itemli4 = document.getElementById("navigationProjectList");
	var table1 = document.getElementById("myDownloadList");
	var table2 = document.getElementById("myTemplateTable");		
	var table3 = document.getElementById("myUsersTable");
	var table4 = document.getElementById("myProjectList");
	itemli1.onclick = function()
	{
		table1.style.display = 'block';
		table2.style.display = 'none';
		table3.style.display = 'none';
		table4.style.display = 'none';
		itemli1.style.background='#dbeef3';
		itemli2.style.background='';
		itemli3.style.background='';
		itemli4.style.background='';
		loadDownloadList();
	}
	
	itemli2.onclick = function()
	{
		table1.style.display = 'none';
		table2.style.display = 'block';
		table3.style.display = 'none';
		table4.style.display = 'none';
		itemli1.style.background='';
		itemli2.style.background='#dbeef3';
		itemli3.style.background='';
		itemli4.style.background='';
		loadTemplateList();
	}
	
	itemli3.onclick = function()
	{
		table1.style.display = 'none';
		table2.style.display = 'none';
		table3.style.display = 'block';
		table4.style.display = 'none';
		itemli1.style.background='';
		itemli2.style.background='';
		itemli3.style.background='#dbeef3';
		itemli4.style.background='';
		loadUsers();
	}
	
	itemli4.onclick = function()
	{
		table1.style.display = 'none';
		table2.style.display = 'none';
		table3.style.display = 'none';
		table4.style.display = 'block';
		itemli1.style.background='';
		itemli2.style.background='';
		itemli3.style.background='';
		itemli4.style.background='#dbeef3';
		loadMyProject();
	}

	  
}


function doUpload() 
{ 
	$("#uploadForm").ajaxSubmit({
		type : 'post',
		url : rootPath + '/project/uploadTemplate.ajax?version=' + new Date().getTime() ,
		success : function(data) 
		{
			if (data.messageCode == "00000")
			{
				loadTemplateList();
			} 
			else
			{
				alert(data.message);
			} 
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			alert("失败");
		}
	});
	
//	
//    var formData = new FormData($( "#uploadForm" )[0]);  
//    $.ajax({  
//         url: rootPath + '/project/uploadTemplate.ajax?version' + new Date().getTime() ,  
//         type: 'POST',  
//         data: formData,  
//         async: false,  
//         cache: false,  
//         contentType: false,  
//         processData: false,  
//         success: function (data) {  
//        	 if (data.messageCode == "00000") {
//         		loadTemplateList();
// 			} else {
// 				alert(data.message);
// 			}  
//         },  
//         error: function (returndata) {  
//             alert("失败");  
//         }  
//    });  
} 


function succFunction(data) {
	// var items = "";
	var dataList, json, orgValue, orgNameValue;
	dataList = [];
	data = data.result;
	$.each(data, function(index, item) {
		orgValue = data[index].hospitalId;
		orgNameValue = data[index].hospitalName;

		dataList.push({
			"value" : orgValue,
			"text" : orgNameValue
		});
	});

	$("#selectHospital").combobox("loadData", dataList);
	$('#selectHospital').combobox({
		editable : false
	});
}

function resionSuccFunction(data) {
	// var items = "";
	var dataList, json, orgValue, orgNameValue;
	dataList = [];
	data = data.result;
	$.each(data, function(index, item) {
		orgValue = data[index].regionId;
		orgNameValue = data[index].regionName;

		dataList.push({
			"value" : orgValue,
			"text" : orgNameValue
		});
	});
	$("#selectRegion").combobox("loadData", dataList);
	$('#selectRegion').combobox({
		editable : false
	});

}


function formatOper(val, row, index) 
{
	return '<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" onclick="deleteUser(\'' + row['id'] + '\')">删除</a>';
	 //return '<a href="javascript:void(0)"  value="授权" onclick="aclRole('+index+')"><img src="js/acl.png"/></a>';  
}


function deleteUser(id) {
	$.ajax({
		url : rootPath + '/project/deleteUser.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		data:
			{
			id:id			
			},
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") {
				loadUsers();
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function formatTemplateOper(val, row, index) 
{
	return '<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" onclick="deleteTemplate(\'' + row['id'] + '\')">删除</a>';
	 //return '<a href="javascript:void(0)"  value="授权" onclick="aclRole('+index+')"><img src="js/acl.png"/></a>';  
}

function deleteTemplate(id) {
	$.ajax({
		url : rootPath + '/project/deleteTemplate.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		data:
			{
			templateId:id			
			},
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000")
			{
				loadTemplateList();
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

/**
 * 加载操作员列表
 */
function loadUsers() 
{
	$.ajax({
		url : rootPath + '/project/getManagerUsers.ajax?version='
				+ new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 $('#myUsers').datagrid('loadData', data.result);
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

/**
 * 加载模板列表
 */
function loadTemplateList() 
{
	$.ajax({
		url : rootPath + '/project/getTemplateList.ajax?version='
				+ new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 $('#templateList').datagrid('loadData', data.result);
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

/**
 * 添加操作员
 */
function addUser() {
	// 数据校验
	var regionName = $('#selectRegion').combobox('getText');
	if (regionName == null || regionName == undefined || regionName == '') {
		$.messager.alert('错误', '区域不能为空！', 'error');
		return;
	}
	var hospitalName = $('#selectHospital').combobox('getText');
	if (hospitalName == null || hospitalName == undefined || hospitalName == '') {
		$.messager.alert('错误', '医院名称不能为空！', 'error');
		return;
	}
	var userName = $("#newUserName").val();
	if (userName == null || userName == undefined || userName == '') {
		$.messager.alert('错误', '账号不能为空！', 'error');
		return;
	}
	var password = $("#newPassword").val();
	if (password == null || password == undefined || password == '') {
		$.messager.alert('错误', '密码不能为空！', 'error');
		return;
	}
	var passwordConfirm = $("#newPasswordConfirm").val();
	if (passwordConfirm == null || passwordConfirm == undefined
			|| passwordConfirm == '') {
		$.messager.alert('错误', '确认密码不能为空！', 'error');
		return;
	}
	if (passwordConfirm != password) {
		$.messager.alert('错误', '两次输入的密码不一致！', 'error');
		return;
	}

	$.ajax({
		url : rootPath + '/project/addManagerUsers.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		cache : false,
		data : {
			regionName : regionName,
			hospitalName : hospitalName,
			userName : userName,
			password : password,
			passwordConfirm : passwordConfirm
		},
		success : function(data) {
			if (data.messageCode == "00000") {
				$("#fullbg,#dialog").hide();
				loadUsers();// 重新加载操作员列表
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	// $("#fullbg,#dialog").hide();

}


/**
 * 加载下载列表
 */
function loadDownloadList() 
{
	$.ajax({
		url : rootPath + '/project/getMyDownloadList.ajax?version=' + new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 $('#myDownloadListTable').datagrid('loadData', data.result);
				
			} 
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}



function loadMyProject() 
{
	$.ajax({
		url : rootPath + '/project/getManagerProject.ajax?version='
				+ new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 $('#myProjectListTable').datagrid('loadData', data.result);
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function loadMyProjectByMonth() 
{
	var monthText = $('#selectMonthText').combobox('getValue');
	$.ajax({
		url : rootPath + "/project/getManagerProjectByMonth.ajax?monthText=" + monthText + " & version= " + new Date().getTime() ,
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 $('#myProjectListTable').datagrid('loadData', data.result);
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
