package com.example.demo.api;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.CategoryRepo;

//@Controller
@RestController
@RequestMapping("/api/book")
public class BookRestApi {
	private static Logger logger = LoggerFactory.getLogger(BookRestApi.class);
	
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
	public String create(@ModelAttribute("cate") @Valid Book book,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "book/create";
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
	public String update(@ModelAttribute Book book) {
		bookRepo.save(book);
		
		return "redirect:/book/search";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		bookRepo.deleteById(id);
		return "redirect:/book/search";
	}
	
	@GetMapping("/search")
	public List<Book> search(Model model , 
			@RequestParam(name = "id", required = false) Integer id ,
			@RequestParam(name = "title", required = false) String title ,
			@RequestParam(name = "page", required = false) Integer page ,
			@RequestParam(name = "size", required = false) Integer size) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		if(size == null) {
			size = 5;
		}
		
		if(page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		if(title != null && !title.isEmpty()) {
			Page<Book> pageBook = bookRepo.searchTitle("%" + title + "%", pageable);
			
			model.addAttribute("list", pageBook.toList());
			model.addAttribute("totalPage", pageBook.getTotalPages());
		} else if(id != null) {
			Book book = bookRepo.findById(id).orElse(null);
			if (book != null) {
				model.addAttribute("list", Arrays.asList(book));
			} else {
				logger.info("id not found !");
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
		model.addAttribute("title", title == null ? "" : title);
		
		List<Book> books = bookRepo.findAll();
		return books;
	}
}
