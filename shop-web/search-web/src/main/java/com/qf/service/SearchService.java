package com.qf.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "SEARCH-SERVICE")
public interface SearchService {

    @RequestMapping("search/query")
     String searchByKeyword(@PathVariable String keyword);

    @RequestMapping("search/addProduct")
    ResultBean addProduct(@PathVariable Long pid);
}
