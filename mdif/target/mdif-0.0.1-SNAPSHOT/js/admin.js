window.onload = function()
{		
	intilizeNavigation();
	loadAllProjects();
}



function intilizeNavigation()
{
	var itemli1 = document.getElementById("allProjects");
	var itemli2 = document.getElementById("uploadProjects");
	var itemli3 = document.getElementById("unuploadProjects");
	itemli1.onclick = function()
	{
		//加载全部的项目列表
		itemli1.style.background='#dbeef3';
		itemli2.style.background='';
		itemli3.style.background='';
		loadAllProjects();
	}	
	itemli2.onclick = function()
	{
		//加载已上传的项目列表
		itemli1.style.background='';
		itemli2.style.background='#dbeef3';
		itemli3.style.background='';
		loadUploadProjects();
	}
	itemli3.onclick = function()
	{
		//加载已上传的项目列表
		itemli1.style.background='';
		itemli2.style.background='';
		itemli3.style.background='#dbeef3';
		loadUnuploadProjects();
	}
	
}


function loadAllProjects() 
{
	$.ajax({
		url : rootPath + '/project/getMainManagerProjectList.ajax?version'
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

function loadUploadProjects() 
{
	$.ajax({
		url : rootPath + '/project/getMainManagerUploadedProjectList.ajax?version'
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

function loadUnuploadProjects() 
{
	$.ajax({
		url : rootPath + '/project/getMainManagerUnUploadedProjectList.ajax?version'
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









