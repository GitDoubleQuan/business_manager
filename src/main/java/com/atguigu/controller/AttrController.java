package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.service.AttrServiceImp;

@Controller
public class AttrController {

	@Autowired
	AttrServiceImp attrServiceImp;

	@RequestMapping("save_attr")
	public ModelAndView save_attr(MODEL_OBJECT_T_MALL_ATTR list_attr, int class_2_id, String class_2_name) {

		// 调用业务层保存属性
		attrServiceImp.save_attr(list_attr.getList_attr(), class_2_id);

		ModelAndView modelAndView = new ModelAndView("redirect:/index.do");
		modelAndView.addObject("class_2_id", class_2_id);
		modelAndView.addObject("class_2_name", class_2_name);

		modelAndView.addObject("success", "添加分类属性成功");
		modelAndView.addObject("url", "goto_attr_add.do?class_2_id=" + class_2_id + "&class_2_name=" + class_2_name);
		modelAndView.addObject("title", "添加分类属性");

		return modelAndView;
	}

	@RequestMapping("goto_attr_add")
	public String goto_attr_add(int class_2_id, String class_2_name, ModelMap map) {
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		return "manager_attr_add";
	}

	@RequestMapping("goto_attr")
	public String goto_attr() {
		return "manager_attr";
	}

	@RequestMapping("get_attr_by_class_2")
	public String get_attr_by_class_2(int class_2_id, ModelMap map) {
		// 查询属性集合的业务
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_list_by_class_2(class_2_id);
		// 返回结果给ajax
		map.put("list_attr", list_attr);
		return "manager_attr_list";
	}

	@RequestMapping("get_attr_by_class_2_json")
	@ResponseBody
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_json(int class_2_id, ModelMap map) {
		// 查询属性集合的业务
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_list_by_class_2(class_2_id);

		return list_attr;
	}

}
