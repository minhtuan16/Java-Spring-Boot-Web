package com.example.demo.controller;

import java.text.ParseException;
import java.util.Arrays;
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
import com.example.demo.entity_model.BillItems;
import com.example.demo.entity_model.Product;
import com.example.demo.repository.BillItems_Repo;
import com.example.demo.repository.Bill_Repo;
import com.example.demo.repository.ProductRepo;

@Controller

@RequestMapping("/bill_items")
public class BillItems_Controller {

	private static Logger logger = LoggerFactory.getLogger(BillItems_Controller.class);

	@Autowired
	BillItems_Repo billItems_Repo;
	
	@Autowired
	Bill_Repo bill_Repo;
	
	@Autowired
	ProductRepo productRepo;

	@GetMapping("/create")
	public String create(Model model) {
		List<Bill> bills = bill_Repo.findAll();
		model.addAttribute("bills", bills);

		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		return "bill_items/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute BillItems billItems) {
		
//		if(bindingResult.hasErrors()) {
//			return "product/create";
//		}
		
		billItems_Repo.save(billItems);

		return "redirect:/bill_items/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		List<Bill> bills = bill_Repo.findAll();
		model.addAttribute("bills", bills);

		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);

		BillItems billItems = billItems_Repo.getById(id);

		model.addAttribute("billItems", billItems);
		return "bill_items/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute BillItems billItems) { // no map thang vao doi tuong Student va set thang gia tri
																// vao id
		// va name
		
		billItems_Repo.save(billItems);

		return "redirect:/bill_items/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		
		billItems_Repo.deleteById(id);

		return "redirect:/bill_items/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model, 
			@RequestParam(name = "id", required = false) Integer id,
//			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "billID", required = false) Integer billID,
			@RequestParam(name = "productID", required = false) Integer productID,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) throws ParseException {
		
		List<Bill> bills = bill_Repo.findAll();
		model.addAttribute("bills", bills);

		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		if (size == null) {
			size = 5;
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
		
		if(billID != null && productID == null) {
			Page<BillItems> pageBillItems = billItems_Repo.searchBillID(billID, pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		} else if(billID == null && productID != null) {
			Page<BillItems> pageBillItems = billItems_Repo.searchProductID(productID, pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		} else if (id != null) {
			BillItems billItems = billItems_Repo.findById(id).orElse(null);
			if (billItems != null) {
				model.addAttribute("list", Arrays.asList(billItems));
			} else
				// log
				logger.info("Id not found");
			
			model.addAttribute("totalPage", 0);
		} else {
			Page<BillItems> pageBillItems = billItems_Repo.findAll(pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("billID", billID == null ? "" : billID);
		model.addAttribute("productID", productID == null ? "" : productID);
		model.addAttribute("id", id == null ? "" : id);
		
		return "bill_items/search";

	}

}