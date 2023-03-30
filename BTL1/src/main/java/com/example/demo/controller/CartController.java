package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.model.Cart;
import com.example.demo.repository.BookRepo;


@Controller
@RequestMapping("/cart")
@SuppressWarnings("unchecked")
public class CartController {

	@Autowired
	BookRepo bookRepo;
	 
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id, HttpSession session, Model model, 
			@RequestParam(value = "cartPage", required = false) String cartPage) {
		Book book = bookRepo.getById(id);
		
		if(session.getAttribute("cart") == null) {
			Map<Integer, Cart> cart = new HashMap<Integer, Cart>();
			cart.put(id, new Cart(id, book.getTitle(), book.getBprice(), 1, book.getImageURL()) );
			session.setAttribute("cart", cart);
		} else {
			Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
			if(cart.containsKey(id)) {
				int qty = cart.get(id).getCquantity();
				cart.put(id, new Cart(id, book.getTitle(), book.getBprice(), ++qty, book.getImageURL()));
			} else {
				cart.put(id, new Cart(id, book.getTitle(), book.getBprice(), 1, book.getImageURL()));
				session.setAttribute("cart", cart);
			}
		}
	 	
		Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        int size = 0;
        double total = 0;
        for (Cart value : cart.values()) {
            size += value.getCquantity();
            total = value.getCquantity() * Double.parseDouble(value.getCprice()) + total;
        }

        model.addAttribute("csize", size);
        
        model.addAttribute("ctotal", total);
        
		if(cartPage != null) {
//			return "/bookstore/cart_view";
			return "redirect:/cart/view";
		}
		
		return "redirect:/cart/view";
	}
	
	@GetMapping("/view") 
	public String view(HttpSession session, Model model) {
		if(session.getAttribute("cart") == null) {
			return "/bookstore/cart_view";
		}
		
		Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
		model.addAttribute("cart", cart);
		model.addAttribute("notCartViewPage", true);
		
		return "/bookstore/cart";
	}
	
	
	@GetMapping("/subtract/{id}")
    public String subtract(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
        Book book = bookRepo.getOne(id);
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");

        int qty = cart.get(id).getCquantity();
        if (qty == 1) {
            cart.remove(id);
            if (cart.size() == 0) {
                session.removeAttribute("cart");
            }
        } else {
            cart.put(id, new Cart(id, book.getTitle(), book.getBprice(), --qty, book.getImageURL()));
        }

        String refererLink = httpServletRequest.getHeader("referer");
        return "redirect:" + refererLink;
//        return "/bookstore/cart_view";
    }
 
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
        
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        cart.remove(id);
        if (cart.size() == 0) {
            session.removeAttribute("cart");
        }

        String refererLink = httpServletRequest.getHeader("referer");
        return "redirect:" + refererLink;
//        return "/bookstore/cart_view";
    }
	
	
	 
	@GetMapping("/clear")
	public String clear(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("cart");
		
		String refererLink = request.getHeader("referer");
		return "redirect:" + refererLink;
//		return "/bookstore/cart_view";
	}
}
