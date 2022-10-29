package com.fhw.mq;

import com.fhw.ConfigAndUtils.MqConstants;
import com.fhw.Service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookListener {

    @Autowired
    private BookService bookService;

    @RabbitListener(queues = MqConstants.BOOK_INSERT_QUEUE)
    public void listenHotelInsertOrUpdate(Long id){
        System.out.println(1);
        bookService.insertBookById(id); ;
    }

//    @RabbitListener(queues = MqConstants.HOTEL_DELETE_QUEUE)
//    public void listenHotelDelete(Long id){
//        bookService.deleteById(id);
//    }
}
