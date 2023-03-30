package com.example.demo;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.Cart;
import com.example.demo.repository.BookRepo;


@ControllerAdvice
@SuppressWarnings("unchecked")
public class Common {

	@Autowired
	BookRepo bookRepo;
	
	@ModelAttribute
	public void shareData(Model model, HttpSession session) {
		boolean cartActive = false;
		
		if(session.getAttribute("cart") != null) {
			Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
			int size = 0;
			double total = 0;
			for (Cart value: cart.values()) {
				size += value.getCquantity();
				total += value.getCquantity() * Double.parseDouble(value.getCprice());
			}
			
			model.addAttribute("csize", size);
			model.addAttribute("ctotal", total);
			
			cartActive = true;
		}
		
		model.addAttribute("cartActive", cartActive);
	}
}
