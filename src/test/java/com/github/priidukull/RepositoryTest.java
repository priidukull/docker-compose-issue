package com.github.priidukull;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;
import java.io.File;

public class RepositoryTest {
    @ClassRule
    public static DockerComposeContainer elasticContainer =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withExposedService("elasticsearch_1", 9200)
                    .withExposedService("elasticsearch_1", 9300);

    @Test
    public void test() throws Exception {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
        RestHighLevelClient client = new RestHighLevelClient(restClient);
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
                .source("user", "kimchy");
        try {
            client.index(indexRequest);
        } catch (Exception e) {
            System.out.println("");
        }

    }
}