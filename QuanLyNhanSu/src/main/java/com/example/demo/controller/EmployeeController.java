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
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmployeeRepo;

@Controller

@RequestMapping("/employee")
public class EmployeeController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	DepartmentRepo departmentRepo;

	@GetMapping("/create")
	public String create(Model model) {
			
		List<Department> departments = departmentRepo.findAll();
		model.addAttribute("departments", departments);

		model.addAttribute("emp", new Employee());
		return "employee/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("emp") @Valid Employee employee,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "employee/create";
		}

		employeeRepo.save(employee);

		return "redirect:/employee/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("maNV") int maNV, Model model) {
		
		List<Department> departments = departmentRepo.findAll();
		model.addAttribute("departments", departments);

		Employee employee = employeeRepo.getById(maNV);
		model.addAttribute("employee", employee);
		return "employee/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute Employee employee) throws ParseException { // no map thang vao doi tuong Student va set
																			// thang gia tri vao id
		// va name

		Employee oldOne = employeeRepo.getById(employee.getMaNV());
		oldOne.setHoTen(employee.getHoTen());
		oldOne.setDiaChi(employee.getDiaChi());
		oldOne.setSoDienThoai(employee.getSoDienThoai());
		oldOne.setBacLuong(employee.getBacLuong());
		oldOne.setDepartment(employee.getDepartment());

		employeeRepo.save(oldOne);

		return "redirect:/employee/search";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("maNV") int maNV) {
		employeeRepo.deleteById(maNV);

		return "redirect:/employee/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model, 
			@RequestParam(name = "maNV", required = false) Integer maNV,
			@RequestParam(name = "hoTen", required = false) String hoTen,
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

		Pageable pageable = PageRequest.of(page, size, Sort.by("maNV").ascending());

		if (hoTen != null && !hoTen.isEmpty() && departmentMaPhong != null) {
			Page<Employee> pageEmp = employeeRepo.searchByDepartmentMaPhong(departmentMaPhong, "%" + hoTen + "%",  pageable);

			model.addAttribute("list", pageEmp.toList());
			model.addAttribute("totalPage", pageEmp.getTotalPages());
		} else if (maNV != null) {
			Employee employee = employeeRepo.findById(maNV).orElse(null);
			if (employee != null) {
				model.addAttribute("list", Arrays.asList(employee));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<Employee> pageEmp = employeeRepo.findAll(pageable);
			model.addAttribute("list", pageEmp.toList());
			model.addAttribute("totalPage", pageEmp.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("maNV", maNV == null ? "" : maNV);
		model.addAttribute("hoTen", hoTen == null ? "" : hoTen);
		model.addAttribute("departmentMaPhong", departmentMaPhong == null ? "" : departmentMaPhong);
		return "employee/search";

	}

}