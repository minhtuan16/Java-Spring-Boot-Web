package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.User;
import com.example.demo.model.Cart;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.UserRepo;

@Controller
public class HomeController {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookRepo bookRepo;

	@GetMapping("/dang-nhap")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "register.html";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    userRepo.save(user);
	     
	    return "register_success.html";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/dang-nhap";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		List<Book> books = bookRepo.findAll();
		model.addAttribute("books", books); 
		return "book/search1.html";
	}
	
	@GetMapping("/bookstore/home")
	public String bookStore(Model model,
//			@RequestParam(name = "id", required = false) Integer id ,
			@RequestParam(name = "title", required = false) String title,
//			@RequestParam(name = "categoryID", required = false) Integer categoryID ,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (size == null) { 
			size = 9;
		} 

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (title != null && !title.isEmpty()) {
			Page<Book> pageBook = bookRepo.searchTitle("%" + title + "%", pageable);
			model.addAttribute("list", pageBook.toList());
			model.addAttribute("totalPage", pageBook.getTotalPages());
		} else {
			Page<Book> pageBook = bookRepo.findAll(pageable);
			model.addAttribute("list", pageBook.toList());
			model.addAttribute("totalPage", pageBook.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("title", title == null ? "" : title);
		return "bookstore/homeStore";
	}
	
	@GetMapping("/bookstore")
	public String product(Model model, HttpServletRequest request, 
			@RequestParam(name = "id", required = false) Integer id) {

		Book book = bookRepo.getById(id);
		request.setAttribute("book", book);
		
		return "bookstore/productBook";
		
	}
	
	@GetMapping("/bookstore/checkout")
	public String checkOut(HttpSession session, Model model) {
		if(session.getAttribute("cart") == null) {
			return "/bookstore/cart_view";
		}
		
		Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
		model.addAttribute("cart", cart);
		model.addAttribute("notCartViewPage", true);
		
		return "/bookstore/checkout";
	}
	
	@GetMapping("/bookstore/order-success")
	public String orderSuccess() {
		return "/bookstore/order_success";
	}
}
