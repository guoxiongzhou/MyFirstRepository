window.onload = function()
{		
	intilizeNavigation();
	loadAllProjects(1,10);	
	//表格分页处理
	var pager1=$('#templateList1').datagrid('getPager');
    pager1.pagination({
        total:0,
        rows : 0,
        pageNumber : 1,
        pageList : [10,20,30],// 可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字   
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        onBeforeRefresh: function () {  
        },  
        onSelectPage: function (pageNumber, pageSize) {//分页触发  
        	loadAllProjects(pageNumber, pageSize);  
        }
    });  
    var pager2=$('#templateList2').datagrid('getPager');
    pager2.pagination({
        total:0,
        rows : 0,
        pageNumber : 1,
        pageList : [10,20,30],// 可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字   
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        onBeforeRefresh: function () {  
        },  
        onSelectPage: function (pageNumber, pageSize) {//分页触发  
        	loadUploadProjects(pageNumber, pageSize);  
        }
    }); 
    var pager3=$('#templateList3').datagrid('getPager');
    pager3.pagination({
        total:0,
        rows : 0,
        pageNumber : 1,
        pageList : [10,20,30],// 可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字   
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        onBeforeRefresh: function () {  
        },  
        onSelectPage: function (pageNumber, pageSize) {//分页触发  
        	loadUnuploadProjects(pageNumber, pageSize);  
        }
    }); 
  
}



function intilizeNavigation()
{	
	var itemli1 = document.getElementById("allProjects");
	var itemli2 = document.getElementById("uploadProjects");
	var itemli3 = document.getElementById("unuploadProjects");
	var table1 = document.getElementById("divTemplateList1");
	var table2 = document.getElementById("divTemplateList2");		
	var table3 = document.getElementById("divTemplateList3");
	itemli1.onclick = function()
	{
		//加载全部的项目列表
		itemli1.style.background='#dbeef3';
		itemli2.style.background='';
		itemli3.style.background='';
		var pageNumber = $("#templateList1").datagrid("getPager").data("pagination").options.pageNumber;
		var pageSize = $("#templateList1").datagrid("getPager").data("pagination").options.pageSize;
		if(pageNumber == 0)
		{
			pageNumber = 1;
		}
		loadAllProjects(pageNumber,pageSize);
		table1.style.display = 'block';
		table2.style.display = 'none';
		table3.style.display = 'none';
	}	
	itemli2.onclick = function()
	{
		//加载已上传的项目列表
		itemli1.style.background='';
		itemli2.style.background='#dbeef3';
		itemli3.style.background='';
		var pageNumber = $("#templateList2").datagrid("getPager").data("pagination").options.pageNumber;
		var pageSize = $("#templateList2").datagrid("getPager").data("pagination").options.pageSize;
		if(pageNumber == 0)
		{
			pageNumber = 1;
		}
		loadUploadProjects(pageNumber,pageSize);
		table1.style.display = 'none';
		table2.style.display = 'block';
		table3.style.display = 'none';
	}
	itemli3.onclick = function()
	{
		//加载已上传的项目列表
		itemli1.style.background='';
		itemli2.style.background='';
		itemli3.style.background='#dbeef3';
		var pageNumber = $("#templateList3").datagrid("getPager").data("pagination").options.pageNumber;
		var pageSize = $("#templateList3").datagrid("getPager").data("pagination").options.pageSize;
		if(pageNumber == 0)
		{
			pageNumber = 1;
		}
		loadUnuploadProjects(pageNumber,pageSize);
		table1.style.display = 'none';
		table2.style.display = 'none';
		table3.style.display = 'block';
	}
	
}


function loadAllProjects(pageNumber,pageSize) 
{
	$.ajax({
		url : rootPath + '/project/getMainManagerProjectList.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		cache : false,
		data : {
            'pageNumber' : pageNumber,
            'pageSize' : pageSize
        },
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 //$('#templateList1').datagrid('loadData', data.result);
				 $("#templateList1").datagrid('loadData',pageData(data.result.rows,data.result.total));
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function loadUploadProjects(pageNumber, pageSize) 
{
	$.ajax({
		url : rootPath + '/project/getMainManagerUploadedProjectList.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		cache : false,
		data : {
            'pageNumber' : pageNumber,
            'pageSize' : pageSize
        },
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 //$('#templateList').datagrid('loadData', data.result);
				 $("#templateList2").datagrid('loadData',pageData(data.result.rows,data.result.total));
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function loadUnuploadProjects(pageNumber, pageSize) 
{
	$.ajax({
		url : rootPath + '/project/getMainManagerUnUploadedProjectList.ajax?version'
				+ new Date().getTime(),
		type : 'POST',
		dataType : 'json',
		cache : false,
		data : {
            'pageNumber' : pageNumber,
            'pageSize' : pageSize
        },
		success : function(data) {
			if (data.messageCode == "00000") 
			{
				 //$('#templateList').datagrid('loadData', data.result);
				 $("#templateList3").datagrid('loadData',pageData(data.result.rows,data.result.total));
				
			} else {
				alert(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function pageData(list,total){
    var obj=new Object(); 
    obj.total=total; 
    obj.rows=list; 
    return obj; 
}







