package com.wlp.dao;

import com.wlp.model.ProductModel;

import java.util.List;

public interface JdDao {
    //查询商品列表
    public List<ProductModel> selectProductListByQuery(String queryStr, String catalog_name, String price, String sort) throws Exception ;
}
