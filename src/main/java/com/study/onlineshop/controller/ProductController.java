package com.study.onlineshop.controller;

import com.study.onlineshop.dao.ProductDao;
import com.study.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    ProductDao productDao;

    @RequestMapping(value = "products", method= RequestMethod.GET)
    public String getAllProducts(Model model){
        model.addAttribute("products", productDao.getAll());
        return "products";
    }

    @GetMapping(path = "/product/add")
    public String addProduct(){
        return "add";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProductPost(Product product, Model model){
        productDao.add(product);
        model.addAttribute("products", productDao.getAll());
        return "products";
    }

    @GetMapping(path = "/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model){
        model.addAttribute("product", productDao.getById(id));
        return "edit";
    }


    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
    public String editProduct(Product product, Model model){
        productDao.update(product);
        model.addAttribute("products", productDao.getAll());
        return "products";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable int id, Model model){
        productDao.delete(id);
        model.addAttribute("products", productDao.getAll());
        return "products";
    }
}
