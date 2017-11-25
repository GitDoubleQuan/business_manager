package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuServiceImp;
import com.atguigu.util.MyUploadUtil;

@Controller
public class SpuController {

    @Autowired
    SpuServiceImp spuServiceImp;

    @RequestMapping("get_spu_test")
    @ResponseBody
    public List<T_MALL_PRODUCT> get_spu_test() {
        List<T_MALL_PRODUCT> list_spu = spuServiceImp.get_spu_test();
        return list_spu;
    }

    @RequestMapping("goto_spu")
    public String goto_spu() {
        return "manager_spu";
    }

    @RequestMapping("goto_spu_add")
    public String goto_spu_add(String success, ModelMap map) {
        map.put("success", success);
        return "manager_spu_add";
    }

    @RequestMapping("save_spu")
    public ModelAndView save_spu(T_MALL_PRODUCT spu, @RequestParam("files") MultipartFile[] files) {
        // 保存商品spu的业务

        // 上传图片，返回图片名称集合
        List<String> list_image_name = MyUploadUtil.upload_image(files);

        // 保存商品和商品图片信息
        spuServiceImp.save_spu(spu, list_image_name);

        ModelAndView modelAndView = new ModelAndView("redirect:/index.do");

        modelAndView.addObject("success", "添加成功");
        modelAndView.addObject("url", "goto_spu_add.do?success=success");
        modelAndView.addObject("title", "添加商品");

        return modelAndView;// "redirect:/goto_spu_add.do?success=添加成功";
    }

}
