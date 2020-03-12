package com.qf.contrller;

import com.qf.dto.ResultBean;
import com.qf.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("query")
    public String searchByKeyword(@PathVariable String keyword, Model model){

     //   ResultBean resultBean = searchService.searchByKeyword(keyword);

        //List<TProductSearchDTO>
      //  model.addAttribute("products",resultBean.getData());
        return "search";
    }


    @RequestMapping("addProduct")
    public ResultBean addProduct(@PathVariable Long pid){

        return searchService.addProduct(pid);


    }
}
