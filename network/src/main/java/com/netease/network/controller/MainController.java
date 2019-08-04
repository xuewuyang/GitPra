package com.netease.network.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.netease.network.entity.Product;
import com.netease.network.entity.ShoppingCart;
import com.netease.network.service.ProductService;
import com.netease.network.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//访问首页
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap,@RequestParam(required=false) Integer type) {
		modelMap.addAttribute("user", getPrincipal());
		if (type != null) {
			List<Product> data = productService.findNotBuyProducts();
			modelMap.addAttribute("data", data);
			return "index";
		}
		List<Product> data = productService.findAll();
		modelMap.addAttribute("data", data);
		return "index";
	}
	
	//卖家登录成功后跳转至该页
	@RequestMapping("/seller")
	public String seller(ModelMap modelMap) {
		modelMap.addAttribute("user", getPrincipal());
		List<Product> data = productService.findAll();
		modelMap.addAttribute("data", data);
		return "index";
	}
	
	//买家登录成功后跳转至该页
	@RequestMapping("/buyer")
	public String buyer(ModelMap modelMap) {
		modelMap.addAttribute("user", getPrincipal());
		List<Product> data = productService.findAll();
		modelMap.addAttribute("data", data);
		return "index";
	}
	
	//单个商品详情，不同用户返回的页面结构不一样
	@RequestMapping("/show")
	public String pageShow(ModelMap modelMap,@RequestParam Integer id) {
		modelMap.addAttribute("user", getPrincipal());
		Product product = productService.findProductById(id);
		modelMap.addAttribute("message",product);
		return "show";
	}
	
	//账务页面
	@RequestMapping("/account")
	public String account(ModelMap modelMap) {
		modelMap.addAttribute("user", getPrincipal());
		List<Product> data = productService.findSoldProducts();
		modelMap.addAttribute("message", data);
		return "account";
	}
	
	//发布页面
	@RequestMapping("/public")
	public String publicProduct(ModelMap modelMap,@RequestParam(required=false) Integer id) {
		modelMap.addAttribute("user", getPrincipal());
		if (id != null) {
			Product product = productService.findProductById(id);
			modelMap.addAttribute("message",product);
		}
		return "public";
	}
	
	//购物车页面
	@RequestMapping("/shoppingcart")
	public String shoppingCart(ModelMap modelMap) {
		modelMap.addAttribute("user", getPrincipal());
		List<ShoppingCart> data = shoppingCartService.getShoppingCarts();
		if (data != null)
			modelMap.addAttribute("data", data);
		return "shoppingcart";
	}
	
	//搜索商品
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String searchProduct(ModelMap modelMap, @RequestParam("searchInput") String searchInfo) {
		modelMap.addAttribute("user", getPrincipal());
		if (searchInfo != null) {
			List<Product> data = productService.searchProducts(searchInfo);
			if (data != null)
				modelMap.addAttribute("data",data);
			return "index";
		} else {
			return "forward:home";
		}
	}
	
	@RequestMapping("/publicSubmit")
	public String publicSubmit(ModelMap modelMap) {
		modelMap.addAttribute("user", getPrincipal());
		return "publicSubmit";
	}
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
