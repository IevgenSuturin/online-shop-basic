package com.study.onlineshop.web.servlet;

import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.service.ServiceLocator;
import com.study.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

// DONE TODO: display add product page on GET request for /product/add
// DONE TODO: add product on POST request for /product/add
public class AddProductServlet extends HttpServlet {
    ProductService productService = (ProductService) ServiceLocator.getService("productService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("add", null);
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.valueOf(request.getParameter("price"));
        if(name == null || name.isEmpty() || price <= 0d){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Product product =new Product();
            product.setName(name);
            product.setPrice(price);
            productService.add(product);
            response.sendRedirect("/products");
        }
    }
}
