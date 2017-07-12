window.onload = function()
{	
	loadUploadList();//加载下载列表
	
	intilizeNavigation();
}



function intilizeNavigation()
{
	var itemli1 = document.getElementById("navigationUpload");
	var itemli2 = document.getElementById("navigationDownload");	
	var table1 = document.getElementById("myUploadList");
	var table2 = document.getElementById("myDownloadList");
	itemli1.onclick = function()
	{
		table1.style.display = 'block';
		table2.style.display = 'none';
		itemli1.style.background='#dbeef3';
		itemli2.style.background='';
		loadUploadList();
	}
	
	itemli2.onclick = function()
	{
		table1.style.display = 'none';
		table2.style.display = 'block';
		itemli1.style.background='';
		itemli2.style.background='#dbeef3';		
		loadTemplateList();
	}
	
}

/////上传部分/////


/**
 * 加载自己上传的文件
 */
function loadUploadList() 
{	
	$.ajax({
		url : rootPath + '/project/getMyDownloadList.ajax?version'
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


function formatTemplateOper(val, row, index) 
{
	return '<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" onclick="deleteFile(\'' + row['fileId'] + '\')">删除</a>'; 
}

/**
 * 删除已上传的数据
 * @param id
 */
function deleteFile(id) {
	$.ajax({
		url : rootPath + '/project/deleteUploadFile.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		data:
			{
			uploadId:id			
			},
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000")
			{
				loadUploadList();
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
 * 上传文件
 */
function doUpload()
{  
	var monthText = $('#selectMonthText').combobox('getValue');
	
	$("#uploadForm").ajaxSubmit({
		type : 'post',
		url : rootPath + "/project/uploadFiles.ajax?monthText=" + monthText + " & version= " + new Date().getTime() ,
		success : function(data) 
		{
			if (data.messageCode == "00000")
			{
       		     loadUploadList();
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
	
//    var formData = new FormData($( "#uploadForm" )[0]);  
//    $.ajax({  
//         url: rootPath + '/project/uploadFiles.ajax?version' + new Date().getTime() ,  
//         type: 'POST',  
//         data: formData,  
//         async: false,  
//         cache: false,  
//         contentType: false,  
//         processData: false,  
//         success: function (data) {  
//        	 if (data.messageCode == "00000") {
//        		 loadUploadList();
// 			} else {
// 				alert(data.message);
// 			}  
//         },  
//         error: function (returndata) {  
//             alert("失败");  
//         }  
//    });  
}


////下载部分////
/**
 * 加载模板列表
 */
function loadTemplateList() 
{
	$.ajax({
		url : rootPath + '/project/getTemplateList.ajax?version'
				+ new Date().getTime(),
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 $('#myDownloadListTable').datagrid('loadData', data.result);
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}








