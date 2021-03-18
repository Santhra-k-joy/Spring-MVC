package com.epsilon.training.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epsilon.training.dao.DaoException;
import com.epsilon.training.dao.ProductDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //qualifies for component scan
public class ProductController {

	@Autowired
	private ProductDao dao;
	@RequestMapping("/product-list")
	public String getAllProducts(Model model) throws DaoException
	{
		log.debug("from ProductController.getAllProducts()" );
	model.addAttribute("products",dao.findAll());
		return "/show-products.jsp";
	}
}
