package com.zhangqw7.project.controller;

import com.zhangqw7.project.model.Product;
import com.zhangqw7.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
//@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/catalog")
    public String catalog() {
        return "catalogue";
    }

    @RequestMapping("/product/findAll")
    public String findAll(Model model) { //存数据， Model对象
        System.out.println("Controller表现层：查询所有商品。。。");

        //调用service的方法
        List<Product> list = productService.findAll();
        model.addAttribute("listAll", list);
        return "detailAll";
    }

    @RequestMapping(value = "/product/{type}", method = RequestMethod.GET)
    public String findByType(@PathVariable("type") String type, Model model) {
        System.out.println(type);
        List<Product> list = productService.findByType(type);
        model.addAttribute("list", list);
        return "detail";
    }

    @RequestMapping(value = "/product/search", method = RequestMethod.POST)
    public String findByName(String name, Model model) {
        System.out.println(name);
        List<Product> list = productService.findByName(name);
        model.addAttribute("listbyname", list);
        return "detail";
    }

    @RequestMapping("/product/add")
    public String addProduct() {
        return "addProductForm";
    }

    @RequestMapping("/product/save")
    public String save(@ModelAttribute Product product) {
        productService.saveProduct(product);
        System.out.println("商品录入成功！");
        return "addProductForm";
    }

    @RequestMapping("/product/order")
    public String order(@ModelAttribute Product product, Model model) {
        model.addAttribute("product_id", product.getId());
        return "orderForm";
    }

    @RequestMapping("/admin/products")
    public String adminProducts(Model model) {
        List<Product> products = productService.findAll();

        model.addAttribute("admin_products", products);
        return "adminProducts";
    }
}
