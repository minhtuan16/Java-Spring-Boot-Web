package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

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

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillItems;
import com.example.demo.entity.Product;
import com.example.demo.repository.BillItemsRepo;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.ProductRepo;

@Controller
@RequestMapping("/billitems")
public class BillItemsController {
	
	private static Logger logger = LoggerFactory.getLogger(BillItemsController.class);
	@Autowired
	BillItemsRepo billItemsRepo;
	
	@Autowired
	BillRepo billRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);
		
		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		return "billitems/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute BillItems billItems) {
		
		billItemsRepo.save(billItems);
		return "redirect:/billitems/search";
	}
	
	@GetMapping("/update")
	public String update(Model model, @RequestParam("id") int id) {
		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);
		
		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		BillItems billItems = billItemsRepo.getById(id);
		model.addAttribute("billItems", billItems);
		return "billitems/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute BillItems billItems) {
		
		billItemsRepo.save(billItems);
		return "redirect:/billitems/search";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		billItemsRepo.deleteById(id);
		return "redirect:/billitems/search";
	}
	
	@GetMapping("/search")
	public String search(Model model, 
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "billID", required = false) Integer billID,
			@RequestParam(name = "productID", required = false) Integer productID,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {
		
		List<Bill> bills = billRepo.findAll();
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
		
		if(billID != null && productID == null) {
			Page<BillItems> pageBillItems = billItemsRepo.search_bID(billID, pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		} else if(billID == null && productID != null) {
			Page<BillItems> pageBillItems = billItemsRepo.search_pID(productID, pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		} else if (id != null) {
			BillItems billItems  = billItemsRepo.findById(id).orElse(null);
			if (billItems != null) {
				model.addAttribute("list", Arrays.asList(billItems));
			} else
				// log
				logger.info("Id not found");
			
			model.addAttribute("totalPage", 0);
		} else {
			Page<BillItems> pageBillItems = billItemsRepo.findAll(pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("billID", billID == null ? "" : billID);
		model.addAttribute("productID", productID == null ? "" : productID);
		model.addAttribute("id", id == null ? "" : id);
		
		return "billitems/search";
	}
}