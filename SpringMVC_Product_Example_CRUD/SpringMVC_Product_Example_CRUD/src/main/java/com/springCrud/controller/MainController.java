package com.springCrud.controller;

import com.springCrud.dao.ProductDao;
import com.springCrud.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public String home(Model model){
        List<Product> products = productDao.getProducts();
        model.addAttribute("products",products);
        return "index";
    }

    @RequestMapping("/add-product")
    public String addProduct(){
        return "addProductForm";
    }

    @RequestMapping(value = "/handle-product", method = RequestMethod.POST)
    public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest httpServletRequest){
        System.out.println(product);
        productDao.createProduct(product);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(httpServletRequest.getContextPath()+"/");
        return redirectView;
    }

    @RequestMapping(value = "/delete-product/{productId}")
    public RedirectView deleteProduct(@PathVariable("productId") int productId, HttpServletRequest httpServletRequest){
        System.out.println(productId);
        productDao.deleteProduct(productId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(httpServletRequest.getContextPath()+"/");
        return redirectView;
    }

    @RequestMapping(value = "/edit-product/{productId}")
    public String updateProduct(@PathVariable("productId") int productId, Model model){
        System.out.println(productId);
        Product product = productDao.getSingleProduct(productId);
        model.addAttribute("product",product);
        return "editProduct";
    }

}
