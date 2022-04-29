package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.example.demo.entity_model.Department;
import com.example.demo.entity_model.Ticket;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.TicketRepo;

@Controller

@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	TicketRepo ticketRepo;

	@GetMapping("/create")
	public String create() {

		return "ticket/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Ticket ticket
			, @RequestParam("ngay_Nhan") String date
			, @RequestParam("xu_Ly") String date1) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		ticket.setNgayNhan(dateFormat.parse(date));
		
		ticket.setXuLy(dateFormat.parse(date1));
		
		ticketRepo.save(ticket);

		return "ticket/create";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {

		Ticket ticket = ticketRepo.getById(id);

		model.addAttribute("ticket", ticket);
		return "ticket/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Ticket ticket
			,@RequestParam("ngay_Nhan") String date
			,@RequestParam("xu_Ly") String date1) throws ParseException { // no map thang vao doi tuong Student va set thang gia tri vao id
														// va name
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		ticket.setNgayNhan(dateFormat.parse(date));
		
		ticket.setXuLy(dateFormat.parse(date1));
		
		ticketRepo.save(ticket);

		return "redirect:/ticket/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		ticketRepo.deleteById(id);

		return "redirect:/ticket/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "key", required = false) String s, @RequestParam("id") int x) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
//		if (s == null) {
//			List<Ticket> list = ticketRepo.findAll();
//			model.addAttribute("list", list);
//		} 
//		
//		if () {
//			List<Ticket> list1 = ticketRepo.searchSDT("%" + s + "%");
//			model.addAttribute("list1", list1);
//		} 
//		if (){
//			
//		}
		
		List<Ticket> list = ticketRepo.findAll();
		model.addAttribute("list", list);
		return "ticket/search";
		
	}
	
//	@GetMapping("/searchID")
//	public String searchID(Model model, @RequestParam("id", required = false) int x) {
//		
//	}
}