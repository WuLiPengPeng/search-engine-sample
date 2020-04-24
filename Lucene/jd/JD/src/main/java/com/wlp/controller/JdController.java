package com.wlp.controller;

import com.wlp.model.ProductModel;
import com.wlp.service.JdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JdController {

    @Autowired
    private JdService jdService ;
    @RequestMapping("list.action")
    public ModelAndView list(String queryString, String catalog_name, String price, String sort) throws Exception {
       System.out.println("queryString:"+queryString+",catalog_name:"+catalog_name+",price:"+price+",sort:"+sort);
        //通过上面的四个条件查询对象商品结果集
        List<ProductModel> productModels = jdService.selectProductListByQuery(queryString, catalog_name, price, sort);
        ModelAndView mv = new ModelAndView();
        mv.addObject("productModels",productModels);
        mv.addObject("queryString",queryString);
        mv.addObject("catalog_name",catalog_name);
        mv.addObject("price",price);
        mv.addObject("sort",sort);
        mv.setViewName("test");
        //product_list.jsp 未使用
        return mv;
    }

}
