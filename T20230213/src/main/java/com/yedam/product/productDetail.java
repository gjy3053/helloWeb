package com.yedam.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;

public class productDetail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		ProductService service = new ProductServiceImpl();
		req.setAttribute("productDetail", service.getProduct(name));
		
		req.setAttribute("productRel", service.relateList()); 
		return "product/productDetail.tiles";
	}

}
