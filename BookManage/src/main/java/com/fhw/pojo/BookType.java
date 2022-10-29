package com.fhw.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_type")
public class BookType {
    @TableId("TYPE_ID")
    private int typeId;

    private String typeName;

}
