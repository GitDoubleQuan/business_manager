package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuServiceInf {

	List<T_MALL_PRODUCT> sku_get_spu(int class_2_id, int class_1_id, int pp_id);

	void save_sku(List<T_MALL_SKU_ATTR_VALUE> list_av, T_MALL_SKU sku);

}
