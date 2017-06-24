<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>总管理</title>
    <link type="text/css" href="<%=request.getContextPath()%>/css/styles.css" rel="Stylesheet" />
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
                        <span class="select">全部</span>
                        <span>已上传</span>
                        <span>未上传</span>
                    </div> 
                    <div class="fl operation-input"><span>搜索:</span><input class="input-style" type="" name="" > </div>
                </div>
                <div class="clear"></div>
                <table class="main-table" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th width="20%">项目名称</th>
                            <th width="10%">区域</th>
                            <th width="20%">医院名称</th>
                            <th width="20%">月份</th>
                            <th width="10%">是否上传</th>
                            <th width="20%">管理员</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>项目名称1</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称2</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称3</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称4</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称5</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称6</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称7</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称8</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称9</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                        <tr>
                            <td>项目名称10</td>
                            <td>区域</td>
                            <td>医院名称</td>
                            <td>月份</td>
                            <td>是否上传</td>
                            <td>管理员</td>
                        </tr>
                     </tbody>
                </table>
                <div class="fy-page">
                    <div class="digg fr">
                         <span class="disabled">&lt; </span>
                         <span class="current">1</span>
                         <a href="">2</a>
                         <a href="">3</a>
                         <a href="">4</a>
                         <a href="">5</a>
                         <a href="">6</a>
                         <a href="">7</a>
                         ...
                         <a href="">199</a>
                         <a href="">200</a>
                         <a href="">&gt; </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>