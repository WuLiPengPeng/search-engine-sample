package com.wlp;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SolrjManager {

    private static final  String baseUrl = "http://www.wllife.top:8014/solr";

    /**
     *获取一个solr客户端连接
     * @return
     */
    public HttpSolrClient getSolrClient(){
        HttpSolrClient solrClient = new HttpSolrClient.Builder(baseUrl).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();
        return  solrClient;
    }

    @Test
    public void testQuery() throws IOException, SolrServerException {
        //获得一个solr客户端连接
        HttpSolrClient solrClient = getSolrClient();

        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.set("q", "product_catalog_name:金属");
        solrQuery.set("q", "帽子");
        //默认域
//        solrQuery.set("df","product_catalog_name");
        solrQuery.set("df","product_keywords");
        QueryResponse response = solrClient.query("testcore01", solrQuery);



//        // 定义查询条件
//        Map<String, String> params = new HashMap<String, String>();
//        //q:对应的是solrAdmin中query的参数（q、fq、sort、...等参数）
//        params.put("q", "product_catalog_name:金属");
//        //查询所有
//        //params.put("q", "*:*");
//        SolrParams mapSolrParams = new MapSolrParams(params);
//        //执行查询 第一个参数是collection(索引库)，就是我们在solr中创建的core
//        QueryResponse response = solrClient.query("testcore01", mapSolrParams);
        // 获取结果集
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            // SolrDocument 数据结构为Map
            System.out.println(result);
            System.out.println("id:"+result.get("id"));;
            System.out.println("product_name:"+result.get("product_name"));;
            System.out.println("product_catalog_name:"+result.get("product_catalog_name"));;
        }
    }

    /**
     * 增加记录
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testAdd() throws IOException, SolrServerException {
        HttpSolrClient solrClient = getSolrClient();
        // 定义数据 solr输入文档 数据结构为Map
        SolrInputDocument inputDocument = new SolrInputDocument();
        inputDocument.addField("id","123");
        inputDocument.addField("producr_name", "角色名称");
        // 执行添加 ps：如果id相同，则执行更新操作
        // 要指定操作的collection 就是solr-home下定义的core
        UpdateResponse add = solrClient.add("testcore01", inputDocument);
        //提交添加/更新
        solrClient.commit("testcore01");
    }

    /**
     * 删除记录
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void testDelete() throws IOException, SolrServerException {
        HttpSolrClient solrClient = getSolrClient();
        // 通过id删除 执行要删除的collection（core）
        solrClient.deleteById("testcore01", "123");
        // 还可以通过查询条件删除
        // solrClient.deleteByQuery("testcore01", "查询条件");
        // 提交删除
        solrClient.commit("testcore01");
    }

}
