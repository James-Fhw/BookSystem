package com.fhw.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fhw.pojo.Book;
import com.fhw.pojo.BookPageResult;
import com.fhw.pojo.BookRequestParams;

import java.io.IOException;
import java.util.List;

public interface BookService extends IService<Book> {



    void insertBookById(Long bookId);

    BookPageResult search(BookRequestParams params) throws IOException;
}
