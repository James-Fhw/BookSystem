package com.fhw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhw.mapper.BookTypeMapper;
import com.fhw.pojo.BookType;
import com.fhw.service.BookTypeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Override
    public BookType selectBookTypeById(int BookTypeId) {
        return bookTypeMapper.selectById(BookTypeId);
    }

    @Override
    public boolean saveBookType(BookType bookType) {
        return bookTypeMapper.insert(bookType) > 0;
    }

    @Override
    public boolean updateBookType(BookType bookType) {
        return bookTypeMapper.updateById(bookType) > 0;
    }

    @Override
    public boolean deleteBookType(int bookTypeId) {
        return bookTypeMapper.deleteById(bookTypeId) > 0;
    }

    @Override
    public boolean deleteBookTypes(List<Integer> bookTypeIds) {
        return bookTypeMapper.deleteBatchIds(bookTypeIds) > 0;
    }

    @Override
    public IPage<BookType> getBookTypeListByPage(int currentPage, int pageSize, BookType bookType) {
        LambdaQueryWrapper<BookType> lqw = new LambdaQueryWrapper<BookType>();
        lqw.like(Strings.isNotEmpty(bookType.getTypeName()),BookType::getTypeName,bookType.getTypeName());
        IPage page = new Page(currentPage,pageSize);
        bookTypeMapper.selectPage(page,lqw);
        return page;
    }
}
