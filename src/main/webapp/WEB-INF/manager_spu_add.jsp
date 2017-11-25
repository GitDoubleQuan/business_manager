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
        function spu_add_img_click(index){

            // 调用按钮对应的file对象的点击事件
            $("#file_"+index).click();

        }

        function spu_add_img_change(index){
            // 获得图片缓存
            var blob = $("#file_"+index)[0].files[0];

            // 转化成url
            var url = window.URL.createObjectURL(blob);

            // 赋值给src
            $("#img_"+index).attr("src",url);


            // 判断用户当前点击的图片是不是组后一个按钮图片
            var length = $("#spu_img :file").length;
            if((length-1)==index){
                spu_add_img_add(index);
            }
        }

        function spu_add_img_add(index){
            var a = '<div id="spu_img_div_'+(index+1)+'" style="margin-left:10px;border:1px solid #333;width:80px;height:80px;float:left;">';
            var b = '<img id = "img_'+(index+1)+'" style="cursor:pointer" src="image/upload_hover.png" width="80px" height="80px" onclick="spu_add_img_click('+(index+1)+')"/>';
            var c = '<input id="file_'+(index+1)+'"  type="file" name="files" style="display:none;" onChange="spu_add_img_change('+(index+1)+')" /><br>';
            var d = '</div>';
            $("#spu_img").append(a+b+c+d);

        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
<form action="save_spu.do" method="post" enctype="multipart/form-data">
    <jsp:include page="manager_class_spu.jsp" />
    <hr>
    商品名称：<input name="shp_mch" type="text" />
    <hr>
    商品描述：<textarea  name="shp_msh" rows="2" cols="20"></textarea><br>
    商品图片：<br>
    <div id="spu_img">
        <div id="spu_img_div_0" style="border:1px solid #333;width:80px;height:80px;float:left;">
            <img id = "img_0" style="cursor:pointer" src="image/upload_hover.png" width="80px" height="80px" onclick="spu_add_img_click(0)"/>
            <input id="file_0"  type="file" name="files" style="display:none;" onChange="spu_add_img_change(0)" /><br>
        </div>
    </div>
    <input type="submit" value="提交"/>
</form>
</body>
</html>