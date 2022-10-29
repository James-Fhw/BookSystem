package com.fhw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhw.pojo.BookType;
import com.fhw.service.BookTypeService;
import com.fhw.ConfigAndUtils.BookMsg;
import com.fhw.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping
    public R getTypeList(){
        return new R(true,bookTypeService.list());
    }

    @PostMapping
    public R saveType(@RequestBody BookType bookType){
        boolean flag = bookTypeService.saveBookType(bookType);
        return new R(flag,new BookMsg().insertBookMsg(flag));
    }

    @PutMapping
    public R modifyType(@RequestBody BookType bookType){
        boolean flag = bookTypeService.updateBookType(bookType);
        return new R(flag,new BookMsg().updateBookMsg(flag));
    }

    @GetMapping("{bookTypeId}")
    public R getOneType(@PathVariable int bookTypeId){
        return new R(true,bookTypeService.selectBookTypeById(bookTypeId));
    }

    @DeleteMapping("{bookTypeId}")
    public R removeType(@PathVariable int bookTypeId){
        boolean flag = bookTypeService.deleteBookType(bookTypeId);
        return new R(flag,new BookMsg().deleteBookMsg(flag));
    }

    @DeleteMapping("/deleteTypes/{bookTypeIds}")
    public R removeTypes(@PathVariable List<Integer> bookTypeIds){
        boolean flag = bookTypeService.deleteBookTypes(bookTypeIds);
        return new R(flag,new BookMsg().deleteBookMsg(flag));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getTypeListByPage(@PathVariable int currentPage,@PathVariable int pageSize,BookType bookType){
        IPage<BookType> page = bookTypeService.getBookTypeListByPage(currentPage, pageSize,bookType);
        if (currentPage > page.getPages()){
            page = bookTypeService.getBookTypeListByPage((int) page.getPages(),pageSize,bookType);
        }
        return new R(true,page);
    }
}
