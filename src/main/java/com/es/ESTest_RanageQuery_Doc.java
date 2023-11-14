package com.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 21:36
 * Version 1.0
 * @Description 范围查询
 */
public class ESTest_RanageQuery_Doc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient EsClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");

        //构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //范围查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");

        // from to 类似于MySQL中的 between ... and ...
        rangeQueryBuilder.from(30); //包含30
        rangeQueryBuilder.to(40);  //包含40

        sourceBuilder.query(rangeQueryBuilder);

        //查询字段过滤
        String[] excludes = {};
        String[] includes = {"name", "age"};
        sourceBuilder.fetchSource(includes, excludes);


        sourceBuilder.sort("age", SortOrder.DESC);

        //分页查询
//        sourceBuilder.from(0); //当前页（类似于MySQL中的页偏移量）
//        sourceBuilder.size(3);  //每页显示多少条数据

        request.source(sourceBuilder);

        SearchResponse response = EsClient.search(request, RequestOptions.DEFAULT);

        //查询匹配
        SearchHits hits = response.getHits();

        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");

        //输出所有匹配的解结果
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        EsClient.close();
    }

}