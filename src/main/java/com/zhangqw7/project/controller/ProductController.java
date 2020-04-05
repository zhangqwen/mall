package com.zhangqw7.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangqw7.project.constant.RespCode;
import com.zhangqw7.project.model.Product;
import com.zhangqw7.project.service.ProductService;
import com.zhangqw7.project.utils.ResponseJson;
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

    /**
     * 查询所有商品
     * @return 商品列表
     */
    @RequestMapping("/product/findAll")
    @ResponseBody
    public String findAllProducts() {
        //调用service的方法
        try{
            List<Product> products = productService.findAll();
            System.out.println("Controller表现层：查询所有商品。。。");

            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "搜索成功", products);
            return JSONObject.toJSONString(rm.toMap());
        }catch (Exception e){
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, e.getMessage());
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 查询商品所有类别
     * @return 类别列表
     */
    @RequestMapping("/product/catalogues")
    @ResponseBody
    public String findAllTypes() {
        //调用service的方法
        try{
            List<String> types = productService.getAllTypes();
            System.out.println("Controller表现层：查询所有商品。。。");

            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "搜索商品类别成功", types);
            return JSONObject.toJSONString(rm.toMap());
        }catch (Exception e){
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, e.getMessage());
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 搜索关键名商品
     * @param product 关键名
     * @return 商品列表
     */
    @RequestMapping(value = "/product/searches", method = RequestMethod.POST)
    @ResponseBody
    public String findProductsByName(@RequestBody Product product) {
        System.out.println(product);

        Product p = productService.findByName(product.getName());
        if(p != null){
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "搜索成功", p);
            return JSONObject.toJSONString(rm.toMap());
        }else {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, "未搜索到相关商品");
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 模糊搜索关键名商品
     * @param product 指定商品名称
     * @return 商品列表
     */
    @RequestMapping(value = "/product/seek", method = RequestMethod.POST)
    @ResponseBody
    public String seekProductsByName(@RequestBody Product product) {
        System.out.println(product);

        List<Product> products = productService.seekByName(product.getName());
        if(products != null){
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "搜索成功", products);
            return JSONObject.toJSONString(rm.toMap());
        }else {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, "未搜索到相关商品");
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 搜索指定id商品
     * @param product 商品id
     * @return 商品
     */
    @RequestMapping(value="/product/search", method=RequestMethod.POST)
    @ResponseBody
//    public String findByName(String name, Model model) {
    public String findProductsById(@RequestBody Product product) {
        System.out.println(product);

        Product p = productService.findById(product.getProduct_id());
        if(p != null){
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "搜索成功", p);
            return JSONObject.toJSONString(rm.toMap());
        }else {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, "未搜索到相关商品");
            return JSONObject.toJSONString(rm.toMap());
        }

    }

    /**
     * 上架商品
     * @param product 商品
     * @return 上架结果
     */
    @RequestMapping("/product/add")
    @ResponseBody
    public String addProduct(@RequestBody Product product) {
        Integer pid = product.getProduct_id();
        Product product1 = productService.findById(pid);
        //查询到已有该商品，在原有基础上添加
        if(product1!=null){
            //验证商品名是否相同（前提是一个商品自发布后不会改名字）
            if(product.getName().equals(product1.getName())){
                //计算已有商品新库存
                Integer stock_before = product1.getStock();
                Integer stock_new = product.getStock();
                if(stock_new!=null) {
                    product1.setStock(stock_before + stock_new);
                }

                //更新其它字段
                if(product.getDetail()!=null){
                    product1.setDetail(product.getDetail());
                }
                if(product.getPicture_url()!=null) {
                    product1.setPicture_url(product.getPicture_url());
                }
                if(product.getPrice()!=null) {
                    product1.setPrice(product.getPrice());
                }
                if(product.getType()!=null) {
                    product1.setType(product.getType());
                }

                //更新数据库
                productService.updateProduct(product1);

                ResponseJson rm = new ResponseJson(RespCode.CODE_1000,
                        "商品"+product1.getName()+"添加成功，原有"+stock_before+"件,现有"+product1.getStock()+"件",
                        product.getId());
                return JSONObject.toJSONString(rm.toMap());
            //商品id与商品名冲突
            }else {
                ResponseJson rm = new ResponseJson(RespCode.CODE_1001, "该商品ID与其它商品重复，请更改");
                return JSONObject.toJSONString(rm.toMap());
            }
        //未查询到该商品，直接添加
        }else {
            productService.saveProduct(product);
            System.out.println("商品录入成功！");
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "商品"+product.getName()+"添加成功", product.getId());
            return JSONObject.toJSONString(rm.toMap());
        }
    }

   /* @RequestMapping("/product/add")
    public String addProduct() {
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


     @RequestMapping("/product/catalog")
     public String catalog() {
         return "catalogue";
     }
    @RequestMapping(value = "/product/{type}", method = RequestMethod.GET)
    public String findByType(@PathVariable("type") String type, Model model) {
        System.out.println(type);
        List<Product> list = productService.findByType(type);
        model.addAttribute("list", list);
        return "detail";
    }*/
}
