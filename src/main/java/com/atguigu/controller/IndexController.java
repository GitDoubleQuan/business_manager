package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shuangquan.Xu on 2017/11/24.
 */
@Controller
public class IndexController {

    //dev
    @RequestMapping("index")
    public String index(ModelMap map, String url, String title, String success) {
        map.put("url", url);
        map.put("title", title);
        map.put("success", success);
        return "manager_index";
    }
}
