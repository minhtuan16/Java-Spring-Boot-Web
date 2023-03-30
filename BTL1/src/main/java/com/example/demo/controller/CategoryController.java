package com.example.demo.controller;

import java.util.Arrays;

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

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("cate", new Category());
		return "category/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("cate") @Valid Category category,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "category/create";
		}
		categoryRepo.save(category);
		
		return "redirect:/category/search";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		Category category = categoryRepo.getById(id);
		
		model.addAttribute("category", category);
		return "category/update";
	}
	 
	@PostMapping("/update")
	public String update(@ModelAttribute Category category) {
		categoryRepo.save(category);
		 
		return "redirect:/category/search";
	} 
	
	@GetMapping("/view")
	public String view(@RequestParam("id") int id, Model model) {
		
		Category category = categoryRepo.getById(id);
		
		model.addAttribute("category", category);
		return "category/view";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		
		categoryRepo.deleteById(id);
		return "redirect:/category/search";
	}
	
	@GetMapping("/search")
	public String search(Model model , 
			@RequestParam(name = "id", required = false) Integer id ,
			@RequestParam(name = "name", required = false) String name ,
			@RequestParam(name = "page", required = false) Integer page ,
			@RequestParam(name = "size", required = false) Integer size) {
		if(size == null) {
			size = 5;
		}
		
		if(page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		if(name != null && !name.isEmpty()) {
			Page<Category> pageCategory = categoryRepo.searchNameC("%" + name + "%", pageable);
			
			model.addAttribute("list", pageCategory.toList());
			model.addAttribute("totalPage", pageCategory.getTotalPages());
		} else if(id != null) {
			Category category = categoryRepo.findById(id).orElse(null);
			if (category != null) {
				model.addAttribute("list", Arrays.asList(category));
			} else {
				logger.info("id not found !");
			}
			model.addAttribute("totalPage", 0);
		} else {
			Page<Category> pageCategory = categoryRepo.findAll(pageable);
			model.addAttribute("list", pageCategory.toList());
			model.addAttribute("totalPage", pageCategory.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("name", name == null ? "" : name);
		
		
		return "/category/search";
	}
}
