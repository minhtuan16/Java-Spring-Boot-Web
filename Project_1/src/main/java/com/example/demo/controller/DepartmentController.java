package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
import com.example.demo.repository.DepartmentRepo;

@Controller

@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentRepo departmentRepo;

	@GetMapping("/create")
	public String create() {

		return "department/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Department department, @RequestParam("ngaytao") String date)
			throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		department.setNgayTao(dateFormat.parse(date));

		departmentRepo.save(department);

		return "department/create";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {

		Department department = departmentRepo.getById(id);

		model.addAttribute("department", department);
		return "department/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Department department
			,@RequestParam("ngaytao") String date) throws ParseException { // no map thang vao doi tuong Student va set thang gia
																	// tri vao id
		// va name

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		department.setNgayTao(dateFormat.parse(date));

		departmentRepo.save(department);

		return "redirect:/department/search";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest req, @RequestParam("id") int id) {
		
		departmentRepo.deleteById(id);

		return "redirect:/department/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "key", required = false) String s) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong

		List<Department> list = departmentRepo.search("%" + s + "%");

		model.addAttribute("list", list);

		return "department/search";

	}

}