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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        function get_spu_test(url){
            // 向数据表格区域添加返回的spu商品信息列表
            $('#dg').datagrid({
                url:url,
                columns:[[
                    {field:'id',title:'商品编号',width:100},
                    {field:'shp_mch',title:'商品名称',width:100},
                    {field:'shp_msh',title:'商品描述',width:200},
                    {field:'chjshj',title:'创建时间',width:300,
                        formatter: function(value,row,index){
                            var d = new Date(value);
                            return d.toLocaleString();
                        }
                    }
                ]]
            });
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
<hr>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',split:true" style="height:50px">
    </div>
    <div data-options="region:'west',split:true" style="width:100px">
        <a href="javascript:add_tabs('goto_spu_add.do','商品添加');">商品添加</a><br>
        <a href="javascript:get_spu_test('get_spu_test.do');">商品查询</a>
    </div>
    <div data-options="region:'center'">
        <div id="dg" class="easyui-datagrid">

        </div>
    </div>
</div>

</body>
</html>