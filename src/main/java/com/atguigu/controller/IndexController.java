package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shuangquan.Xu on 2017/11/24.
 */
@Controller
public class IndexController {


    //测试分支合并
    //dev
    //git pull
    //test push22222zhangsan
    //测试上游分支
    //push 冲突
    //王五
    //master
    @RequestMapping("index")
    public String index(ModelMap map, String url, String title, String success) {
        map.put("url", url);
        map.put("title", title);
        map.put("success", success);
        return "manager_index";
    }
}
