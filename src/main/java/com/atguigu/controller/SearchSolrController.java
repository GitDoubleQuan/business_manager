package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_KEYWORDS_SKU;
import com.atguigu.mapper.SearchMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by Shuangquan.Xu on 2017/12/5.
 */
@Controller
public class SearchSolrController {

    @Autowired
    private SearchMapper searchMapper;

    @RequestMapping("reflashSolr.do")
    @ResponseBody
    public String reflashSolr() throws IOException, SolrServerException {
        // 获得solr客户端
        HttpSolrServer solr = new HttpSolrServer("http://localhost:8983/solr/sku");
        solr.setParser(new XMLResponseParser());
        solr.setConnectionTimeout(60000);

        // 商品 sku列表集合
        // 配置solr的数据和java数据的字段的对应
        List<OBJECT_T_MALL_KEYWORDS_SKU> list_sku = searchMapper.select_class_search(28);

        // 导入solr,刷新solr数据时注意清除之前的数据内容
        solr.deleteByQuery("*:*");
        solr.addBeans(list_sku);
        solr.commit();

        // 查询solr数据
        SolrQuery q = new SolrQuery();
        q.setQuery("*:*");
        q.setRows(30);
        QueryResponse query = solr.query(q);
        List<OBJECT_T_MALL_KEYWORDS_SKU> beans = query.getBeans(OBJECT_T_MALL_KEYWORDS_SKU.class);

        System.out.println(beans);

        return "";
    }


}
