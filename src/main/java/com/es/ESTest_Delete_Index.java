package com.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/13 13:49
 * Version 1.0
 * @Description 删除索引
 */
public class ESTest_Delete_Index {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //删除索引
        DeleteIndexRequest user = new DeleteIndexRequest("user");//索引名必须是小写
        AcknowledgedResponse response = esClient.indices().delete(user, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(response.isAcknowledged());

        //关闭客户端
        esClient.close();
    }

}
