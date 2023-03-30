//package com.example.demo.api;
//
//import java.text.ParseException;
//import java.util.Arrays;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepo;
//
////@Controller
//@RestController
//@RequestMapping("/api/user")
//public class UserAPI {
//
//	private static Logger logger = LoggerFactory.getLogger(UserAPI.class);
//
//	@Autowired
//	UserRepo userRepo;
//
////	@GetMapping("/create")
////	public String create(Model model) {
////
////		model.addAttribute("userrr", new User());
////		return "user/create";
////	}
//	
//	
//	@PostMapping("/create")
//	public User create(@RequestBody @Validated User user) {
//		
////		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//		
//		userRepo.save(user);
//
//		return user;
//	}
//
//	@GetMapping("/update")
//	public String update(@RequestParam("id") int id, Model model) {
//
//		User user = userRepo.getById(id);
//
//		model.addAttribute("user", user);
//		return "user/update";
//	}
//
//	@PostMapping("/update")
////	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
////	2 cai tuong tu nhau
//
//	public String update(@ModelAttribute User user) throws ParseException { // no map thang vao doi tuong Student va set
//																			// thang gia tri vao id
//		// va name
//
//		userRepo.save(user);
//
//		return "redirect:/user/search";
//	}
//
//	@GetMapping("/delete")
//	public String delete(@RequestParam("id") int id) {
//		userRepo.deleteById(id);
//
//		return "redirect:/user/search"; // doc ra man hinh danh sach
//
//	}
//
//	@GetMapping("/search")
//	public String search(Model model, 
////			@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "id", required = false) Integer id,
//			@RequestParam(name = "username", required = false) String username,
//			@RequestParam(name = "page", required = false) Integer page,
//			@RequestParam(name = "size", required = false) Integer size) {
//		// neu khong co required = false thi loi duong truyen
//		// khi co required = false thi truyen String hay khong cung khong anh huong
//
//		if (size == null) {
//			size = 5;
//		}
//
//		if (page == null) {
//			page = 0;
//		}
//
//		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
//
//		if (username != null && !username.isEmpty()) {
//			Page<User> pageUser = userRepo.search1("%" + username + "%", pageable);
//
//			model.addAttribute("list", pageUser.toList());
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//		} else if (id != null) {
//			User user = userRepo.findById(id).orElse(null);
//			if (user != null) {
//				model.addAttribute("list", Arrays.asList(user));
//			} else
//				// log
//				logger.info("Id not found");
//
//			model.addAttribute("totalPage", 0);
//		} else {
//			Page<User> pageUser = userRepo.findAll(pageable);
//			model.addAttribute("list", pageUser.toList());
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//		}
//
//		model.addAttribute("page", page);
//		model.addAttribute("size", size);
//		model.addAttribute("id", id == null ? "" : id);
//		model.addAttribute("username", username == null ? "" : username);
//		return "user/search";
//
//	}
//	
////	@GetMapping("/search")
////	public String search(Model model, @RequestParam(name = "keyword", required = false) String s) {
////
////		if (s == null) {
////			List<User> list = userRepo.findAll();
////			model.addAttribute("userList", list);
////		} else {
////			List<User> list = userRepo.search("%" + s + "%");
////			model.addAttribute("userList", list);
////		}
////		return "user/search";
////	}
//}
