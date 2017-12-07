package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.bean.OBJECT_T_MALL_CLASS_SKU;
import com.atguigu.bean.OBJECT_T_MALL_KEYWORDS_SKU;
import com.atguigu.bean.T_MALL_SKU;

public interface SearchMapper {


	List<OBJECT_T_MALL_KEYWORDS_SKU> select_class_search(int class_2_id);

	List<OBJECT_T_MALL_CLASS_SKU> select_attr_search(Map<Object, Object> map);

	List<T_MALL_SKU> select_list_sku_by_spu_id(int spu_id);



}
