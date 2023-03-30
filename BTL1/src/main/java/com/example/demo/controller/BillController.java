package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.SecurityUtils;
import com.example.demo.entity.Bill;
import com.example.demo.entity.User;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.utils.MailService;

@Controller
@RequestMapping("/bill")
public class BillController {

	@Autowired
	BillRepo billRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/create")
	public String create(Model model) {
//		List<User> users = userRepo.findAll();
//		model.addAttribute("users", users);
		
		/*
		 * User user = new User();
		 * user.setUsername(SecurityUtils.getPrincipal().getUsername());
		 * user.setId(SecurityUtils.getPrincipal().getId());
		 * user.setMailUser(SecurityUtils.getPrincipal().getMailUser());
		 * 
		 * model.addAttribute("user", user);
		 */
		
		return "bill/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute Bill bill) {
		bill.setBuyDate(new Date());
		
		billRepo.save(bill);
		
//		User user = userRepo.getById(bill.getUser().getId());
//		mailService.sendEmail(user.getMailUser(), "Bill", "Ban co 1 don hang BookStore !!!");
		return "redirect:/bookstore/order-success";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
//		List<User> users = userRepo.findAll();
//		model.addAttribute("users", users);

		Bill bill = billRepo.getById(id);

		model.addAttribute("bill", bill);
		return "bill/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Bill bill) { // no map thang vao doi tuong Student va set thang gia tri
																// vao id
		// va name
		bill.setBuyDate(new Date());
		billRepo.save(bill);

		return "redirect:/bill/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		billRepo.deleteById(id);

		return "redirect:/bill/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model, 
//			@RequestParam(name = "id", required = false) Integer id,
//			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "userID", required = false) Integer userID,
			@RequestParam(name = "fromDate", required = false) String fromDate,
			@RequestParam(name = "toDate", required = false) String toDate,
			@RequestParam(name = "date", required = false) Integer date,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) throws ParseException {
		
//		List<User> users = userRepo.findAll();
//		model.addAttribute("users", users);
		
		if (size == null) {
			size = 5;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (fromDate != null && toDate != null && !fromDate.trim().isEmpty() && !toDate.trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = billRepo.searchFromTo(sdf.parse(fromDate), sdf.parse(toDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else if (fromDate != null && toDate == null && !fromDate.trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = billRepo.searchFrom(sdf.parse(fromDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else if ( fromDate == null && toDate != null && !toDate.trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = billRepo.searchTo(sdf.parse(toDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else {
			Page<Bill> pageBill = billRepo.findAll(pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		}
		
//		long date1 = new Date().getTime()-5*1000*60; 
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("fromDate", fromDate == null ? "" : fromDate);
		model.addAttribute("toDate", toDate == null ? "" : toDate);
		
		return "bill/search";

	}
}
