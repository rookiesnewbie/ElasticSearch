package com.es;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 13:49
 * Version 1.0
 * @Description 客户端
 */
public class ESTestClient {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //关闭客户端
        esClient.close();
    }

}
