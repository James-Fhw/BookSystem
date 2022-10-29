package com.fhw.ConfigAndUtils;

public class MqConstants {
    //交换机
    public final static String BOOK_EXCHANGE = "book.topic";
    //监听新增和修改的队列
    public final static String BOOK_INSERT_QUEUE = "book.insert.queue";
    //监听删除的队列
    public final static String BOOK_DELETE_QUEUE = "book.delete.queue";
    //新增和修改的RoutingKey
    public final static String BOOK_INSERT_KEY = "book.insert";
    //新增和修改的RoutingKey
    public final static String BOOK_DELETE_KEY = "book.delete";
}
