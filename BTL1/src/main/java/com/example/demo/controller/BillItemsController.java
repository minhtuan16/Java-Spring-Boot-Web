package com.example.demo.controller;

import java.text.ParseException;
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

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillItems;
import com.example.demo.entity.Book;
import com.example.demo.repository.BillItemsRepo;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.BookRepo;

@Controller
@RequestMapping("/bill-items")
public class BillItemsController {

	@Autowired
	BillItemsRepo billItemsRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	BillRepo billRepo;
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Book> books = bookRepo.findAll();
		model.addAttribute("books", books);
		
		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);
		
		return "bill_items/create";
		
	}
	
	
	@PostMapping("/create")
	public String create(@ModelAttribute BillItems billItems) {
		
		billItemsRepo.save(billItems);

		return "redirect:/bill_items/search";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);

		List<Book> books = bookRepo.findAll();
		model.addAttribute("books", books);

		BillItems billItems = billItemsRepo.getById(id);

		model.addAttribute("billItems", billItems);
		return "bill_items/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute BillItems billItems) { // no map thang vao doi tuong Student va set thang gia tri
																// vao id
		// va name
		
		billItemsRepo.save(billItems);

		return "redirect:/bill_items/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		
		billItemsRepo.deleteById(id);

		return "redirect:/bill_items/search"; // doc ra man hinh danh sach

	}
	
	@GetMapping("/search")
	public String search(Model model, 
			@RequestParam(name = "id", required = false) Integer id,
//			@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "billID", required = false) Integer billID,
			@RequestParam(name = "productID", required = false) Integer productID,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) throws ParseException {
		
		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);

		List<Book> books = bookRepo.findAll();
		model.addAttribute("books", books);
		
		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

//		if(billID != null && productID != null) {
//			Page<BillItems> pageBillItems = billItems_Repo.searchBillProductID(billID, productID, pageable);
//
//			model.addAttribute("list", pageBillItems.toList());
//			model.addAttribute("totalPage", pageBillItems.getTotalPages());
//		} else 
		
		if(productID != null) {
			Page<BillItems> pageBillItems = billItemsRepo.searchProductID(productID, pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		} else {
			Page<BillItems> pageBillItems = billItemsRepo.findAll(pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("productID", productID == null ? "" : productID);
		model.addAttribute("id", id == null ? "" : id);
		
		return "bill_items/search";

	}
}
