package com.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 13:49
 * Version 1.0
 * @Description 批量删除数据
 */
public class ESTest_DeleteBatch_Doc {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );


        //创建批量删除请求对象
        BulkRequest request = new BulkRequest();
        request.add(new
                DeleteRequest().index("user").id("1001"));
        request.add(new

                DeleteRequest().index("user").id("1002"));
        request.add(new
                DeleteRequest().index("user").id("1003"));
        //客户端发送请求，获取响应对象
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        //关闭客户端
        esClient.close();
    }

}
