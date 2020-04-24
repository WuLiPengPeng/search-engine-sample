package com.wlp.service;

import com.wlp.dao.JdDao;
import com.wlp.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdServiceImpl implements  JdService {

    @Autowired
    private JdDao jdDao ;
    @Override
    public List<ProductModel> selectProductListByQuery(String queryStr, String catalog_name, String price, String sort) throws Exception {
        return jdDao.selectProductListByQuery(queryStr, catalog_name, price, sort);
    }
}
