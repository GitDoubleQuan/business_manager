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
        function goto_attr_add(){
            // 被选中的二级分类id
            var class_2_id = $("#class_2_select_attr").val();
            // 被选中的二级分类名称
            var class_2_name = $("#class_2_select_attr option:selected").text();
            alert(class_2_id+class_2_name);
            add_tabs("goto_attr_add.do?class_2_id="+class_2_id+"&class_2_name="+class_2_name,"添加分类属性");
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
商品属性管理<br>
<jsp:include page="manager_class_attr.jsp" />
<hr>
<div id="manager_attr_list_inner">

</div>
<a href="javascript:goto_attr_add();">添加分类属性</a>
</body>
</html>