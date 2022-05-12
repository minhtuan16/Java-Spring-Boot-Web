package com.example.demo.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

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
import com.example.demo.entity_model.Employee;
import com.example.demo.entity_model.TimeSheet;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.TimeSheetRepo;

@Controller

@RequestMapping("/timesheet")
public class TimeSheetController {
	
	private static Logger logger = LoggerFactory.getLogger(TimeSheetController.class);

	@Autowired
	TimeSheetRepo timeSheetRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	DepartmentRepo departmentRepo;
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Employee> employees = employeeRepo.findAll();
		model.addAttribute("employees", employees);
		
		List<Department> departments = departmentRepo.findAll();
		model.addAttribute("departments", departments);
		return "timesheet/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute TimeSheet timesheet,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "timesheet/create";
		}
		
//		đoạn này tương đương với thuộc tính employee trong bảng timesheet mà để UNIQUE
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
//		Page<TimeSheet> page2 = timeSheetRepo.search1(timesheet.getEmployee().getMaNV(), pageable);
//		
//		if(page2.getNumberOfElements() == 0) {
//			timeSheetRepo.save(timesheet);
//		}
		
		timeSheetRepo.save(timesheet);
		return "redirect:/timesheet/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		List<Employee> employees = employeeRepo.findAll();
		model.addAttribute("employees", employees);

		TimeSheet timesheet = timeSheetRepo.getById(id);

		model.addAttribute("timesheet", timesheet);
		return "timesheet/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute TimeSheet timesheet) throws ParseException { // no map thang vao doi tuong Student va set thang gia tri vao id
														// va name
		
//		Department oldOne = departmentRepo.getById(department.getMaPhong());
//		oldOne.setTenPhong(timeSheet.getTenPhong());
//		oldOne.setMoTa(timeSheet.getMoTa());
//		oldOne.setHeSoCV(timeSheet.getHeSoCV());
//		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		department.setCreatedAt(dateFormat.parse(date));
		
		timeSheetRepo.save(timesheet);

		return "redirect:/timesheet/search";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		timeSheetRepo.deleteById(id);

		return "redirect:/timesheet/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "departmentMaPhong", required = false) Integer departmentMaPhong,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {
		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong
		
		List<Department> departments = departmentRepo.findAll();
		model.addAttribute("departments", departments);
		
		if (size == null) {
			size = 3;
		}
		
		if (page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (departmentMaPhong != null) {
			Page<TimeSheet> pageTimeSheet = timeSheetRepo.searchByDepartmentMaPhong(departmentMaPhong, pageable);
			
			model.addAttribute("list", pageTimeSheet.toList());
			model.addAttribute("totalPage", pageTimeSheet.getTotalPages());
		} else if (id != null) {
			TimeSheet timesheet = timeSheetRepo.findById(id).orElse(null);
			if (timesheet != null) {
				model.addAttribute("list", Arrays.asList(timesheet));
			} else
				// log
				logger.info("Id not found");
			
			model.addAttribute("totalPage", 0);
		} else {
			Page<TimeSheet> pageTimeSheet = timeSheetRepo.findAll(pageable);
			model.addAttribute("list", pageTimeSheet.toList());
			model.addAttribute("totalPage", pageTimeSheet.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("departmentMaPhong", departmentMaPhong == null ? "" : departmentMaPhong);
		model.addAttribute("id", id == null ? "" : id);
		
		return "timesheet/search";
		
	}

}