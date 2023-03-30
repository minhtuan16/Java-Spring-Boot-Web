package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.CategoryRepo;

@Controller
@RequestMapping("/book")
public class BookController {
	private static Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookRepo bookRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@GetMapping("/create")
	public String create(Model model) {
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

		model.addAttribute("book", new Book());
		return "book/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("book") @Valid Book book,
			@RequestParam(name = "file", required = false) MultipartFile file, BindingResult bindingResult) {

		book.setReleaseDay(new Date());

		if (bindingResult.hasErrors()) {
			return "book/create";
		}

		// luu lai file vao 1 folder, sau do lay url save to db
		if (file != null && file.getSize() > 0) {
			try {
				final String folder = "D:/JavaCore/BTL1/picture/upload";
				String originFilename = file.getOriginalFilename();

				File newFile = new File(folder + "/" + originFilename);

				// copy noi dung file upload vao file new
				file.transferTo(newFile);

				// luu lai vao db
				book.setImageURL(originFilename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		bookRepo.save(book);

		return "redirect:/book/search";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

		Book book = bookRepo.getById(id);

		model.addAttribute("book", book);
		return "book/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Book book, @RequestParam(name = "file", required = false) MultipartFile file) {
		// luu lai file vao 1 folder, sau do lay url save to db
		if (file != null && file.getSize() > 0) {
			try {
				final String folder = "D:/JavaCore/BTL1/picture/upload";
				String originFilename = file.getOriginalFilename();

				File newFile = new File(folder + "/" + originFilename);

				// copy noi dung file upload vao file new
				file.transferTo(newFile);

				// luu lai vao db
				book.setImageURL(originFilename); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		book.setReleaseDay(new Date());
		bookRepo.save(book);

		return "redirect:/book/search";
	}

	@GetMapping("/view")
	public String view(@RequestParam("id") int id, Model model) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

		Book book = bookRepo.getById(id);

		model.addAttribute("book", book);
		return "book/view";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		bookRepo.deleteById(id);
		return "redirect:/book/search";
	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "categoryID", required = false) Integer categoryID ,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

		if (size == null) {
			size = 5;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		
		if(categoryID != null && name != null && !name.isEmpty()) {
			Page<Book> pageProduct = bookRepo.searchCategoryIdAndTitle(categoryID, "%" + name + "%", pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (categoryID != null) {
			Page<Book> pageProduct = bookRepo.searchCategoryId(categoryID, pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (categoryID == null && name != null && !name.isEmpty()) {
			Page<Book> pageProduct = bookRepo.searchTitle("%" + name + "%", pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (id != null) {
			Book book = bookRepo.findById(id).orElse(null);
			if(book != null) {
				model.addAttribute("list", Arrays.asList(book));
			} else {
				logger.info("id not found");
			}
			model.addAttribute("totalPage", 0);
		} else {
			Page<Book> pageBook = bookRepo.findAll(pageable);
			model.addAttribute("list", pageBook.toList());
			model.addAttribute("totalPage", pageBook.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("name", name == null ? "" : name);

		return "book/search";
	}

	@GetMapping("/download")
	public void download(@RequestParam("imageURL") String imageURL, HttpServletResponse response) {
		final String folder = "D:/JavaCore/BTL1/picture/upload";

		File file = new File(folder + "/" + imageURL);
		if (file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
