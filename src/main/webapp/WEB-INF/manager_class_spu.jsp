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
            $("#class_1_select_spu").combobox(
                    {
                        url:"js/json/class_1.js",
                        valueField:"id",
                        textField:"flmch1",
                        width:100,
                        onSelect:function(){
                            // $("#class_1_select_spu option:selected").val();
                            // $("#class_1_select_spu").val();
                            var class_1_id = $(this).combobox('getValue');
                            $("#class_2_select_spu").combobox({
                                url:"js/json/class_2_"+class_1_id+".js",
                                valueField:"id",
                                textField:"flmch2",
                                width:100,
                                onSelect:function(){
                                    var class_2_id = $("#class_2_select_spu").combobox("getValue");
                                    get_attr_by_class_2(class_2_id);
                                }
                            });

                            $("#tm_select_spu").combobox({
                                url:"js/json/tm_class_1_"+class_1_id+".js",
                                valueField:"id",
                                textField:"ppmch",
                                width:100
                            });
                        }
                    }
            );

        });

        function get_class_2(class_1_id){
            // $("#class_1_select_spu option:selected").val();
            // $("#class_1_select_spu").val();
            $.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
                // 用js循环json的分类集合
                $("#class_2_select_spu").empty();
                $(data).each(function(i,json){
                    // 将分类集合的内容生成<option>对象加载到下拉列表中
                    $("#class_2_select_spu").append("<option value="+json.id+">"+json.flmch2+"</option>");
                });
            });

            get_tm(class_1_id);
        }

        function get_tm(class_1_id){
            // $("#class_1_select_spu option:selected").val();
            // $("#class_1_select_spu").val();
            $.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
                // 用js循环json的分类集合
                $("#tm_select_spu").empty();
                $(data).each(function(i,json){
                    // 将分类集合的内容生成<option>对象加载到下拉列表中
                    $("#tm_select_spu").append("<option value="+json.id+">"+json.ppmch+"</option>");
                });
            });
        }

        function get_attr_by_class_2(class_2_id){

            // 异步请求分类属性集合列表
            /* 		$.post("get_attr_by_class_2.do",{class_2_id:class_2_id},function(data){
             $("#manager_attr_list_inner").html(data);
             }); */
            $("#manager_attr_list_inner").datagrid({
                url:'get_attr_by_class_2_json.do',
                queryParams:{class_2_id:class_2_id},
                columns:[[
                    {field:'id',title:'属性编号',width:100,checkbox:true},
                    {field:'shxm_mch',title:'属性名称',width:100},
                    {field:'chjshj',title:'创建时间',width:200},
                    {field:'list_value',title:'属性值',width:300,
                        formatter: function(value,row,index){
                            var v = "";
                            $(value).each(function(i,json){
                                v = v+ " "+json.shxzh+json.shxzh_mch;
                            });
                            return v;
                        }
                    }
                ]]
            });

        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
<select class="easyui-combobox" name="flbh1" id="class_1_select_spu" onchange="get_class_2(this.value)">
    <option>请选择</option>
</select>
<select class="easyui-combobox" name="flbh2" id="class_2_select_spu" onchange="get_attr_by_class_2(this.value)">
    <option>请选择</option>
</select>
<select class="easyui-combobox" name="pp_id" id="tm_select_spu">
    <option>请选择</option>
</select>
</body>
</html>