package com.atguigu.mapper;

import com.atguigu.BaseFunctionalTestCase;
import com.atguigu.bean.T_MALL_PRODUCT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Shuangquan.Xu on 2017/11/25.
 */
public class MapperTest extends BaseFunctionalTestCase {

    @Autowired
    private SpuMapper spuMapper;

    @Test
    public void test(){
        List<T_MALL_PRODUCT> t_mall_products = spuMapper.select_spu_test();
        System.out.println(t_mall_products);
    }
}
