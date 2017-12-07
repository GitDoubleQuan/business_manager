package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_CLASS_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchServiceInf {

	public List<OBJECT_T_MALL_CLASS_SKU> get_search_class(int class_2_id);

	public List<OBJECT_T_MALL_CLASS_SKU> get_search_attr(String string, int class_2_id,
														 List<T_MALL_SKU_ATTR_VALUE> list_attr_value);

	List<T_MALL_SKU> get_list_sku_by_spu_id(int spu_id);

}
