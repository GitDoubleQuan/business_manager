package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.bean.OBJECT_T_MALL_KEYWORDS_SKU;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_CLASS_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SearchMapper;

@Service
public class SearchServiceImp implements SearchServiceInf {

	@Autowired
	SearchMapper searchMapper;

	public List<OBJECT_T_MALL_KEYWORDS_SKU> get_search_class(int class_2_id) {
		List<OBJECT_T_MALL_KEYWORDS_SKU> list_sku = searchMapper.select_class_search(class_2_id);
		return list_sku;
	}

	public List<OBJECT_T_MALL_CLASS_SKU> get_search_attr(String order, int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("class_2_id", class_2_id);
		if (StringUtils.isNotBlank(order)) {// order!=null&&!order.qeuals("")
			map.put("order", order);
		}

		if (list_attr_value != null && list_attr_value.size() > 0) {
			// 根据分类属性交叉检索的动态sql
			StringBuffer sql = new StringBuffer();
			sql.append(" and sku.id in ");
			sql.append(" (select sku0.sku_id from ");

			// 拼接动态sql
			for (int i = 0; i < list_attr_value.size(); i++) {
				sql.append(" (select sku_id from t_mall_sku_attr_value where shxm_id = "
						+ list_attr_value.get(i).getShxm_id() + " and shxzh_id = "
						+ list_attr_value.get(i).getShxzh_id() + ") sku" + i + " ");
				if (list_attr_value.size() > 1 && i < (list_attr_value.size() - 1)) {
					sql.append(" , ");
				}

			}

			if (list_attr_value.size() > 1) {
				sql.append(" where ");
				for (int j = 0; j < list_attr_value.size(); j++) {

					if (j < list_attr_value.size() - 1) {
						sql.append(" sku" + j + ".sku_id=sku" + (j + 1) + ".sku_id");
					}

					if (list_attr_value.size() > 2 && j < list_attr_value.size() - 2) {
						sql.append(" and ");
					}
				}
			}

			sql.append(" ) ");

			map.put("sql", sql.toString());

		}

		List<OBJECT_T_MALL_CLASS_SKU> list_sku = searchMapper.select_attr_search(map);

		return list_sku;

	}

	@Override
	public List<T_MALL_SKU> get_list_sku_by_spu_id(int spu_id) {
		List<T_MALL_SKU> list_sku = searchMapper.select_list_sku_by_spu_id(spu_id);
		return list_sku;
	}

}
