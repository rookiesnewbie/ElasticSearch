package com.es;


import com.es.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 13:49
 * Version 1.0
 * @Description 修改文档
 */
public class ESTest_Update_Doc {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");

        //修改1001的sex为女
        request.doc(XContentType.JSON,"sex","女");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(response.getResult());

        //关闭客户端
        esClient.close();
    }

}
