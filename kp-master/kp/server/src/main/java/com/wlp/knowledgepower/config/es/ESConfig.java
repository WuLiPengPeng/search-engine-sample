package com.wlp.knowledgepower.config.es;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @author: wlp
 * @create: 2020-02-27 14:41
 * @description: es 配置类
 **/
@Component("eSConfig")
public class ESConfig {


    @Value("${elasticsearch.networkHost}")
    private String networkHost;

    @Value("${elasticsearch.httpPort}")
    private String httpPort;

    @Value("${elasticsearch.clusterName}")
    private String clusterName;

    @Value("${elasticsearch.nodeName}")
    private String nodeName;


    public String getNetworkHost() {
        return networkHost;
    }

    public void setNetworkHost(String networkHost) {
        this.networkHost = networkHost;
    }

    public String getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
