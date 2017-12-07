<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>">
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        $(function(){
            var url = "${url}";
            var title = "${title}";
            var success = "${success}";
            add_tabs(url,title);
        });
        function add_tabs(url,title){
            // 判断本次添加的页面是否存在，如果不存在
            var b = $('#tt').tabs('exists',title);
            if(!b){
                $.post(url,function(data){
                    $('#tt').tabs('add',{
                        title:title,
                        content:data,
                        closable:true,
                        tools:[{
                            iconCls:'icon-mini-refresh',
                            handler:function(){
                            }
                        }]
                    });
                });
            }else{
                // 定位到指定的选项卡
                $('#tt').tabs('select',title);
            }
        }
        function add_tabs2(url,title){
            $('#tt').tabs('add',{
                title:title,
                href:url,
                closable:true,
                tools:[{
                    iconCls:'icon-mini-refresh',
                    handler:function(){
                        alert('refresh');
                    }
                }]
            });
        }
    </script>
    <title>硅谷商城</title>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
<div data-options="region:'west',split:true,title:'West'" style="width:200px;padding:10px;">
    <div class="easyui-accordion">
        <div title="内容管理" data-options="iconCls:'icon-search'" >
            <ul class="easyui-tree">
                <li>
                    <span>商品管理</span>
                    <ul>
                        <li>
                            <a href="javascript:add_tabs('goto_spu.do','商品信息管理');" >商品信息管理</a>
                        </li>
                        <li>
                            <a href="javascript:add_tabs('goto_attr.do','商品属性管理')" >商品属性管理</a>
                        </li>
                        <li>
                            <a href="javascript:add_tabs('goto_sku.do','商品库存管理')" >商品库存管理</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="easyui-accordion">
        <div title="检索管理" data-options="iconCls:'icon-ok'" >
            <ul class="easyui-tree">
                <li>
                    <span>缓存管理</span>
                    <ul>
                        <li>
                            <a href="javascript:add_tabs('goto_search_cache.do','商品检索缓存');" >商品检索缓存</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
<div data-options="region:'center',title:'Center'">
    <div class="easyui-tabs" id="tt" style="height:700px">
    </div>
</div>
</body>
</html>