package com.fhw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fhw.pojo.BookType;

import java.util.List;

public interface BookTypeService extends IService<BookType> {

    BookType selectBookTypeById(int BookTypeId);

    /**
     * 添加类别
     * @param bookType
     * @return
     */
    boolean saveBookType(BookType bookType);

    /**
     * 根据类别id修改
     * @param bookType
     * @return
     */
    boolean updateBookType(BookType bookType);

    /**
     * 根据类别id删除
     * @param bookTypeId
     * @return
     */
    boolean deleteBookType(int bookTypeId);

    /**
     * 根据类别id删除
     * @param bookTypeIds
     * @return
     */
    boolean deleteBookTypes(List<Integer> bookTypeIds);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    IPage<BookType> getBookTypeListByPage(int currentPage, int pageSize,BookType bookType);
}
