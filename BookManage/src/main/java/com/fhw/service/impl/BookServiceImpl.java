package com.fhw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhw.mapper.BookMapper;
import com.fhw.pojo.*;
import com.fhw.service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;


    @Override
    public Book selectBookById(int bookId) {
        return bookMapper.selectById(bookId);
    }

    //数据库
    @Override
    public boolean saveBook(Book book) {
        return bookMapper.insert(book)>0;
    }



    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateById(book)>0;
    }

    @Override
    public boolean deleteBook(int bookId) {
        return bookMapper.deleteById(bookId) > 0;
    }

    @Override
    public boolean deleteBooks(List<Integer> bookIds) {
        return bookMapper.deleteBatchIds(bookIds) > 0;
    }

    @Override
    public IPage<Book> getBookListByPage(int currentPage, int pageSize,Book book) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(Strings.isNotEmpty(book.getBookType()),Book::getBookType,book.getBookType());
        lqw.like(Strings.isNotEmpty(book.getBookName()),Book::getBookName,book.getBookName());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
        IPage page = new Page(currentPage,pageSize);
        bookMapper.selectPage(page,lqw);
        return page;
    }

    @Override
    public boolean checkBookName(Book book) {
        return bookMapper.selectBookName(book) >0;
    }


}
