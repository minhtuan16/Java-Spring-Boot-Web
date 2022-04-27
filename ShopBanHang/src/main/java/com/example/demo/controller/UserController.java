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

import com.example.demo.entity_model.User;
import com.example.demo.repository.UserRepo;

@Controller

@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo userRepo;

	@GetMapping("/create")
	public String create() {

		return "user/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute User user) {

		userRepo.save(user);

		return "user/create";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {

		User user = userRepo.getById(id);

		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute User user) { // no map thang vao doi tuong Student va set thang gia tri vao id
														// va name
		userRepo.save(user);

		return "redirect:/user/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		userRepo.deleteById(id);

		return "redirect:/user/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		
		List<User> list = userRepo.findAll();
		model.addAttribute("list", list);
		
		return "user/search";
		
	}
	
	@GetMapping("/search-theo-ten")
	public String search1(Model model, @RequestParam(name = "keyword", required = false) String s) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		List<User> list = userRepo.search1("%" + s + "%");
		model.addAttribute("list", list);
		
		return "user/search-theo-ten.html";
		
	}
	
	@GetMapping("/search-theo-role")
	public String search2(Model model, @RequestParam(name = "keyword", required = false) String s) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		
		List<User> list = userRepo.search2("%" + s + "%");
		model.addAttribute("list", list);
		
		return "user/search-theo-role";
	}
	
	@GetMapping("/search-theo-username")
	public String search3(Model model, @RequestParam(name = "keyword", required = false) String s) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		
		List<User> list = userRepo.search3("%" + s + "%");
		model.addAttribute("list", list);
		 
		return "user/search-theo-username";
	}
}