package com.fhw.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDoc {

    @TableId("BOOK_ID")
    private Long bookId;
    private String bookType;
    private String bookName;
    private String description;
    private List<String> suggestion;

    public BookDoc(Book book) {
        this.bookId = book.getBookId();
        this.bookType = book.getBookType();
        this.bookName = book.getBookName();
        this.description = book.getDescription();
        this.suggestion = Arrays.asList(this.bookName,this.bookType);
    }
}
