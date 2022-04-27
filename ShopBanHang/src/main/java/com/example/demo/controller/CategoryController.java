package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity_model.Category;
import com.example.demo.entity_model.User;
import com.example.demo.repository.CategoryRepo;

@Controller

@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryRepo categoryRepo;

	@GetMapping("/create")
	public String create() {

		return "category/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Category category) {

		categoryRepo.save(category);

		return "category/create";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {

		Category category = categoryRepo.getById(id);

		model.addAttribute("category", category);
		return "category/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Category category) { // no map thang vao doi tuong Student va set thang gia tri vao id
														// va name
		categoryRepo.save(category);

		return "redirect:/category/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		categoryRepo.deleteById(id);

		return "redirect:/category/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		
		List<Category> list = categoryRepo.findAll();
		model.addAttribute("list", list);
		
		return "category/search";
		
	}

}