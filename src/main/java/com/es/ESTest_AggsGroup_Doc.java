package com.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 21:36
 * Version 1.0
 * @Description 聚合查询 -- 分组统计
 */
public class ESTest_AggsGroup_Doc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient EsClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");

        //构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();



        // 分组统计
        sourceBuilder.aggregation(AggregationBuilders.terms("age_group").field("age"));



        request.source(sourceBuilder);

        SearchResponse response = EsClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response);


        EsClient.close();
    }

}
