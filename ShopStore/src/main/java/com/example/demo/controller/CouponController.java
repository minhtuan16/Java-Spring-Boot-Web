package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Coupon;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.CouponRepo;

@Controller
@RequestMapping("/coupon")
public class CouponController {
	private static Logger logger = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	CouponRepo couponRepo;

	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("coupon", new Coupon());
		return "coupon/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("coupon") @Valid Coupon coupon,
			@RequestParam("expired_Date") String expired_Date, BindingResult bindingResult) throws ParseException {

		if (bindingResult.hasErrors()) {
			return "coupon/create";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		coupon.setExpiredDate(dateFormat.parse(expired_Date));
		
		couponRepo.save(coupon);

		return "redirect:/coupon/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		Coupon coupon = couponRepo.getById(id);

		model.addAttribute("coupon", coupon);
		return "coupon/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Coupon coupon, @RequestParam("expired_Date") String expiredDate) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			coupon.setExpiredDate(dateFormat.parse(expiredDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		couponRepo.save(coupon);

		return "redirect:/coupon/search";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		couponRepo.deleteById(id);
		return "redirect:/coupon/search";
	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "couponCode", required = false) String couponCode,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (size == null) {
			size = 5;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (couponCode != null && !couponCode.isEmpty()) {
			Page<Coupon> pageCoupon = couponRepo.searchCC("%" + couponCode + "%", pageable);

			model.addAttribute("list", pageCoupon.toList());
			model.addAttribute("totalPage", pageCoupon.getTotalPages());
		} else if (id != null) {
			Coupon coupon = couponRepo.findById(id).orElse(null);
			if (coupon != null) {
				model.addAttribute("list", Arrays.asList(coupon));
			} else {
				logger.info("id not found !");
			}
			model.addAttribute("totalPage", 0);
		} else {
			Page<Coupon> pageCoupon = couponRepo.findAll(pageable);

			model.addAttribute("list", pageCoupon.toList());
			model.addAttribute("totalPage", pageCoupon.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("couponCode", couponCode == null ? "" : couponCode);

		return "coupon/search";
	}
}
