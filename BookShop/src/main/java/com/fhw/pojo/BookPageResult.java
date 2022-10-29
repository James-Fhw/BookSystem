package com.fhw.pojo;


import lombok.Data;

import java.util.List;

@Data
public class BookPageResult{

    public BookPageResult(Long total,List<BookDoc> books){
        this.total = total;
        this.books = books;
    }

    private Long total;
    private List<BookDoc> books;
}
