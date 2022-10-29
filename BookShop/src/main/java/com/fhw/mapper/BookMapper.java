package com.fhw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhw.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book> {

    @Select("select count(*) from t_book where BOOK_NAME = #{bookName}")
    int selectBookName(Book book);

}
