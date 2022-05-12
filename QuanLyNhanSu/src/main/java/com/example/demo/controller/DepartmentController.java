package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.example.demo.entity_model.Department;
import com.example.demo.repository.DepartmentRepo;

@Controller

@RequestMapping("/department")
public class DepartmentController {
	
	private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	DepartmentRepo departmentRepo;

	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("department", new Department());
		return "department/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("department") @Valid Department department,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "department/create";
		}
		
		departmentRepo.save(department);

		return "redirect:/department/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("maPhong") int maPhong, Model model) {

		Department department = departmentRepo.getById(maPhong);

		model.addAttribute("department", department);
		return "department/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Department department) throws ParseException { // no map thang vao doi tuong Student va set thang gia tri vao id
														// va name
		
		Department oldOne = departmentRepo.getById(department.getMaPhong());
		oldOne.setTenPhong(department.getTenPhong());
		oldOne.setMoTa(department.getMoTa());
		oldOne.setHeSoCV(department.getHeSoCV());
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		department.setCreatedAt(dateFormat.parse(date));
		
		departmentRepo.save(oldOne);

		return "redirect:/department/search";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("maPhong") int maPhong) {
		departmentRepo.deleteById(maPhong);

		return "redirect:/department/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model,
			@RequestParam(name = "tenPhong", required = false) String tenPhong,
			@RequestParam(name = "maPhong", required = false) Integer maPhong,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		
		if (size == null) {
			size = 2;
		}
		
		if (page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("maPhong").ascending());
		
		if (tenPhong != null && !tenPhong.isEmpty()) {
			Page<Department> pageDepartment = departmentRepo.search("%" + tenPhong + "%", pageable);
			
			model.addAttribute("list", pageDepartment.toList());
			model.addAttribute("totalPage", pageDepartment.getTotalPages());
		} else if (maPhong != null) {
			Department department = departmentRepo.findById(maPhong).orElse(null);
			if (department != null) {
				model.addAttribute("list", Arrays.asList(department));
			} else
				// log
				logger.info("Id not found");
			
			model.addAttribute("totalPage", 0);
		} else {
			Page<Department> pageDepartment = departmentRepo.findAll(pageable);
			model.addAttribute("list", pageDepartment.toList());
			model.addAttribute("totalPage", pageDepartment.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("tenPhong", tenPhong == null ? "" : tenPhong);
		model.addAttribute("maPhong", maPhong == null ? "" : maPhong);
		return "department/search";
		
	}

}