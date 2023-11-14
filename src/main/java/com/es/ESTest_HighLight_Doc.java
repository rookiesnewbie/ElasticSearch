package com.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;

/**
 * @Author LiTeng
 * @Date 2023/11/13 21:36
 * Version 1.0
 * @Description 高亮查询
 */
public class ESTest_HighLight_Doc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient EsClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");

        //构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.termQuery("name","zhangsan"));

        //构建高亮字段
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");//设置标签前缀
        highlightBuilder.postTags("</font>");//设置标签后缀
        highlightBuilder.field("name");//设置高亮字段
//设置高亮构建对象
        sourceBuilder.highlighter(highlightBuilder);

        //查询字段过滤
        String[] excludes = {};
        String[] includes = {"name", "age"};
        sourceBuilder.fetchSource(includes, excludes);

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
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);

            //打印高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields);
        }

        EsClient.close();
    }

}
