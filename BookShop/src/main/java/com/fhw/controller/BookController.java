package com.fhw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhw.ConfigAndUtils.MqConstants;
import com.fhw.pojo.Book;
import com.fhw.pojo.BookPageResult;
import com.fhw.pojo.BookRequestParams;
import com.fhw.Service.BookService;
import com.fhw.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public R getBookList(){
//        log.info("查看书籍列表");
        return new R(true,bookService.list());
    }


    @PostMapping("/list")
    public R searchBook(@RequestBody BookRequestParams params) throws IOException {
        BookPageResult bookPageResult = bookService.search(params);
        return new R(true,bookPageResult);

    }

}
