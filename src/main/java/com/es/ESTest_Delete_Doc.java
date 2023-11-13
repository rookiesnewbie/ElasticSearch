package com.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 13:49
 * Version 1.0
 * @Description 删除文档
 */
public class ESTest_Delete_Doc {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //删除文档
        DeleteRequest request = new DeleteRequest().index("user").id("1001");

        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
        //响应状态
        System.out.println(response.getResult());

        //关闭客户端
        esClient.close();
    }

}
