package com.atguigu;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Shuangquan.Xu on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/spring.xml","/springMVC.xml"})
@ActiveProfiles("test")
public abstract class BaseFunctionalTestCase {
}
