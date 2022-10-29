package com.fhw.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhw.mapper.BookMapper;
import com.fhw.pojo.Book;
import com.fhw.pojo.BookDoc;
import com.fhw.pojo.BookPageResult;
import com.fhw.pojo.BookRequestParams;
import com.fhw.Service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {


    @Autowired
    private RestHighLevelClient client;

    @Override
    public void insertBookById(Long bookId) {
        try {
            Book book = getById(bookId);

            BookDoc bookDoc = new BookDoc(book);

            IndexRequest request = new IndexRequest("book").id(book.getBookId().toString());

            request.source(JSON.toJSONString(bookDoc), XContentType.JSON);

            client.index(request,RequestOptions.DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookPageResult search(BookRequestParams params) {
        try {

            SearchRequest request = new SearchRequest("book");
            BuildBasicQuery(params, request);
            //分页
            int page = params.getCurrentPage();
            int size = params.getPageSize();
            request.source().from(page - 1).size(size);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            return handleResponse(response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void BuildBasicQuery(BookRequestParams params, SearchRequest request) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //关键字
        String bookName = params.getBookName();
        String bookType = params.getBookType();
        if ((bookName == null || ("").equals(bookName))&&(bookType == null || ("").equals(bookType))){
            boolQuery.must(QueryBuilders.matchAllQuery());
        }

        if (bookName != null && !bookName.equals("")){
            boolQuery.filter(QueryBuilders.matchQuery("bookName",bookName));
        }

        if (bookType != null && !bookType.equals("")){
            boolQuery.filter(QueryBuilders.matchQuery("bookType",bookType));
        }
        request.source().query(boolQuery);
    }

    private BookPageResult handleResponse(SearchResponse response) {
        SearchHits responseHits = response.getHits();

        long total = responseHits.getTotalHits().value;

        System.out.println("共搜到"+total+"条数据");

        SearchHit[] hits = responseHits.getHits();
        List<BookDoc> books = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();

            BookDoc bookDoc = JSON.parseObject(json, BookDoc.class);

//            Object[] sortValues = hit.getSortValues();
//
//            if (sortValues.length > 0){
//                Object sortValue = sortValues[0];
//                BookDoc.setDistance(sortValue);
//            }

            books.add(bookDoc);

        }
        return new BookPageResult(total,books);
    }
}
