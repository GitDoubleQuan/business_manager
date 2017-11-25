package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SpuMapper;

@Service
public class SpuServiceImp implements SpuServiceInf {

    @Autowired
    private SpuMapper spuMapper;

    public void save_spu(T_MALL_PRODUCT spu, List<String> list_image_name) {

        spu.setShp_tp(list_image_name.get(0));
        spuMapper.insert_spu(spu);

        spuMapper.insert_spu_image(spu, list_image_name);

    }

    @Override
    public List<T_MALL_PRODUCT> get_spu_test() {
        List<T_MALL_PRODUCT> list_spu = spuMapper.select_spu_test();
        return list_spu;
    }

}
