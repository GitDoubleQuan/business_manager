package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.AttrServiceImp;
import com.atguigu.service.SkuServiceImp;

@Controller
public class SkuController {

	@Autowired
	AttrServiceImp attrServiceImp;

	@Autowired
	SkuServiceImp skuServiceImp;

	@RequestMapping("save_sku")
	public ModelAndView save_sku(T_MALL_SKU sku, MODEL_T_MALL_SKU_ATTR_VALUE list_attr_value) {

		skuServiceImp.save_sku(list_attr_value.getList_attr_value(), sku);
		ModelAndView modelAndView = new ModelAndView("redirect:/index.do");

		modelAndView.addObject("success", "添加库存信息成功");
		modelAndView.addObject("url", "goto_sku.do");
		modelAndView.addObject("title", "商品库存管理");

		return modelAndView;
	}

	@RequestMapping("sku_get_spu")
	@ResponseBody
	public List<T_MALL_PRODUCT> sku_get_spu(int pp_id, int class_1_id, int class_2_id) {

		List<T_MALL_PRODUCT> list_spu = skuServiceImp.sku_get_spu(class_2_id, class_1_id, pp_id);
		return list_spu;
	}

	@RequestMapping("goto_sku")
	public String goto_sku() {
		return "manager_sku";
	}

	@RequestMapping("get_attr_by_class_2_sku")
	public String get_attr_by_class_2_sku(int class_2_id, ModelMap map) {
		// 查询属性集合的业务
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_list_by_class_2(class_2_id);
		// 返回结果给ajax
		map.put("list_attr", list_attr);
		return "manager_attr_list_inner";
	}

}
