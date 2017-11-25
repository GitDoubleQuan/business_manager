package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SkuMapper;

@Service
public class SkuServiceImp implements SkuServiceInf {

	@Autowired
	SkuMapper skuMapper;

	public List<T_MALL_PRODUCT> sku_get_spu(int class_2_id, int class_1_id, int pp_id) {

		return skuMapper.select_sku_get_spu(class_1_id, class_2_id, pp_id);
	}

	public void save_sku(List<T_MALL_SKU_ATTR_VALUE> list_av, T_MALL_SKU sku) {
		skuMapper.insert_sku(sku);

		Map<Object, Object> map = new HashMap<Object, Object>();

		map.put("sku_id", sku.getId());
		map.put("shp_id", sku.getShp_id());
		map.put("list_av", list_av);
		skuMapper.insert_sku_av(map);

	}

}
