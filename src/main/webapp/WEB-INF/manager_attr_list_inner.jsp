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
        function show_sku_check(attr_id,checked){
            if(checked){
                $("#sku_attr_id_"+attr_id).show();
            }else{
                $("#sku_attr_id_"+attr_id).hide();
            }
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
属性列表页<br>
<c:forEach items="${list_attr}" var="attr" varStatus="index">
    <input type="checkbox" name="list_attr_value[${index.index}].shxm_id" value="${attr.id}" onchange="show_sku_check(${attr.id},this.checked)"/>${attr.shxm_mch}
</c:forEach>
<hr>
<c:forEach items="${list_attr}" var="attr" varStatus="index">
    <div id="sku_attr_id_${attr.id}" style="display:none;">
        <c:forEach items="${attr.list_value}" var="val">
            <input type="radio" name="list_attr_value[${index.index}].shxzh_id" value="${val.id}">${val.shxzh}${val.shxzh_mch}
        </c:forEach>
    </div>
    <br>
</c:forEach>
<hr>
商品库存名称：<input name="sku_mch" type="text" /><br>
商品库存数量：<input name="kc" type="text" /><br>
商品库存价格：<input name="jg" type="text" /><br>
商品库存地址：<input name="kcdz" type="text" /><br>
<input type="submit" value="提交"/>
</body>
</html>