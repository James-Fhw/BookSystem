package com.fhw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fhw.pojo.Book;

import java.util.List;

public interface BookService extends IService<Book> {

    Book selectBookById(int bookId);

    /**
     * 添加类别
     * @param book
     * @return
     */
    boolean saveBook(Book book);



    /**
     * 根据类别id修改
     * @param book
     * @return
     */
    boolean updateBook(Book book);

    /**
     * 根据书籍id删除
     * @param bookId
     * @return
     */
    boolean deleteBook(int bookId);

    /**
     * 根据书籍id删除
     * @param bookIds
     * @return
     */
    boolean deleteBooks(List<Integer> bookIds);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    IPage<Book> getBookListByPage(int currentPage, int pageSize, Book book);


    /**
     * 检查书名是否重复
     * @param book
     * @return
     */
    boolean checkBookName(Book book);

}
