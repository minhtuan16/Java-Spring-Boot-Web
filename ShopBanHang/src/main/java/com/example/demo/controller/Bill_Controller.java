package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.demo.entity_model.Bill;
import com.example.demo.entity_model.User;
import com.example.demo.model.BillStat;
import com.example.demo.model.BillStatUserName;
import com.example.demo.repository.Bill_Repo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.MailService;

@Controller

@RequestMapping("/bill")
public class Bill_Controller {

	private static Logger logger = LoggerFactory.getLogger(Bill_Controller.class);

	@Autowired
	Bill_Repo bill_Repo;
	
	@Autowired
	UserRepo userRepo;

	@Autowired
	MailService mailService;
	
	@GetMapping("/create")
	public String create(Model model) {
		List<User> users = userRepo.findAll();
		model.addAttribute("users", users);

		return "bill/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Bill bill) {
		
//		if(bindingResult.hasErrors()) {
//			return "product/create";
//		}

		bill.setBuyDate(new Date());
		
		if(bill.getCouponCode() != null && !bill.getCouponCode().isEmpty()) {
			
		}
		
		bill_Repo.save(bill);
		
//		Date date = new Date().getTime()-5*1000*60;
		
//		User user = userRepo.getById(bill.getUser().getId());
//		mailService.sendEmail(user.getMailUser(), "Bill", "Ban co 1 don hang moi !");
		
		return "redirect:/bill/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		List<User> users = userRepo.findAll();
		model.addAttribute("users", users);

		Bill bill = bill_Repo.getById(id);

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
		bill_Repo.save(bill);

		return "redirect:/bill/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		bill_Repo.deleteById(id);

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
		
		List<User> users = userRepo.findAll();
		model.addAttribute("users", users);
		
		if (size == null) {
			size = 5;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (userID != null && fromDate != null && toDate != null 
				&& !fromDate.trim().isEmpty() && !toDate.trim().isEmpty()) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = bill_Repo.search_User_fromDate_toDate(userID, sdf.parse(fromDate), sdf.parse(toDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else if (userID == null && fromDate != null && toDate != null && !fromDate.trim().isEmpty() && !toDate.trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = bill_Repo.search_fromDate_toDate(sdf.parse(fromDate), sdf.parse(toDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else if (userID == null && fromDate != null && toDate == null && !fromDate.trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = bill_Repo.searchByFrom(sdf.parse(fromDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else if (userID == null && fromDate == null && toDate != null && !toDate.trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = bill_Repo.searchByTo(sdf.parse(toDate), pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else if (userID != null && fromDate == null && toDate == null) {

			Page<Bill> pageBill = bill_Repo.searchUser(userID, pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		} else {
			Page<Bill> pageBill = bill_Repo.findAll(pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		}
		
//		long date1 = new Date().getTime()-5*1000*60; 
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("userID", userID == null ? "" : userID);
		model.addAttribute("fromDate", fromDate == null ? "" : fromDate);
		model.addAttribute("toDate", toDate == null ? "" : toDate);
		
		return "bill/search";

	}
	
	@GetMapping("/thongKe")
	public String thongKe(Model model) throws ParseException {
		List<Object[]> list = bill_Repo.thongKeTheoThang();

		List<BillStat> billStats = new ArrayList<BillStat>();
		if (list != null && !list.isEmpty()) {
			for (Object[] objects : list) {
				BillStat billStat = new BillStat();
				billStat.setSoLuong(Integer.parseInt((objects[0]).toString()));
				billStat.setThang(Integer.parseInt((objects[1]).toString()));
				billStats.add(billStat);
			}
		}
		model.addAttribute("billStats", billStats);
		return "bill/thongKe";
	}
	
	@GetMapping("/thongKe2")
	public String thongKe2(Model model) throws ParseException {
		List<Object[]> list = bill_Repo.thongKeTheoUserName();

		List<BillStatUserName> billStats = new ArrayList<BillStatUserName>();
		if (list != null && !list.isEmpty()) {
			for (Object[] objects : list) {
				BillStatUserName billStat = new BillStatUserName();
				billStat.setSoLuong(Integer.parseInt((objects[0]).toString()));
				billStat.setUserName((objects[1]).toString());
				billStats.add(billStat);
			}
		}
		model.addAttribute("billStats", billStats);
		return "bill/thongKe2";
	}
	
}