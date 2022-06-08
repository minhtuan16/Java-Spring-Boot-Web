package com.example.demo.controller;

import java.text.ParseException;
import java.util.Arrays;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.MailService;

@Controller
@RequestMapping("/user")
public class UserControllerr {
	
	private static Logger logger = LoggerFactory.getLogger(UserControllerr.class);
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/create")
//	@PreAuthorize("hasRole('ROLE_Admin')")
	@RolesAllowed(value = { "ROLE_Admin" })
	public String create(Model model, HttpServletRequest request) {
		// check vai tro admin
		org.springframework.security.core.userdetails.User currentUser = 
				(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(currentUser.getUsername());
		
		if (request.isUserInRole("Admin")) {
			System.out.println("Admin");
		}
		model.addAttribute("userrr", new User());
		return "user/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("userrr") @Valid User user,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "user/create";
		}
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepo.save(user);
		
		
		return "redirect:/user/search";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		User user = userRepo.getById(id);
		
		model.addAttribute("user", user);
		return "user/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user) throws ParseException{
		
		userRepo.save(user);
		
		return "redirect:/user/search";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		userRepo.deleteById(id);
		
		return "redirect:/user/search";
	}
	
	@GetMapping("/search")
	public String search(Model model,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {
		
		if(size == null) {
			size = 5;
		}
		
		if(page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		if(username != null && !username.isEmpty()) {
			Page<User> pageUser = userRepo.searchUsername("%" + username + "%", pageable);
			model.addAttribute("list", pageUser.toList());
			model.addAttribute("totalPage", pageUser.getTotalPages());
		} else if (id != null) {
			User user = userRepo.findById(id).orElse(null);
			if(user != null) {
				model.addAttribute("list", Arrays.asList(user));
			} else {
				logger.info("id not found !");
			}
			model.addAttribute("totalPage", 0);
		} else {
			Page<User> pageUser = userRepo.findAll(pageable);
			model.addAttribute("list", pageUser.toList());
			model.addAttribute("totalPage", pageUser.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("username", username == null ? "" : username);
		
		return "user/search";
	}
}
