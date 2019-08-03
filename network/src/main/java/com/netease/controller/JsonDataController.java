package com.netease.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.netease.dto.Result;
import com.netease.entity.Product;
import com.netease.entity.ShoppingCart;
import com.netease.service.ProductService;
import com.netease.service.ShoppingCartService;
import com.netease.vo.PublicProductVo;
import com.netease.vo.SoldItemVo;

@RestController
public class JsonDataController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//获得首页所有数据，包括各商品信息
	@RequestMapping(value="/getIndexData",method=RequestMethod.GET)
	public Result getIndexData() {
		Result result = new Result();
		List<Product> data = productService.getProducts();
		result.setData(data);
		return result;
	}
	
	//获得单个商品的详细信息
	@RequestMapping(value="/getDetailsData",method=RequestMethod.GET)
	public Result getDetailsOfProduct(int id) {
		Result result = new Result();
		Product product = productService.getProductById(id);
		result.setData(product);
		return result;
	}
	
	//卖家删除未售出的商品
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Boolean deleteProductById(@RequestBody JSONObject jsonObject) {
		return productService.deleteProductById(jsonObject.getIntValue("id"));
	}
	
	//加入购物车
	@RequestMapping(value="/addShoppingCart",method=RequestMethod.POST)
	public Boolean addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
		return shoppingCartService.addShoppingCart(shoppingCart);
	}
	
	//对购物车中的商品进行购买
	@RequestMapping(value="/buy",method=RequestMethod.POST)
	public Boolean buy(@RequestBody List<SoldItemVo> list) {
		return productService.updateProduct(list);
	}
	
	//图片上传
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException{
		if (!file.isEmpty()) {
			//上传文件路径
			String path = request.getSession().getServletContext().getRealPath("\\imgs\\file");
			//上传文件名
			String fileName = file.getOriginalFilename();
			File filePath = new File(path,fileName);
			
			//判断路径是否存在，不存在就创建一个
			if (!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = sFormat.format(new Date());
			String realFilePath = path + File.separator + now + "_" +fileName;
			//将上传文件保存到一个目标文件中
			file.transferTo(new File(realFilePath));
			
			return "imgs/file/" + now + "_" +fileName;
		}
		return "";
	}
	
	//卖家发布商品
    @RequestMapping(value="/pub",method=RequestMethod.POST)
    public Boolean publicProduct(ModelMap modelMap,@RequestBody PublicProductVo productVo) {
    	modelMap.addAttribute("user", getPrincipal());
		return productService.publicProduct(productVo);
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
