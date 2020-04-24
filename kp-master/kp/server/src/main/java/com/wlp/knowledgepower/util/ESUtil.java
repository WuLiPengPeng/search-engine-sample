package com.wlp.knowledgepower.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: wlp
 * @create: 2020-02-27 19:18
 * @description: es 工具类
 **/
public class ESUtil {
    private static Logger log = Logger.getLogger(ESUtil.class);

    public static ESUtil eSUtil = null ;
    private ESUtil(){
    }

    public static ESUtil getInstance(){
        if(eSUtil == null){
            synchronized (ESUtil.class){
                if (eSUtil == null){
                    eSUtil = new ESUtil();
                }
            }
        }
        return eSUtil;
    }

    /**
     * 参数校验
     * @param parameters
     * @return
     */
    public boolean parameterCheck(String ... parameters){
        if(parameters == null) return false ;

        for(String parameter : parameters){
            if(StringUtils.isEmpty(parameter)){
                return false ;
            }
        }
        return true;
    }

    /**
     * 检查是否是支持的文档数据
     * 支持map,json字符串
     *
     * @param docData 文档数据
     * @return
     */
    public boolean isSupportedDocData(Object docData){
        //map
        if(docData instanceof Map){
            return true ;
        }
        //json
        if(docData instanceof String){
            try {
                JSONObject.parseObject(docData.toString());
                return true ;
            }catch(Exception e){
            }
        }
        return false;
    }

    /**
     * 同步方式
     * 创建索引，插入文档数据(map,json字符串)
     * @param indexName
     * @param docId
     * @param data
     * @return
     * @throws Exception
     */
    public IndexResponse createIndex(String indexName,String docId,Object data) throws Exception {
        IndexRequest indexRequest = createIndexCombiningParam(indexName, docId, data, null);
        RestHighLevelClient client = ESPoolUtil.getClient();
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse;
    }

    /**同步方式
     * 创建索引，不插入文档数据
     * @param indexName 索引名称
     * @param docId 文档id
     * @return
     */

    public IndexResponse createIndex(String indexName,String docId) throws Exception {
        IndexResponse indexResponse = createIndex(indexName, docId, null);
        return  indexResponse ;
    }

    /**
     * 异步方式
     * 创建索引，不插入文档数据
     *
     * @param indexName 索引名称
     * @param docId 文档id
     * @param listener 监听器
     * @throws Exception
     */
    public void createIndexAsync(String indexName,String docId,ActionListener<IndexResponse> listener) throws Exception {
        createIndexAsync(indexName, docId, null,listener);
    }

    /**
     * 异步方式
     * 创建索引，插入文档数据(map,json字符串)
     *
     * @param indexName 索引名称
     * @param docId 文档id
     * @param data 文档数据
     * @param listener 监听器
     * @throws Exception
     */
    public void createIndexAsync(String indexName,String docId,Object data,ActionListener<IndexResponse> listener) throws Exception {
        IndexRequest indexRequest = createIndexCombiningParam(indexName, docId, data, listener);
        RestHighLevelClient client = ESPoolUtil.getClient();
        client.indexAsync(indexRequest, RequestOptions.DEFAULT,listener);
    }

    /**
     * 创建索引请求，组装数据
     * @param indexName
     * @param docId
     * @param data
     * @param listener
     * @return
     */
    public IndexRequest createIndexCombiningParam(String indexName, String docId, Object data, ActionListener<IndexResponse> listener){
        if(parameterCheck(indexName,docId)){
            log.error("createIndex() Parameter is null or empty");
            return null;
        }
        /*
         * 从ES 7.0.0开始，移除Type（类型）这个概念,Type 字段那里变为固定值 _doc.
         * http://<ip>:<port>/<索引>/_doc/<文档ID>
         */
        IndexRequest indexRequest = new IndexRequest(indexName);
        indexRequest.id(docId);
        if(data != null){
            if(isSupportedDocData(data)){
                indexRequest.source(data);
            }else{
                log.error("Not supported document data ");
                return null;
            }
        }
        return indexRequest;
    }

    /**
     * 同步方式
     * 获取对应索引及id的文档
     * @param indexName 缩索引名称
     * @param docId 文档Id
     * @return
     * @throws Exception
     */
    public GetResponse getDoc(String indexName,String docId) throws Exception {
        GetRequest getRequest = new GetRequest(indexName,docId);
        RestHighLevelClient client = ESPoolUtil.getClient();
        GetResponse getResponse =  client.get(getRequest, RequestOptions.DEFAULT);
        return getResponse ;
    }
    /**
     * 异步方式
     * 获取对应索引及id的文档
     * @param indexName 缩索引名称
     * @param docId 文档Id
     * @return
     * @throws Exception
     */
    public void getDocAsync(String indexName,String docId,ActionListener<GetResponse> listener) throws Exception {
        GetRequest getRequest = new GetRequest(indexName,docId);
        RestHighLevelClient client = ESPoolUtil.getClient();
        client.getAsync(getRequest, RequestOptions.DEFAULT,listener);
    }

    /**
     * 同步方式
     * 检测文档是否存在
     * @param indexName 索引名称
     * @param docId 文档id
     * @throws Exception
     */
    public boolean docExists(String indexName,String docId) throws Exception {
        GetRequest getRequest = docExistsCombiningParam(indexName, docId);
        RestHighLevelClient client = ESPoolUtil.getClient();
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        return  exists ;
    }
    /**
     * 异步方式
     * 检测文档是否存在
     * @param indexName 索引名称
     * @param docId 文档id
     * @param listener 监听器
     * @throws Exception
     */
    public void docExistsAsync(String indexName,String docId,ActionListener<Boolean> listener) throws Exception {
        GetRequest getRequest = docExistsCombiningParam(indexName, docId);
        RestHighLevelClient client = ESPoolUtil.getClient();
        client.existsAsync(getRequest, RequestOptions.DEFAULT,listener);
    }

    public GetRequest docExistsCombiningParam(String indexName,String docId) throws Exception {
        GetRequest getRequest = new GetRequest(indexName,docId);
        //禁用提取_source
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        //禁用提取存储的字段
        getRequest.storedFields("_none_");

        return getRequest;
    }

    /**
     * 同步方式
     * 删除文档
     * @param indexName 索引名称
     * @param docId 文档id
     * @return
     * @throws Exception
     */
    public DeleteResponse deleteDoc (String indexName,String docId) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(indexName,docId);
        RestHighLevelClient client = ESPoolUtil.getClient();
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse;
    }

    /**
     * 异步方式
     * 删除文档
     * @param indexName 索引名称
     * @param docId 文档id
     * @param listener 监听器
     * @return
     * @throws Exception
     */
    public void deleteDocAsync (String indexName,String docId,ActionListener<DeleteResponse> listener) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(indexName,docId);
        RestHighLevelClient client = ESPoolUtil.getClient();
        client.deleteAsync(deleteRequest, RequestOptions.DEFAULT,listener);
    }

    /**
     * 查询文档
     *
     * @param indexName
     * @param keyword
     * @return
     * @throws Exception
     */
    public SearchResponse searchDoc(String indexName,String keyword) throws Exception {

        SearchRequest searchRequest = new SearchRequest(indexName);
        //使用默认选项创建一个SearchSourceBuilder。
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置查询。成为任何类型的QueryBuilder
        searchSourceBuilder.query(QueryBuilders.termQuery("message", "trying"));
        //设置from选项，该选项确定要开始搜索的结果索引。默认值为0。
        searchSourceBuilder.from(0);
        //设置确定要返回的搜索结果数量的大小选项。默认为10。
        searchSourceBuilder.size(5);
        //设置一个可选的超时，控制允许搜索的时间。
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);
        RestHighLevelClient client = ESPoolUtil.getClient();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return searchResponse;
    }
}
