package com.example.www.controller;

import com.example.www.entity.Product;
import com.example.www.entity.ProductType;
import com.example.www.repository.ProductRepository;
import com.example.www.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class LoadController {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/store")
    public String store(Model model, Long id){
        List<ProductType> types = (List<ProductType>) productTypeRepository.findAll();
        ProductType type = new ProductType();
        for (ProductType product: types) {
            if (product.getId() == id) {
                type=product;
                break;
            }
        }
        Iterable<Product> list = productRepository.findByProductType(type);
        model.addAttribute("list", list);
        model.addAttribute("types", types);
        return "store";
    }

    @GetMapping("/")
    public String index(Model model, Long id){
        List<ProductType> types = (List<ProductType>) productTypeRepository.findAll();
        model.addAttribute("types", types);
        return "index";
    }

    @GetMapping("/product")
    public String product(Model model, Long id){
        List<ProductType> types = (List<ProductType>) productTypeRepository.findAll();
        List<Product> products = (List<Product>) productRepository.findAll();
        Product prod = new Product();
        for (Product product: products) {
            if (product.getId() == id) {
                prod=product;
                break;
            }
        }
        model.addAttribute("types", types);
        model.addAttribute("prod", prod);
        return "product";
    }
}