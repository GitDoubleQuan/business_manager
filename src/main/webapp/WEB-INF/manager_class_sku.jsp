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
        $(function (){
            $.getJSON("js/json/class_1.js",function(data){
                // 用js循环json的分类集合
                $(data).each(function(i,json){
                    // 将分类集合的内容生成<option>对象加载到下拉列表中
                    $("#class_1_select_sku").append("<option value="+json.id+">"+json.flmch1+"</option>");
                });
            });
        });

        function get_class_2_sku(class_1_id){
            // $("#class_1_select option:selected").val();
            // $("#class_1_select").val();
            $.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
                // 用js循环json的分类集合
                $("#class_2_select_sku").empty();
                $(data).each(function(i,json){
                    // 将分类集合的内容生成<option>对象加载到下拉列表中
                    $("#class_2_select_sku").append("<option value="+json.id+">"+json.flmch2+"</option>");
                });
            });

            get_tm_sku(class_1_id);
        }

        function get_tm_sku(class_1_id){
            // $("#class_1_select option:selected").val();
            // $("#class_1_select").val();
            $.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
                // 用js循环json的分类集合
                $("#tm_select_sku").empty();
                $(data).each(function(i,json){
                    // 将分类集合的内容生成<option>对象加载到下拉列表中
                    $("#tm_select_sku").append("<option value="+json.id+">"+json.ppmch+"</option>");
                });
            });
        }

        function get_attr_by_class_2_sku(class_2_id){
            // 异步请求分类属性集合列表
            $.post("get_attr_by_class_2_sku.do",{class_2_id:class_2_id},function(data){
                $("#manager_attr_list_inner_sku").html(data);
            });

        }

        function get_spu_by_id(tm_id){
            var class_1_id = $("#class_1_select_sku").val();
            var class_2_id = $("#class_2_select_sku").val();
            $.post("sku_get_spu.do",{pp_id:tm_id,class_1_id:class_1_id,class_2_id:class_2_id},function(data){
                console.debug(data);
                $(data).each(function(i,json){
                    $("#spu_select_sku").append("<option value="+json.id+">"+json.shp_mch+"</option>");
                });
            });
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
<select name="flbh1" id="class_1_select_sku" onchange="get_class_2_sku(this.value)">
    <option>请选择</option>
</select>
<select name="flbh2" id="class_2_select_sku" onchange="get_attr_by_class_2_sku(this.value)">
    <option>请选择</option>
</select>
<select name="pp_id" id="tm_select_sku"  onchange="get_spu_by_id(this.value)">
    <option>请选择</option>
</select>
<select name="shp_id" id="spu_select_sku">
    <option>请选择</option>
</select>
</body>
</html>