package com.wlp.knowledgepower.factory;

import com.wlp.knowledgepower.config.es.ESConfig;
import com.wlp.knowledgepower.util.SpringContextUtil;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author: wlp
 * @create: 2020-02-27 19:27
 * @description: es 连接工厂类
 **/
public class EsClientPoolFactory implements PooledObjectFactory<RestHighLevelClient> {

    private ESConfig eSConfig ;

    public EsClientPoolFactory(){
        this.eSConfig = SpringContextUtil.getBean("eSConfig",ESConfig.class);
    }

    /**
     * 生产对象
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<RestHighLevelClient> makeObject() throws Exception{
        RestHighLevelClient client = null;
        try {
            client = new RestHighLevelClient(RestClient.builder(
                    new HttpHost(eSConfig.getNetworkHost(), Integer.parseInt(eSConfig.getHttpPort()), "http")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultPooledObject<RestHighLevelClient>(client);
    }

    /**
     * 销毁对象
     * @param pooledObject 池对象
     *
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        RestHighLevelClient object = pooledObject.getObject();
        object.close();
    }

    /**
     * 验证
     * @param pooledObject
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<RestHighLevelClient> pooledObject) {
        return true;
    }

    /**
     * 激活
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        System.out.println("call activateObject");
    }

    /**
     * 卸载
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        System.out.println("call passivateObject");
    }
}
