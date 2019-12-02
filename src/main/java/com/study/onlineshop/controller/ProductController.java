package com.study.onlineshop.controller;

import com.study.onlineshop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    @Autowired
    ProductDao productDao;

    @RequestMapping(value = "products", method= RequestMethod.GET)
    public String getAllProducts(Model model){
        model.addAttribute("products", productDao.getAll());
        return "products";
    }
}
