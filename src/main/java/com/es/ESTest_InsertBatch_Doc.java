package com.es;


import com.es.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 13:49
 * Version 1.0
 * @Description 批量添加数据
 */
public class ESTest_InsertBatch_Doc {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );


        //创建批量新增请求对象
        BulkRequest request = new BulkRequest();
        request.add(new
                IndexRequest().index("user").id("1001").source(XContentType.JSON, "id","1001","sex","男","name",
                "zhangsan","age",30));
        request.add(new

                IndexRequest().index("user").id("1002").source(XContentType.JSON, "id","1002","sex","男","name",
                        "lisi","age",35));
        request.add(new
                IndexRequest().index("user").id("1003").source(XContentType.JSON, "id","1003","sex","男","name",
                "wangwu","age",40));
        //客户端发送请求，获取响应对象
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        //关闭客户端
        esClient.close();
    }

}
