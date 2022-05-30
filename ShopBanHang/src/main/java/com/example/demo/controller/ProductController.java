package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity_model.Category;
import com.example.demo.entity_model.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;

@Controller

@RequestMapping("/product")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@GetMapping("/create")
	public String create(Model model) {
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

//		model.addAttribute("pro", new Product());
		return "product/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Product product) {
		
//		if(bindingResult.hasErrors()) {
//			return "product/create";
//		}

		productRepo.save(product);

		return "redirect:/product/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

		Product product = productRepo.getById(id);

		model.addAttribute("product", product);
		return "product/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Product product) { // no map thang vao doi tuong Student va set thang gia tri
																// vao id
		// va name
		productRepo.save(product);

		return "redirect:/product/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		productRepo.deleteById(id);

		return "redirect:/product/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model, 
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "categoryID", required = false) Integer categoryID,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		if (size == null) {
			size = 5;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (name != null && !name.isEmpty() && categoryID != null) {
			Page<Product> pageProduct = productRepo.search12(categoryID, "%" + name + "%", pageable);
			
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (name != null && !name.isEmpty() && categoryID == null) {
			Page<Product> pageProduct = productRepo.search1("%" + name + "%", pageable);
			
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (name == null && categoryID != null) {
			Page<Product> pageProduct = productRepo.searchByCategoryId(categoryID, pageable);
			
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (id != null) {
			Product product = productRepo.findById(id).orElse(null);
			if (product != null) {
				model.addAttribute("list", Arrays.asList(product));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<Product> pageProduct = productRepo.findAll(pageable);
			
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("categoryID", categoryID == null ? "" : categoryID);
		
		return "product/search";

	}

}