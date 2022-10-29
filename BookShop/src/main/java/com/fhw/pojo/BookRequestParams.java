package com.fhw.pojo;

import lombok.Data;

@Data
public class BookRequestParams {

    private Integer currentPage;
    private Integer pageSize;
    private String bookName;
    private String bookType;
}
