package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_KEYWORDS_SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.OBJECT_T_MALL_CLASS_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrServiceInf;
import com.atguigu.service.SearchServiceInf;
import com.atguigu.util.MyCacheUtil;

@Controller
public class SearchCacheController {

	@Autowired
	SearchServiceInf searchServiceInf;

	@Autowired
	AttrServiceInf attrServiceInf;

	@RequestMapping("goto_search_cache")
	public String goto_search_cache() {

		return "manager_search_redis";
	}

	@RequestMapping(value = "refresh_attr_search", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refresh_attr_search(int class_2_id, @RequestParam("list_attr[]") int[] list_attr) {

		long count = 0l;

		// 循环属性查询属性值
		for (int i = 0; i < list_attr.length; i++) {
			List<Integer> list_value = attrServiceInf.get_value_by_attr(list_attr[i]);
			// 循环属性值查询sku集合

			for (int j = 0; j < list_value.size(); j++) {
				String key = "attr_" + class_2_id + "_" + list_attr[i] + "_" + list_value.get(j);
				List<T_MALL_SKU_ATTR_VALUE> list_attr_value = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
				T_MALL_SKU_ATTR_VALUE av = new T_MALL_SKU_ATTR_VALUE();
				av.setShxm_id(list_attr[i]);
				av.setShxzh_id(list_value.get(j));
				list_attr_value.add(av);
				List<OBJECT_T_MALL_CLASS_SKU> list_sku = searchServiceInf.get_search_attr("", class_2_id,
						list_attr_value);
				// 循环sku，刷新reids
				int refresh_count = MyCacheUtil.refresh_redis_key(key, list_sku);
				count = count + refresh_count;
			}
		}

		return "刷新了" + count + "条分类检索商品缓存";
	}

	@RequestMapping(value = "refresh_class_2_redis", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refresh_class_2_redis(int class_2_id) {

		// 得到二级分类的id，拼接成，key
		String key = "class_2_" + class_2_id;

		// 根据二级分类的id查询sku商品集合，value
		List<OBJECT_T_MALL_KEYWORDS_SKU> list_sku = searchServiceInf.get_search_class(class_2_id);

		// 将key和value保存在redis的集合中
		int count = MyCacheUtil.refresh_redis_key(key, list_sku);

		return "刷新了" + count + "条分类检索商品缓存";
	}

}
