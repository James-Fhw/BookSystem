package com.fhw.ConfigAndUtils;

import com.fhw.utils.MessageUtils;

public class BookMsg implements MessageUtils {

    private String msg;

    //添加操作
    public String insertBookMsg(boolean flag){
        if(flag){
            msg = "添加成功";
        }else {
            msg = "添加失败";
        }
        return msg;
    }

    //修改操作
    public String updateBookMsg(boolean flag){
        if(flag){
            msg = "修改成功";
        }else {
            msg = "修改失败";
        }
        return msg;
    }

    //删除操作
    public String deleteBookMsg(boolean flag){
        if(flag){
            msg = "删除成功";
        }else {
            msg = "删除失败";
        }
        return msg;
    }

    public String checkBookNameMsg(boolean flag){
        if (flag){
            msg = "书籍名称重复！";
        }else {
            msg = "名字可以使用！";
        }
        return msg;
    }
}

