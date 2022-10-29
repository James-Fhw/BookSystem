package com.fhw;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhw.pojo.Book;
import com.fhw.pojo.BookDoc;
import com.fhw.service.BookService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class tets {


    private RestHighLevelClient client;

    @Autowired
    private BookService bookService;

    @Test
    void begin(){
        System.out.println(client);
    }

    @Test
    void testExistHotelIndex() throws IOException {
        //1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("book");
        //3.发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    @Test
    void testBulkRequest() throws IOException {

        List<Book> books = bookService.list();

        BulkRequest request = new BulkRequest();

        for (Book book : books) {
            BookDoc bookDoc = new BookDoc(book);

            request.add(new IndexRequest("book").id(bookDoc.getBookId().toString()).source(JSON.toJSONString(bookDoc),XContentType.JSON));
        }

        client.bulk(request,RequestOptions.DEFAULT);
    }

    @Test
    void  search() throws IOException {
        SearchRequest request = new SearchRequest("book");
        //BuildBasicQuery(params, request);
        //分页
        int page = 1;
        int size = 5;
        request.source().query(QueryBuilders.matchAllQuery()).from(page - 1).size(size);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        handleResponse(response);
    }

    void handleResponse(SearchResponse response) {
        SearchHits responseHits = response.getHits();

        long total = responseHits.getTotalHits().value;

        System.out.println("共搜到"+total+"条数据");

        SearchHit[] hits = responseHits.getHits();
        List<BookDoc> hotels = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();

            BookDoc bookDoc = JSON.parseObject(json, BookDoc.class);
            System.out.println(bookDoc);
        }
    }

    @BeforeEach
    void setUp(){
        HttpHost[] hosts;
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.182.25:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
