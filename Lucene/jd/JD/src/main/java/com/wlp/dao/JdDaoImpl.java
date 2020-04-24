package com.wlp.dao;

import com.wlp.model.ProductModel;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdDaoImpl implements  JdDao {
    //索引库
    @Autowired
    private SolrClient solrClient ;

    public List<ProductModel> selectProductListByQuery(String queryStr, String catalog_name, String price, String sort) throws Exception{
       System.out.println("solrClient---------:"+solrClient);
        SolrQuery solrQuery = new SolrQuery();
        //q:对应的是solrAdmin中query的参数（q、fq、sort、...等参数）
        solrQuery.set("q",queryStr);
        if(null != price && !"".equals(price)){
            //0-9   50-*
            String[] p = price.split("-");
            solrQuery.set("fq", "product_price:[" + p[0] + " TO " + p[1] + "]");
        }
        //排序
        if("1".equals(sort)){
            solrQuery.setSort("product_price", SolrQuery.ORDER.desc);
        }else{
            solrQuery.setSort("product_price", SolrQuery.ORDER.asc);
        }
        //默认域
        solrQuery.set("df", "product_keywords");
//        solrQuery.set("hl", true);
        //执行查询 第一个参数是collection(索引库)，就是我们在solr中创建的core
        QueryResponse response = solrClient.query("testcore01",solrQuery);
        // 获取结果集
        SolrDocumentList results = response.getResults();
        List<ProductModel> productModelList = new ArrayList<>();
        for (SolrDocument result : results) {

            ProductModel productModel = new ProductModel();
            productModel.setPid(result.get("id").toString());
            productModel.setCatalog_name(result.get("product_catalog_name").toString());
            productModel.setName(result.get("product_name").toString());
            productModel.setPrice(Float.parseFloat(result.get("product_price").toString()));
            productModel.setPicture(result.get("product_picture").toString());
            productModelList.add(productModel);
            // SolrDocument 数据结构为Map
            System.out.println(result);
            System.out.println("id:"+result.get("id"));;
            System.out.println("product_name:"+result.get("product_name"));;
            System.out.println("product_catalog_name:"+result.get("product_catalog_name"));;
        }
        System.out.println("productModelList 集合大小："+productModelList.size());
        return  productModelList ;
    }
}
