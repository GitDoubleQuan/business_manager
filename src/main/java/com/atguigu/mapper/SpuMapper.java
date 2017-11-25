package com.atguigu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuMapper {

    void insert_spu(T_MALL_PRODUCT spu);

    void insert_spu_image(@Param("spu") T_MALL_PRODUCT spu, @Param("list_image_name") List<String> list_image_name);

    List<T_MALL_PRODUCT> select_spu_test();
}