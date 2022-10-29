package com.fhw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhw.ConfigAndUtils.MqConstants;
import com.fhw.pojo.Book;
import com.fhw.ConfigAndUtils.BookMsg;
import com.fhw.pojo.BookPageResult;
import com.fhw.service.BookService;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public R getBookList(){
//        log.info("查看书籍列表");
        return new R(true,bookService.list());
    }

    @PostMapping
    public R saveBook(@RequestBody Book book){
        //log.info("添加书籍：书籍属性为"+book);
        boolean flag = bookService.saveBook(book);
        rabbitTemplate.convertAndSend(MqConstants.BOOK_EXCHANGE,MqConstants.BOOK_INSERT_KEY,book.getBookId());
        return new R(flag,new BookMsg().insertBookMsg(flag));
    }

    @PutMapping
    public R modifyBook(@RequestBody Book book){
        //log.info("修改书籍：书籍属性修改为"+book);
        boolean flag = bookService.updateBook(book);
        return new R(flag,new BookMsg().updateBookMsg(flag));
    }

    @GetMapping("{bookId}")
    public R getOneBook(@PathVariable int bookId){
        //log.info("查看书籍：书籍ID为"+bookId+"号");
        return new R(true,bookService.selectBookById(bookId));
    }

    @DeleteMapping("{bookId}")
    public R removeBook(@PathVariable int bookId){
        //log.info("删除书籍：书籍ID为"+bookId+"号");
        boolean flag = bookService.deleteBook(bookId);
        return new R(flag,new BookMsg().deleteBookMsg(flag));
    }

    @DeleteMapping("/deleteBooks/{bookIds}")
    public R removeBooks(@PathVariable List<Integer> bookIds){
        //log.info("批量删除书籍：书籍ID有"+bookIds+"号");
        boolean flag = bookService.deleteBooks(bookIds);
        return new R(flag,new BookMsg().deleteBookMsg(flag));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getBookListByPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){
        //log.info("查看书籍：第"+currentPage+"页");
        IPage<Book> page = bookService.getBookListByPage(currentPage, pageSize,book);
        if (currentPage > page.getPages()){
            page = bookService.getBookListByPage((int) page.getPages(),pageSize,book);
        }
        return new R(true,page);
    }

    @PostMapping("/checkBookName")
    public R checkBookName(@RequestBody Book book){
        //log.info("查看书籍：书籍ID为"+bookId+"号");
        boolean flag = bookService.checkBookName(book);
        return new R(flag,new BookMsg().checkBookNameMsg(flag));
    }


}
