<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作员</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/styles.css" rel="Stylesheet" />
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
            <div class="title"><span>项目1名称</span></div>
            <div class="part-two">
                <div class="left-tab">
                    <ul class="left-tab-ul">
                        <li class="select">上传配置</li>
                        <li>下载配置</li>
                    </ul>
                </div>
                <!-- 上传列表 -->
                <div class="right-tab-con tab-upload">
                   <div class="operation-list">
                       <div class="btn-group fl">
                           <button class="btn-normal">上传</button>
                        </div> 
                    </div>
                   <div class="clear"></div>
                   <table class="main-table" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th width="40%">文件名</th>
                                <th width="40%">上传时间</th>
                                <th width="20%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-delete">删除</button></td>
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
                <!-- 下载列表 -->
                <div class="right-tab-con tab-download" style="display: none;">
                    <!-- <div class="operation-list">
                        <div class="btn-group fl">
                            <span class="select">全部</span>
                            <span>已上传</span>
                            <span>未上传</span>
                        </div> 
                        <div class="fl operation-input"><span>搜索:</span><input class="input-style" type="" name="" > </div>
                    </div>
                    <div class="clear"></div> -->
                     <table class="main-table" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th width="40%">文件名</th>
                                <th width="40%">上传时间</th>
                                <th width="20%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">下载</button></td>
                            </tr>
                            <tr>
                                <td ><span style="width:350px;">区域</span></td>
                                <td><span>5月</span></td>
                                <td><button class="btn-add">删除</button></td>
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
    </div>
</body>
</html>