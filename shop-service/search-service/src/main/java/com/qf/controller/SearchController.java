package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("query")
    public ResultBean searchByKeyword(@PathVariable String keyword){

        ResultBean resultBean = searchService.searchByKeyword(keyword);

        //List<TProductSearchDTO>
        return resultBean;
    }


    @RequestMapping("addProduct")
    public ResultBean addProduct(@PathVariable Long pid){

        return searchService.addProduct(pid);


    }
}
