package com.fhw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhw.pojo.Book;
import com.fhw.pojo.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookTypeMapper extends BaseMapper<BookType> {
}
