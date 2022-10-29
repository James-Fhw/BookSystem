package com.fhw.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_book")
public class Book {

    @TableId(type = IdType.AUTO)
    private Long bookId;
    private String bookType;
    private String bookName;
    private String description;
}
